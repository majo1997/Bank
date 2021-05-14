package rdg;

import main.DbContext;

import java.math.BigDecimal;
import java.sql.*;

public class Account {

    private Integer id;
    private String accountNumber;
    private Boolean active;
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private String accountType;
    private Integer currencyId;
    private Integer customerId;

    private BigDecimal interestRate;
    private Date commitmentTill;
    private Integer currentAccountId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getCommitmentTill() {
        return commitmentTill;
    }

    public void setCommitmentTill(Date commitmentTill) {
        this.commitmentTill = commitmentTill;
    }

    public Integer getCurrentAccountId() {
        return currentAccountId;
    }

    public void setCurrentAccountId(Integer currentAccountId) {
        this.currentAccountId = currentAccountId;
    }

    public void update() throws SQLException {

        if(id == null) {
            throw new IllegalStateException("Customer ID is not set");
        }

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET active = ?, available_balance = ?, current_balance = ? WHERE id = ?")) {
            ps.setBoolean(1, active);
            ps.setBigDecimal(2, availableBalance);
            ps.setBigDecimal(3, currentBalance);
            ps.setInt(4, id);

            ps.executeUpdate();
        }
    }

    public void insert() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("INSERT INTO accounts (account_number, active, available_balance, current_balance, account_type, interest_rate, commitment_till, currency_id, customer_id, current_account_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, accountNumber);
            ps.setBoolean(2, active);
            ps.setBigDecimal(3, availableBalance);
            ps.setBigDecimal(4, currentBalance);
            ps.setString(5, accountType);

            if(accountType.equals("TERM") || accountType.equals("SAVINGS")) {
                ps.setBigDecimal(6, interestRate);
            }
            else {
                ps.setNull(6, Types.NUMERIC);
            }

            if(accountType.equals("TERM")) {
                ps.setDate(7, commitmentTill);
            }
            else {
                ps.setNull(7, Types.DATE);
            }

            ps.setInt(8, currencyId);
            ps.setInt(9, customerId);

            if(accountType.equals("SAVINGS")) {
                ps.setInt(10, currentAccountId);
            }
            else {
                ps.setNull(10, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                id = rs.getInt(1);
            }
        }


    }

}
