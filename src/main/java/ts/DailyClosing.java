package ts;

import main.DbContext;
import rdg.Account;
import rdg.AccountFinder;
import rdg.Transaction;
import rdg.TransactionFinder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DailyClosing {
    private static final DailyClosing INSTANCE = new DailyClosing();

    public static DailyClosing getInstance() {
        return INSTANCE;
    }

    public void dailyClosing() throws SQLException {
        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        DbContext.getConnection().setAutoCommit(false);

        List<Transaction> elements = TransactionFinder.getInstance().findUnfinishedTransactions();

        for(Transaction t: elements) {
            Account a = AccountFinder.getInstance().findByAccountId(t.getFromId());

            a.setCurrentBalance(a.getCurrentBalance().subtract(t.getAmount()));
            a.update();
            t.setCompleted(true);
            t.update();
        }

        DbContext.getConnection().commit();
        DbContext.getConnection().setAutoCommit(true);
    }
}
