package ts;

import entities.AccountType;
import rdg.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MonthlyClosing {
    private static final MonthlyClosing INSTANCE = new MonthlyClosing();

    public static MonthlyClosing getInstance() {
        return INSTANCE;
    }

    public void monthlyClosing() throws SQLException {
        //todo poplatky
        //todo transakcie ku vsetkemu
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


//        if (duration <= 0) {
//            throw new IllegalArgumentException("duration must be greater than 0");
//        }
//
//        int callId = CallService.getInstance().startCall(sourceNumber, destinationNumber, STEP_DURATION);
//
//        System.out.print("LOG: The call has started. Call id: ");
//        System.out.println(callId);
//
//        Thread.sleep(STEP_SLEEP);
//        System.out.println("LOG: One second has passed");
//
//        for (int i = 1; i < duration; ++i) {
//
//            CallService.getInstance().makeCallStep(callId, STEP_DURATION);
//
//            Thread.sleep(STEP_SLEEP);
//            System.out.println("LOG: One second has passed");
//        }
//
//        System.out.println("LOG: The call ended.");
    }
}
