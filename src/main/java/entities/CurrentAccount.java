package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentAccount extends Account {
//    private final AccountType accountType = AccountType.CURRENT;

    public CurrentAccount(Integer accountId) throws InvalidValueException {
        super();
        accountType = AccountType.CURRENT;

        boolean loaded = loadCurrentAccountById(accountId);

        if(!loaded) {
            throw new InvalidValueException("Invalid account ID!");
        }
    }

    public CurrentAccount(Integer customerId, Integer currencyId) {
        super(customerId, currencyId);
        accountType = AccountType.CURRENT;
    }

    public CurrentAccount(Integer accountId, String accountNumber, Boolean active, BigDecimal availableBalance, BigDecimal currentBalance, Integer currencyId, Integer customerId) {
        super(accountId, accountNumber, active, availableBalance, currentBalance, currencyId, customerId);
        accountType = AccountType.CURRENT;
    }

    private boolean loadCurrentAccountById(Integer accountId) {
        String sql = "SELECT * " +
                "FROM accounts " +
                "WHERE id = ? AND account_type = ?";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, accountId);
            ps.setString(2, accountType.name());

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String accountNumber = rs.getString(2);
                Boolean active = rs.getBoolean(3);
                BigDecimal availableBalance = rs.getBigDecimal(4);
                BigDecimal currentBalance = rs.getBigDecimal(5);
                Integer currencyId = rs.getInt(10);
                Integer customerId = rs.getInt(11);

                this.id = accountId;
                this.accountNumber = accountNumber;
                this.active = active;
                this.availableBalance = availableBalance;
                this.currentBalance = currentBalance;
                this.customerId = customerId;
                this.currencyId =currencyId;

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


//    public void update() {
//        String sql = "UPDATE accounts " +
//                "SET active = ?, available_balance = ?, current_balance = ? " +
//                "WHERE id = ?";
//
//        Connection conn = DbContext.getConnection();
//
//        try(PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setBoolean(1, active);
//            ps.setBigDecimal(2, availableBalance);
//            ps.setBigDecimal(3, currentBalance);
//            ps.setInt(4, id);
//
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void activate() {
        if(active) {
            System.out.println("This account is active.");
            return;
        }
        active = true;

        update();
        System.out.println("Account has been activated");
    }

    //todo finish whole class...
    public void insert() {
        String sql = "INSERT INTO accounts (account_number, active, available_balance, current_balance, account_type, currency_id, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection conn = DbContext.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            ps.setBoolean(2, active);
            ps.setBigDecimal(3, availableBalance);
            ps.setBigDecimal(4, currentBalance);
            ps.setString(5, accountType.name());
            ps.setInt(6, currencyId);
            ps.setInt(7, customerId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {
        return id + ". " + accountType.name() + ", " + active + ", " + availableBalance + "/" + currentBalance + ", " + currencyId + ", " + customerId;
    }
}
