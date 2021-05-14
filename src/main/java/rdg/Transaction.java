package rdg;


import main.DbContext;

import java.math.BigDecimal;
import java.sql.*;

public class Transaction  {
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private String fromAccount; //todo means account not in our db
    private String toAccount;   //todo same as above
    private Timestamp datetime;
    private boolean completed;
    private String type;
    private BigDecimal amount;
    private Integer currencyId; //fromAccount currency, mainly used when fromAccount is not from our bank

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void update() throws SQLException {
        if(id == null) {
            throw new IllegalStateException("Transaction ID is not set");
        }

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("UPDATE transactions SET completed = ? WHERE id = ?")) {
            ps.setBoolean(1, completed);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }

    public void insert() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("INSERT INTO transactions (from_id, to_id, from_account, to_account, datetime, completed, type, amount, currency_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, fromId);

            if(toId != null) {
                ps.setInt(2, toId);
            }
            else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setString(3, fromAccount);
            ps.setString(4, toAccount);
            ps.setTimestamp(5, datetime);
            ps.setBoolean(6, completed);
            ps.setString(7, type);
            ps.setBigDecimal(8, amount);
            ps.setInt(9, currencyId);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                id = rs.getInt(1);
            }
        }
    }

}
