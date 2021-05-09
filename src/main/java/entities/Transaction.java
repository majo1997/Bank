package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.sql.*;

public class Transaction {
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private String fromAccount; //todo means account not in our db
    private String toAccount;   //todo same as above
    private Timestamp datetime;
    private boolean completed;
    private TransactionType type;
    private BigDecimal amount;
    private Integer currencyId; //fromAccount currency, mainly used when fromAccount is not from our bank

    public Transaction(Integer fromId, Integer toId, String fromAccount, String toAccount, BigDecimal amount, Integer currencyId) {
        this.fromId = fromId;
        this.toId = toId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;

        this.completed = (toId != null) ? true : false;
        this.type = TransactionType.TRANSFER;
        this.amount = amount;
        this.currencyId = currencyId;

    }

    public void insert() {
        String sql = "INSERT INTO transactions (from_id, to_id, from_account, to_account, datetime, completed, type, amount, currency_id) " +
                "VALUES (?, ?, ?, ?, current_timestamp, ?, ?, ?, ?); ";

        Connection conn = DbContext.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fromId);
            if(toId == null) {
                ps.setNull(2, Types.INTEGER);
            }
            else {
                ps.setInt(2, toId);
            }

            ps.setString(3, fromAccount);
            ps.setString(4, toAccount);
            ps.setBoolean(5, completed);
            ps.setString(6, type.name());
            ps.setBigDecimal(7, amount);
            ps.setInt(8, currencyId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction(Integer transactionId,
                       Integer fromId,
                       Integer toId,
                       String fromAccount,
                       String toAccount,
                       Timestamp datetime,
                       Boolean completed,
                       TransactionType type,
                       BigDecimal amount,
                       Integer currencyId) {
        this.id = transactionId;
        this.fromId = fromId;
        this.toId = toId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.datetime = datetime;
        this.completed = completed;
        this.type = type;
        this.amount = amount;
        this.currencyId = currencyId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public void update() {
        String sql = "UPDATE transactions " +
                "SET completed = ? " +
                "WHERE id = ?";

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, completed);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return id + ", " + fromId + ", " + toId + ", " + fromAccount + '\'' + ", " + toAccount + '\'' + ", " + datetime
                + ", " + completed + ", " + type + ", " + amount + ", " + currencyId;
    }
}
