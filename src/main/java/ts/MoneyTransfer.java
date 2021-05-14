package ts;

import entities.AccountType;
import rdg.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MoneyTransfer {
    private static final MoneyTransfer INSTANCE = new MoneyTransfer();

    public static MoneyTransfer getInstance() {
        return INSTANCE;
    }

    public void moneyTranfer(String accountNumberFrom, String accountNumberTo, BigDecimal amount) throws TranferException, SQLException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be higher than 0");
        }

        Account source = AccountFinder.getInstance().findByAccountNumber(accountNumberFrom);
        Account destination = AccountFinder.getInstance().findByAccountNumber(accountNumberTo);

        if(source == null) {
            //todo acc doesnt exist
            //todo end this func with err / raise exception??
        }

        if(source.getAvailableBalance().compareTo(amount) < 0) {
            //todo not enough balance on acc available
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
                        //todo nemozme nic
                        //return
                    }
                    break;
                case SAVINGS:
                    //todo ak je sporiaci tak druhy musi byt jeho current
                    //todo tu je druhy z inej banky takze nic
                    //return
                    break;
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
                        //todo nemozme nic
                    }
                    //todo check commitment till if still waiting then cant
                    break;
                case SAVINGS:
                    //todo ak je sporiaci tak druhy musi byt jeho current
                    if(!source.getCurrentAccountId().equals(destination.getId())) {
                        //todo nerovnaju sa treba opravit..
                    }
                    break;
            }

            switch(AccountType.valueOf(destination.getAccountType())) {
                case CURRENT:
                    break;
                case TERM:
                    if(destination.getCommitmentTill().getTime() < System.currentTimeMillis()) {
                        //todo nemozme nic
                    }
                    //todo check commitment till if still waiting then cant
                    break;
                case SAVINGS:
                    //prevod moze byt iba z jeho current uctu na ktory je viazany
                    if(!destination.getCurrentAccountId().equals(source.getId())) {
                        //todo nerovnaju sa treba opravit..
                    }
                    break;
            }
            //todo if term or savings conditions apply heree

            t.setToId(destination.getId());
            t.setCompleted(true);

            source.setAvailableBalance(source.getAvailableBalance().subtract(amount));
            source.setCurrentBalance(source.getCurrentBalance().subtract(amount));

            CurrencyRate cr = CurrencyRateFinder.getInstance().findCurrencyRateByCurrencyIds(source.getCurrencyId(), destination.getCurrencyId());
            if(cr == null) {
                //todo raise exception or stahp
                return;
            }

            BigDecimal newAmount = amount.multiply(cr.getRate());

            destination.setAvailableBalance(destination.getAvailableBalance().add(newAmount));
            destination.setCurrentBalance(destination.getCurrentBalance().add(newAmount));

            source.update();
            destination.update();
        }

        t.insert();
    }
}
