package ts;

import main.DbContext;
import rdg.AccountType;
import rdg.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MonthlyClosing {
    private static final MonthlyClosing INSTANCE = new MonthlyClosing();

    public static MonthlyClosing getInstance() {
        return INSTANCE;
    }

    public void monthlyClosing() throws SQLException {
        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        DbContext.getConnection().setAutoCommit(false);

        final Integer PRECISION = 10;

        List<Customer> customers = CustomerFinder.getInstance().findAll();

        StringBuilder sb = new StringBuilder();

        for(Customer c: customers) {
            AccountStatement as = new AccountStatement();

            List<Account> savingsAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(c.getCustomerId(), AccountType.SAVINGS);
            for(Account a: savingsAccounts) {
                BigDecimal currentBalanceInterest = a.getCurrentBalance().multiply(a.getInterestRate().divide(new BigDecimal(100), PRECISION, RoundingMode.HALF_UP));
                a.setCurrentBalance(a.getCurrentBalance().add(currentBalanceInterest));
                BigDecimal availableBalanceInterest = a.getAvailableBalance().multiply(a.getInterestRate().divide(new BigDecimal(100), PRECISION, RoundingMode.HALF_UP));
                a.setAvailableBalance(a.getAvailableBalance().add(availableBalanceInterest));

                sb.append(("Earned from savings account " + a.getAccountNumber() + ": " + currentBalanceInterest.toString() + "\n"));

                Transaction t = new Transaction();
                Timestamp now = new Timestamp(System.currentTimeMillis());

                t.setFromId(a.getId());
                t.setToId(a.getId());
                t.setFromAccount(a.getAccountNumber());
                t.setToAccount(a.getAccountNumber());
                t.setDatetime(now);
                t.setType("EARNING");
                t.setAmount(currentBalanceInterest);
                t.setCurrencyId(a.getCurrencyId());
                t.setCompleted(true);

                t.insert();

                a.update();
            }

            List<Account> termAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(c.getCustomerId(), AccountType.TERM);

            for(Account a: termAccounts) {
                if(a.getCommitmentTill().getTime() < System.currentTimeMillis() && a.getCurrentBalance().compareTo(BigDecimal.ZERO) == 0) {
                    a.setActive(false);
                }
                else {
                    BigDecimal currentBalanceInterest = a.getCurrentBalance().multiply(a.getInterestRate().divide(new BigDecimal(100), PRECISION, RoundingMode.HALF_UP));
                    a.setCurrentBalance(a.getCurrentBalance().add(currentBalanceInterest));
                    BigDecimal availableBalanceInterest = a.getAvailableBalance().multiply(a.getInterestRate().divide(new BigDecimal(100), PRECISION, RoundingMode.HALF_UP));
                    a.setAvailableBalance(a.getAvailableBalance().add(availableBalanceInterest));

                    Transaction t = new Transaction();
                    Timestamp now = new Timestamp(System.currentTimeMillis());

                    t.setFromId(a.getId());
                    t.setToId(a.getId());
                    t.setFromAccount(a.getAccountNumber());
                    t.setToAccount(a.getAccountNumber());
                    t.setDatetime(now);
                    t.setType("EARNING");
                    t.setAmount(currentBalanceInterest);
                    t.setCurrencyId(a.getCurrencyId());
                    t.setCompleted(true);

                    t.insert();

                    sb.append(("Earned from savings account " + a.getAccountNumber() + ": " + currentBalanceInterest.toString() + "\n"));
                }

                a.update();
            }

            String statement = sb.toString();

            Timestamp now = new Timestamp(System.currentTimeMillis());

            as.setCustomerId(c.getCustomerId());
            as.setStatement(statement);
            as.setDatetime(now);

            as.insert();
        }

        DbContext.getConnection().commit();
        DbContext.getConnection().setAutoCommit(true);
    }
}
