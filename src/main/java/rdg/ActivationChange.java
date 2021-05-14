package rdg;


import main.DbContext;

import java.sql.*;

public class ActivationChange {
    private Integer id;
    private Boolean active;
    private Timestamp datetime;
    private Integer customerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void insert() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("INSERT INTO activation_changes (active, datetime, customer_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, active);
            ps.setTimestamp(2, datetime);
            ps.setInt(3, customerId);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                id = rs.getInt(1);
            }
        }
    }

}
