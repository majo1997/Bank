package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    protected Integer id;
    protected String accountNumber;
    protected Boolean active;
    protected BigDecimal availableBalance;
    protected BigDecimal currentBalance;
    protected AccountType accountType; //todo must be used here
    protected Integer currencyId;
    protected Integer customerId;

//    id SERIAL PRIMARY KEY,
//    account_number VARCHAR(30) UNIQUE ,--uniq
//    active BOOLEAN,
//    available_balance NUMERIC,
//    current_balance NUMERIC,
//    account_type VARCHAR,--acc type
//    interest_rate NUMERIC,
//    commitment_till DATE,
//    currency_id INTEGER REFERENCES currencies,
//    customer_id INTEGER REFERENCES customers,

    public Account() {

    }

    public Account(String accountNumber) throws InvalidValueException {
        boolean loaded = loadAccountByAccountNumber(accountNumber);

        if(!loaded) {
            throw new InvalidValueException("Invalid account number!");
        }
    }


    public Account(Integer customerId, Integer currencyId) {
        this.accountNumber = uniqueRandomAccountNumber();
        this.active = true;
        this.availableBalance = new BigDecimal(0);
        this.currentBalance = new BigDecimal(0);
        this.customerId = customerId;
        this.currencyId = currencyId;
    }

    public Account(Integer id, String accountNumber, Boolean active, BigDecimal availableBalance, BigDecimal currentBalance, Integer currencyId, Integer customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.active = active;
        this.availableBalance = availableBalance;
        this.currentBalance = currentBalance;
        this.currencyId = currencyId;
        this.customerId = customerId;
    }

    public boolean loadAccountByAccountNumber(String accountNumber) {
        String sql = "SELECT * " +
                "FROM accounts " +
                "WHERE account_number = ?";

        //    id SERIAL PRIMARY KEY,
//    account_number VARCHAR(30) UNIQUE ,--uniq
//    active BOOLEAN,
//    available_balance NUMERIC,
//    current_balance NUMERIC,
//    account_type VARCHAR,--acc type
//    interest_rate NUMERIC,
//    commitment_till DATE,
//    currency_id INTEGER REFERENCES currencies,
//    customer_id INTEGER REFERENCES customers,

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Integer accountId = rs.getInt(1);
//                String accountNumber = rs.getString(2);
                Boolean active = rs.getBoolean(3);
                BigDecimal availableBalance = rs.getBigDecimal(4);
                BigDecimal currentBalance = rs.getBigDecimal(5);
                String accountTypeString = rs.getString(6);
                Integer currencyId = rs.getInt(9);
                Integer customerId = rs.getInt(10);

                this.id = accountId;
                this.active = active;
                this.availableBalance = availableBalance;
                this.currentBalance = currentBalance;
                this.accountType = AccountType.valueOf(accountTypeString);
                this.currencyId = currencyId;
                this.customerId = customerId;

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void transferMoney(BigDecimal amount) throws InvalidValueException {
        if(availableBalance.compareTo(amount) < 0) {
            throw new InvalidValueException("There is not enough money on selected account!");
        }
        availableBalance = availableBalance.subtract(amount);
    }

    public void transferMoneyTo(Account toAccount, BigDecimal amount) throws InvalidValueException {
        if(availableBalance.compareTo(amount) < 0) {
            throw new InvalidValueException("There is not enough money on selected account!");
        }
        availableBalance = availableBalance.subtract(amount);
        currentBalance = currentBalance.subtract(amount);

        BigDecimal destCurrencyAmount = null;

        if(!currencyId.equals(toAccount.getCurrencyId())) {
            CurrencyRate currencyRate = new CurrencyRate(currencyId, toAccount.getCurrencyId());
            destCurrencyAmount = currencyRate.calculateAmount(amount);
        }
        else {
            destCurrencyAmount = amount;
        }

        toAccount.availableBalance = toAccount.availableBalance.add(destCurrencyAmount);
        toAccount.currentBalance = toAccount.currentBalance.add(destCurrencyAmount);
    }

    public void subtractMoney(BigDecimal amount) {
        currentBalance = currentBalance.subtract(amount);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    protected String uniqueRandomAccountNumber() {
        //SK3386785834159015377697197684//28 - 2 = 30
        boolean unique = false;
        String generatedAccountNumber = null;

        while(!unique) {
            generatedAccountNumber = generateAccountNumber();
            unique = uniqueAccountNumber(generatedAccountNumber);
        }

        return generatedAccountNumber;
    }

    private String generateAccountNumber() {
//        private final static String CHARACTERS = "0123456789";

        SecureRandom random = new SecureRandom();
        String CHARACTERS = "0123456789";

        StringBuilder sb = new StringBuilder();
        sb.append("SK");

        for(int i = 0; i < 28; i++) {//todo constant 28
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }

    private Boolean uniqueAccountNumber(String accountNumber) {
        String sql = "SELECT * " +
                "FROM accounts " +
                "WHERE account_number = ?";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Integer getAccountId() {
        return id;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void update() {
        String sql = "UPDATE accounts " +
                "SET active = ?, available_balance = ?, current_balance = ? " +
                "WHERE id = ?";

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, active);
            ps.setBigDecimal(2, availableBalance);
            ps.setBigDecimal(3, currentBalance);
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
