package ts;

import main.DbContext;
import rdg.AccountType;
import rdg.Account;
import rdg.AccountFinder;
import rdg.ActivationChange;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DeactivateCustomer {
    private static final DeactivateCustomer INSTANCE = new DeactivateCustomer();

    public static DeactivateCustomer getInstance() {
        return INSTANCE;
    }

    public void deactivateAllCustomerAccounts(Integer customerId) throws SQLException, DeactivationException {
        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        DbContext.getConnection().setAutoCommit(false);

        List<Account> savingsAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.SAVINGS);
        for(Account a: savingsAccounts) {
            try {
                DeactivateAccount.getInstance().deactivateAccount(a.getAccountNumber());
            }
            catch(SQLException | DeactivationException e) {
                DbContext.getConnection().rollback();
                DbContext.getConnection().setAutoCommit(true);
                throw new DeactivationException("One of the savings accounts can not be deactivated");
            }
        }

        List<Account> termAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.TERM);
        for(Account a: termAccounts) {
            try {
                DeactivateAccount.getInstance().deactivateAccount(a.getAccountNumber());
            }
            catch(SQLException | DeactivationException e) {
                DbContext.getConnection().rollback();
                DbContext.getConnection().setAutoCommit(true);
                throw new DeactivationException("One of the term accounts can not be deactivated");
            }
        }


        List<Account> currentAccounts = AccountFinder.getInstance().findAllAccountsByCustomerIdAndType(customerId, AccountType.CURRENT);
        for(Account a: currentAccounts) {
            try {
                DeactivateAccount.getInstance().deactivateAccount(a.getAccountNumber());
            }
            catch(SQLException | DeactivationException e) {
                DbContext.getConnection().rollback();
                DbContext.getConnection().setAutoCommit(true);
                throw new DeactivationException("One of the current accounts can not be deactivated");
            }
        }

        ActivationChange ac = new ActivationChange();

        ac.setActive(false);
        ac.setCustomerId(customerId);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        ac.setDatetime(now);

        ac.insert();

        DbContext.getConnection().commit();
        DbContext.getConnection().setAutoCommit(true);
    }
}
