package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Accounts {
    private final List<Account> accounts;

    public Accounts(Integer customerId, AccountType accountType) {
        accounts = new ArrayList<>();

        switch(accountType) {
            case CURRENT:
                loadCurrentAccounts(customerId);
                break;
            case SAVINGS:
                loadSavingsAccounts(customerId);
                break;
            case TERM:
                loadTermAccounts(customerId);
                break;
        }
    }

    private void loadCurrentAccounts(Integer customerId) {//todo add enum as param and wont be needed these things and change meth name to loadAccountsByType
        String sql = "SELECT * FROM accounts WHERE account_type = ?";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, AccountType.CURRENT.name());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer accountId = rs.getInt(1);
                String accountNumber = rs.getString(2);
                Boolean active = rs.getBoolean(3);
                BigDecimal availableBalance = rs.getBigDecimal(4);
                BigDecimal currentBalance = rs.getBigDecimal(5);
                String accountType = rs.getString(6);
//                BigDecimal interestRate = rs.getBigDecimal(7)
//                Date commitmentTill = rs.getDate(8);
                Integer currencyId = rs.getInt(9);
//                Integer customerId = rs.getInt(10);
//                Integer currentAccountId = rs.getInt(11);

                CurrentAccount currentAccount = new CurrentAccount(accountId, accountNumber, active, availableBalance, currentBalance, currencyId, customerId);
                accounts.add(currentAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSavingsAccounts(Integer customerId) {

    }

    private void loadTermAccounts(Integer customerId) {

    }

    public void print() {
        for(Account account: accounts) {
            System.out.println(account);
        }
    }

}
