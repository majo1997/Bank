package rdg;

import entities.AccountType;
import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountFinder {
    private static final AccountFinder INSTANCE = new AccountFinder();

    public static AccountFinder getInstance() {
        return INSTANCE;
    }

    public Account findByAccountId(Integer accountId) throws SQLException {

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account a = new Account();

                    a.setId(rs.getInt("id"));
                    a.setAccountNumber(rs.getString("account_number"));
                    a.setActive(rs.getBoolean("active"));
                    a.setAvailableBalance(rs.getBigDecimal("available_balance"));
                    a.setCurrentBalance(rs.getBigDecimal("current_balance"));
                    a.setAccountType(rs.getString("account_type"));
                    a.setCurrencyId(rs.getInt("currency_id"));
                    a.setCustomerId(rs.getInt("customer_id"));

                    a.setInterestRate(rs.getBigDecimal("interest_rate"));
                    a.setCommitmentTill(rs.getDate("commitment_till"));

                    a.setCurrentAccountId(rs.getInt("current_account_id"));

                    if (rs.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return a;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Account> findCurrentSavingsAccountsByAccountId(Integer accountId) throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE current_account_id = ?")) {
            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {

                List<Account> elements = new ArrayList<>();

                while (rs.next()) {
                    Account a = new Account();

                    a.setId(rs.getInt("id"));
                    a.setAccountNumber(rs.getString("account_number"));
                    a.setActive(rs.getBoolean("active"));
                    a.setAvailableBalance(rs.getBigDecimal("available_balance"));
                    a.setCurrentBalance(rs.getBigDecimal("current_balance"));
                    a.setAccountType(rs.getString("account_type"));
                    a.setCurrencyId(rs.getInt("currency_id"));
                    a.setCustomerId(rs.getInt("customer_id"));

                    a.setInterestRate(rs.getBigDecimal("interest_rate"));
                    a.setCommitmentTill(rs.getDate("commitment_till"));

                    a.setCurrentAccountId(rs.getInt("current_account_id"));

                    elements.add(a);
                }

                return elements;
            }
        }
    }

    public Account findByAccountNumber(String accountNumber) throws SQLException {

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE account_number = ?")) {
            ps.setString(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account a = new Account();

                    a.setId(rs.getInt("id"));
                    a.setAccountNumber(rs.getString("account_number"));
                    a.setActive(rs.getBoolean("active"));
                    a.setAvailableBalance(rs.getBigDecimal("available_balance"));
                    a.setCurrentBalance(rs.getBigDecimal("current_balance"));
                    a.setAccountType(rs.getString("account_type"));
                    a.setCurrencyId(rs.getInt("currency_id"));
                    a.setCustomerId(rs.getInt("customer_id"));

                    a.setInterestRate(rs.getBigDecimal("interest_rate"));
                    a.setCommitmentTill(rs.getDate("commitment_till"));

                    a.setCurrentAccountId(rs.getInt("current_account_id"));

                    if (rs.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return a;
                } else {
                    return null;
                }
            }
        }
    }

    public Account findByAccountNumberAndType(String accountNumber, AccountType accountType) throws SQLException {

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND account_type = ?")) {
            ps.setString(1, accountNumber);
            ps.setString(2, accountType.name());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account a = new Account();

                    a.setId(rs.getInt("id"));
                    a.setAccountNumber(rs.getString("account_number"));
                    a.setActive(rs.getBoolean("active"));
                    a.setAvailableBalance(rs.getBigDecimal("available_balance"));
                    a.setCurrentBalance(rs.getBigDecimal("current_balance"));
                    a.setAccountType(rs.getString("account_type"));
                    a.setCurrencyId(rs.getInt("currency_id"));
                    a.setCustomerId(rs.getInt("customer_id"));

                    a.setInterestRate(rs.getBigDecimal("interest_rate"));
                    a.setCommitmentTill(rs.getDate("commitment_till"));

                    a.setCurrentAccountId(rs.getInt("current_account_id"));

                    if (rs.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return a;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Account> findAllAccountsByCustomerIdAndType(Integer customerId, AccountType accountType) throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE customer_id = ? AND account_type = ?")) {
            ps.setInt(1, customerId);
            ps.setString(2, accountType.name());

            try (ResultSet rs = ps.executeQuery()) {

                List<Account> elements = new ArrayList<>();

                while (rs.next()) {
                    Account a = new Account();

                    a.setId(rs.getInt("id"));
                    a.setAccountNumber(rs.getString("account_number"));
                    a.setActive(rs.getBoolean("active"));
                    a.setAvailableBalance(rs.getBigDecimal("available_balance"));
                    a.setCurrentBalance(rs.getBigDecimal("current_balance"));
                    a.setAccountType(rs.getString("account_type"));
                    a.setCurrencyId(rs.getInt("currency_id"));
                    a.setCustomerId(rs.getInt("customer_id"));

                    a.setInterestRate(rs.getBigDecimal("interest_rate"));
                    a.setCommitmentTill(rs.getDate("commitment_till"));

                    a.setCurrentAccountId(rs.getInt("current_account_id"));

                    elements.add(a);
                }

                return elements;
            }
        }
    }


}
