package ts;

import entities.AccountType;
import rdg.Account;
import rdg.AccountFinder;
import rdg.Transaction;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DeactivateAccount {
    private static final DeactivateAccount INSTANCE = new DeactivateAccount();

    public static DeactivateAccount getInstance() {
        return INSTANCE;
    }

    public void deactivateAccount(String accountNumber) throws /*todo CallException*/ SQLException {
        Account a = AccountFinder.getInstance().findByAccountNumber(accountNumber);

        if(a == null) {
            //todo ucet neexistuje
            //return prec todo
            return;
        }

        AccountType accountType = AccountType.valueOf(a.getAccountType());

        switch(accountType) {
            case CURRENT:
                if(a.getCurrentBalance().compareTo(BigDecimal.ZERO) == 0) {

                    List<Account> savingsAccounts = AccountFinder.getInstance().findCurrentSavingsAccountsByAccountId(a.getId());
                    for(Account savingsAccount: savingsAccounts) {
                        if(savingsAccount.getActive()) {
                            a.setCurrentBalance(a.getCurrentBalance().add(savingsAccount.getCurrentBalance()));
                            a.setAvailableBalance(a.getAvailableBalance().add(savingsAccount.getAvailableBalance()));

                            BigDecimal amount = savingsAccount.getAvailableBalance();
                            savingsAccount.setCurrentBalance(BigDecimal.ZERO);
                            savingsAccount.setAvailableBalance(BigDecimal.ZERO);
                            savingsAccount.setActive(false);
                            a.update();
                            savingsAccount.update();

                            Transaction t = new Transaction();
                            Timestamp now = new Timestamp(System.currentTimeMillis());

                            t.setFromId(savingsAccount.getId());
                            t.setToId(a.getCurrentAccountId());
                            t.setFromAccount(savingsAccount.getAccountNumber());
                            t.setToAccount(a.getAccountNumber());
                            t.setDatetime(now);
                            t.setType("TRANSFER");
                            t.setAmount(amount);
                            t.setCurrencyId(a.getCurrencyId());
                            t.setCompleted(true);

                            t.insert();
                        }
                    }

                    a.setActive(false);
                    a.update();
                }
                break;
            case TERM:
                if(a.getCurrentBalance().compareTo(BigDecimal.ZERO) == 0) {
                    a.setActive(false);
                    a.update();
                }
                break;
            case SAVINGS:
                Account currentAccount = AccountFinder.getInstance().findByAccountId(a.getCurrentAccountId());
                currentAccount.setCurrentBalance(currentAccount.getCurrentBalance().add(a.getCurrentBalance()));
                currentAccount.setAvailableBalance(currentAccount.getAvailableBalance().add(a.getAvailableBalance()));

                BigDecimal amount = a.getAvailableBalance();
                a.setCurrentBalance(BigDecimal.ZERO);
                a.setAvailableBalance(BigDecimal.ZERO);
                a.setActive(false);
                currentAccount.update();
                a.update();

                Transaction t = new Transaction();
                Timestamp now = new Timestamp(System.currentTimeMillis());

                t.setFromId(a.getId());
                t.setToId(currentAccount.getCurrentAccountId());
                t.setFromAccount(a.getAccountNumber());
                t.setToAccount(currentAccount.getAccountNumber());
                t.setDatetime(now);
                t.setType("TRANSFER");
                t.setAmount(amount);
                t.setCurrencyId(a.getCurrencyId());
                t.setCompleted(true);

                t.insert();
                break;
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
