package rdg;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionFinder {
    private static final TransactionFinder INSTANCE = new TransactionFinder();

    /**
     * @return instance of transaction finder
     * */
    public static TransactionFinder getInstance() {
        return INSTANCE;
    }

    /**
     * @param accountNumber account number
     * @return list of transactions on account with entered account number
     * */
    public List<Transaction> findAllTransactionsByAccountNumber(String accountNumber) throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM transactions WHERE to_account = ? OR from_account = ?")) {
            ps.setString(1, accountNumber);
            ps.setString(2, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {

                List<Transaction> elements = new ArrayList<>();

                while (rs.next()) {
                    Transaction t = new Transaction();

                    t.setId(rs.getInt("id"));
                    t.setFromId(rs.getInt("from_id"));
                    t.setToId(rs.getInt("to_id"));
                    t.setFromAccount(rs.getString("from_account"));
                    t.setToAccount(rs.getString("to_account"));
                    t.setDatetime(rs.getTimestamp("datetime"));
                    t.setCompleted(rs.getBoolean("completed"));
                    t.setType(rs.getString("type"));
                    t.setAmount(rs.getBigDecimal("amount"));
                    t.setCurrencyId(rs.getInt("currency_id"));

                    elements.add(t);
                }

                return elements;
            }
        }
    }

    /**
     * @return list of not completed transactions
     * */
    public List<Transaction> findUnfinishedTransactions() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM transactions WHERE completed = FALSE")) {

            try (ResultSet rs = ps.executeQuery()) {

                List<Transaction> elements = new ArrayList<>();

                while (rs.next()) {
                    Transaction t = new Transaction();

                    t.setId(rs.getInt("id"));
                    t.setFromId(rs.getInt("from_id"));
                    t.setToId(rs.getInt("to_id"));
                    t.setFromAccount(rs.getString("from_account"));
                    t.setToAccount(rs.getString("to_account"));
                    t.setDatetime(rs.getTimestamp("datetime"));
                    t.setCompleted(rs.getBoolean("completed"));
                    t.setType(rs.getString("type"));
                    t.setAmount(rs.getBigDecimal("amount"));
                    t.setCurrencyId(rs.getInt("currency_id"));

                    elements.add(t);
                }

                return elements;
            }
        }
    }
}
