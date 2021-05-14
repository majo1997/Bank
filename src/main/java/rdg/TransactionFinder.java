package rdg;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionFinder {
    private static final TransactionFinder INSTANCE = new TransactionFinder();

    public static TransactionFinder getInstance() {
        return INSTANCE;
    }

//    public Transaction findTransactionById(Strsing ssss) throws SQLException {
//
//        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE account_number = ?")) {
//            ps.setString(1, accountNumber);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    Account a = new Account();
//
//                    a.setId(rs.getInt("id"));
//                    a.setAccountNumber(rs.getString("account_number"));
//                    a.setActive(rs.getBoolean("active"));
//                    a.setAvailableBalance(rs.getBigDecimal("available_balance"));
//                    a.setCurrentBalance(rs.getBigDecimal("current_balance"));
//                    a.setAccountType(rs.getString("account_type"));
//                    a.setCurrencyId(rs.getInt("currency_id"));
//                    a.setCustomerId(rs.getInt("customer_id"));
//
//                    a.setInterestRate(rs.getBigDecimal("interest_rate"));
//                    a.setCommitmentTill(rs.getDate("commitment_till"));
//
//                    a.setCurrentAccountId(rs.getInt("currency_account_id"));
//
//                    if (rs.next()) {
//                        throw new RuntimeException("More than one row was returned");
//                    }
//
//                    return a;
//                } else {
//                    return null;
//                }
//            }
//        }
//    }

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

    //todo remove??
//    public List<Transaction> findAllTransactions() throws SQLException {
//        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM transactions")) {
//
//            try (ResultSet rs = ps.executeQuery()) {
//
//                List<Transaction> elements = new ArrayList<>();
//
//                while (rs.next()) {
//                    Transaction t = new Transaction();
//
//                    t.setId(rs.getInt("id"));
//                    t.setFromId(rs.getInt("from_id"));
//                    t.setToId(rs.getInt("to_id"));
//                    t.setFromAccount(rs.getString("from_account"));
//                    t.setToAccount(rs.getString("to_account"));
//                    t.setDatetime(rs.getTimestamp("datetime"));
//                    t.setCompleted(rs.getBoolean("completed"));
//                    t.setType(rs.getString("type"));
//                    t.setAmount(rs.getBigDecimal("amount"));
//                    t.setCurrencyId(rs.getInt("currency_id"));
//
//                    elements.add(t);
//                }
//
//                return elements;
//            }
//        }
//    }

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
//todo check locking transactions
}
