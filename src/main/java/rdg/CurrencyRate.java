package rdg;

import main.DbContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrencyRate {
    private final Integer PRECISION = 10;

    private Integer id;
    private Integer fromCurrencyId;
    private Integer toCurrencyId;
    private BigDecimal rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromCurrencyId() {
        return fromCurrencyId;
    }

    public void setFromCurrencyId(Integer fromCurrencyId) {
        this.fromCurrencyId = fromCurrencyId;
    }

    public Integer getToCurrencyId() {
        return toCurrencyId;
    }

    public void setToCurrencyId(Integer toCurrencyId) {
        this.toCurrencyId = toCurrencyId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void insert() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("INSERT INTO currency_rates (from_id, to_id, rate) VALUES (?, ?, ?), (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, fromCurrencyId);
            ps.setInt(2, toCurrencyId);
            ps.setBigDecimal(3, rate);

            BigDecimal invertedRate = (new BigDecimal(1)).divide(rate, PRECISION, RoundingMode.HALF_UP);
            ps.setInt(4, toCurrencyId);
            ps.setInt(5, fromCurrencyId);
            ps.setBigDecimal(6, invertedRate);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                id = rs.getInt(1);
            }
        }

    }

    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Currency rate is null");
        }

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("UPDATE currency_rates SET rate = ? WHERE (from_id = ? AND to_id = ?); UPDATE currency_rates SET rate = ? WHERE (to_id = ? AND from_id = ?);")) {
            BigDecimal invertedRate = (new BigDecimal(1)).divide(rate, PRECISION, RoundingMode.HALF_UP);
            ps.setBigDecimal(1, rate);
            ps.setInt(2, fromCurrencyId);
            ps.setInt(3, toCurrencyId);
            ps.setBigDecimal(4, invertedRate);
            ps.setInt(5, fromCurrencyId);
            ps.setInt(6, toCurrencyId);

            ps.executeUpdate();
        }

    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Currency rate is null");
        }

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("DELETE FROM currency_rates WHERE (from_id = ? AND to_id = ?) OR (to_id = ? AND from_id = ?)")) {
            ps.setInt(1, fromCurrencyId);
            ps.setInt(2, toCurrencyId);
            ps.setInt(3, fromCurrencyId);
            ps.setInt(4, toCurrencyId);

            ps.executeUpdate();
        }
    }
}
