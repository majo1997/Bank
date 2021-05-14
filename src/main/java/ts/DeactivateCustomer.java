package ts;

import entities.AccountType;
import rdg.Account;
import rdg.AccountFinder;

import java.sql.SQLException;
import java.util.List;

public class DeactivateCustomer {
    private static final DeactivateCustomer INSTANCE = new DeactivateCustomer();

    public static DeactivateCustomer getInstance() {
        return INSTANCE;
    }

    public void deactivateAllCustomerAccounts(Integer customerId) throws /*todo CallException*/ SQLException/*todo , InterruptedException*/ {
        List<Account> savingsAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.SAVINGS);
        for(Account a: savingsAccounts) {
            try {
                DeactivateAccount.getInstance().deactivateAccount(a.getAccountNumber());
            }
            catch(SQLException e) {
                //todo rollback + return
            }
        }

        List<Account> termAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.TERM);
        for(Account a: termAccounts) {
            try {
                DeactivateAccount.getInstance().deactivateAccount(a.getAccountNumber());
            }
            catch(SQLException e) {
                //todo rollback
            }
        }


        List<Account> currentAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.CURRENT);
        for(Account a: currentAccounts) {
            try {
                DeactivateAccount.getInstance().deactivateAccount(a.getAccountNumber());
            }
            catch(SQLException e) {
                //todo rollback
            }
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
