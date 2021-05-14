package ts;

import main.DbContext;
import rdg.AccountType;
import rdg.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MoneyTransfer {
    private static final MoneyTransfer INSTANCE = new MoneyTransfer();

    public static MoneyTransfer getInstance() {
        return INSTANCE;
    }

    public void moneyTranfer(String accountNumberFrom, String accountNumberTo, BigDecimal amount) throws TransferException, SQLException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be higher than 0");
        }

        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        DbContext.getConnection().setAutoCommit(false);


        Account source = AccountFinder.getInstance().findByAccountNumber(accountNumberFrom);
        Account destination = AccountFinder.getInstance().findByAccountNumber(accountNumberTo);

        if(source == null) {
            DbContext.getConnection().rollback();
            DbContext.getConnection().setAutoCommit(true);
            throw new TransferException("Source account doesnt exist");
        }

        if(source.getAvailableBalance().compareTo(amount) < 0) {
            DbContext.getConnection().rollback();
            DbContext.getConnection().setAutoCommit(true);
            throw new TransferException("Not enough balance");
        }

        Transaction t = new Transaction();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        t.setFromId(source.getId());
        t.setFromAccount(accountNumberFrom);
        t.setToAccount(accountNumberTo);
        t.setDatetime(now);
        t.setType("TRANSFER");
        t.setAmount(amount);
        t.setCurrencyId(source.getCurrencyId());

        if(destination == null) {
            switch(AccountType.valueOf(source.getAccountType())) {
                case CURRENT:
                    break;
                case TERM:
                    if(source.getCommitmentTill().getTime() < System.currentTimeMillis()) {
                        DbContext.getConnection().rollback();
                        DbContext.getConnection().setAutoCommit(true);
                        throw new TransferException("Source Term account commitment date still not ended");
                    }
                    break;
                case SAVINGS:
                    //sporiaci ucet nemoze byt naviazany na ucet v inej banke

                    DbContext.getConnection().rollback();
                    DbContext.getConnection().setAutoCommit(true);
                    throw new TransferException("Savings account is not assigned into another bank account");
            }

            source.setAvailableBalance(source.getAvailableBalance().subtract(amount));
            source.update();

            t.setCompleted(false);
        }
        else {
            switch(AccountType.valueOf(source.getAccountType())) {
                case CURRENT:
                    break;
                case TERM:
                    if(source.getCommitmentTill().getTime() < System.currentTimeMillis()) {
                        DbContext.getConnection().rollback();
                        DbContext.getConnection().setAutoCommit(true);
                        throw new TransferException("Source Term account commitment date still not ended");
                    }
                    break;
                case SAVINGS:
                    if(!source.getCurrentAccountId().equals(destination.getId())) {
                        DbContext.getConnection().rollback();
                        DbContext.getConnection().setAutoCommit(true);
                        throw new TransferException("You can't send money from savings acount to other current accounts, than its assigned.");
                    }
                    break;
            }

            switch(AccountType.valueOf(destination.getAccountType())) {
                case CURRENT:
                    break;
                case TERM:
                    if(destination.getCommitmentTill().getTime() < System.currentTimeMillis()) {
                        DbContext.getConnection().rollback();
                        DbContext.getConnection().setAutoCommit(true);
                        throw new TransferException("You can't send money to term account with still active commitment date");
                    }
                    break;
                case SAVINGS:
                    //prevod moze byt iba z jeho current uctu na ktory je viazany
                    if(!destination.getCurrentAccountId().equals(source.getId())) {
                        DbContext.getConnection().rollback();
                        DbContext.getConnection().setAutoCommit(true);
                        throw new TransferException("You can't send to money to savings account from different current account than assigned");
                    }
                    break;
            }

            t.setToId(destination.getId());
            t.setCompleted(true);

            source.setAvailableBalance(source.getAvailableBalance().subtract(amount));
            source.setCurrentBalance(source.getCurrentBalance().subtract(amount));

            CurrencyRate cr = CurrencyRateFinder.getInstance().findCurrencyRateByCurrencyIds(source.getCurrencyId(), destination.getCurrencyId());
            if(cr == null) {
                DbContext.getConnection().rollback();
                DbContext.getConnection().setAutoCommit(true);
                throw new TransferException("Currency rate not found");
            }

            BigDecimal newAmount = amount.multiply(cr.getRate());

            destination.setAvailableBalance(destination.getAvailableBalance().add(newAmount));
            destination.setCurrentBalance(destination.getCurrentBalance().add(newAmount));

            source.update();
            destination.update();
        }

        t.insert();

        DbContext.getConnection().commit();
        DbContext.getConnection().setAutoCommit(true);
    }
}
