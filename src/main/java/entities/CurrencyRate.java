package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRate {
    private final Integer PRECISION = 10;

    private Integer id;
    private Integer fromCurrencyId;
    private Integer toCurrencyId;
    private BigDecimal rate;

    public CurrencyRate(Integer fromCurrencyId, Integer toCurrencyId) throws InvalidValueException {
        boolean loaded = loadCurrencyRate(fromCurrencyId, toCurrencyId);

        if(!loaded) {
            throw new InvalidValueException("Invalid currency rate IDs!");
        }
    }

    public CurrencyRate(Integer fromCurrencyId, Integer toCurrencyId, BigDecimal rate) {
        this.fromCurrencyId = fromCurrencyId;
        this.toCurrencyId = toCurrencyId;
        this.rate = rate;
    }

    public CurrencyRate(Integer currencyRateId, Integer fromCurrencyId, Integer toCurrencyId, BigDecimal rate) {
        this.id = currencyRateId;
        this.fromCurrencyId = fromCurrencyId;
        this.toCurrencyId = toCurrencyId;
        this.rate = rate;
    }

    private boolean loadCurrencyRate(Integer fromCurrencyId, Integer toCurrencyId) {
        String sql = "SELECT * " +
                "FROM currency_rates " +
                "WHERE from_id = ? AND to_id = ?";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, fromCurrencyId);
            ps.setInt(2, toCurrencyId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Integer id = rs.getInt(1);
                BigDecimal rate = rs.getBigDecimal(4);

                this.id = id;
                this.fromCurrencyId = fromCurrencyId;
                this.toCurrencyId = toCurrencyId;
                this.rate = rate;

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void changeRate(BigDecimal newRate) {
        this.rate = newRate;
    }

    public void insert() {
        //todo ak existuje tak vynimku vyhod??
        String sql = "INSERT INTO currency_rates (from_id, to_id, rate) VALUES (?, ?, ?); " +
                "INSERT INTO currency_rates (from_id, to_id, rate) VALUES (?, ?, ?); ";

        Connection conn = DbContext.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fromCurrencyId);
            ps.setInt(2, toCurrencyId);
            ps.setBigDecimal(3, rate);

            BigDecimal invertedRate = (new BigDecimal(1)).divide(rate, PRECISION, RoundingMode.HALF_UP);
            ps.setInt(4, toCurrencyId);
            ps.setInt(5, fromCurrencyId);
            ps.setBigDecimal(6, invertedRate);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String sql = "UPDATE currency_rates " +
                "SET rate = ?" +
                "WHERE (from_id = ? AND to_id = ?);" +
                "UPDATE currency_rates SET rate = ? WHERE (to_id = ? AND from_id = ?);";

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            BigDecimal invertedRate = (new BigDecimal(1)).divide(rate, PRECISION, RoundingMode.HALF_UP);
            ps.setBigDecimal(1, rate);
            ps.setInt(2, fromCurrencyId);
            ps.setInt(3, toCurrencyId);
            ps.setBigDecimal(4, invertedRate);
            ps.setInt(5, fromCurrencyId);
            ps.setInt(6, toCurrencyId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        String sql = "DELETE FROM currency_rates WHERE (from_id = ? AND to_id = ?) OR (to_id = ? AND from_id = ?)";

        Connection conn = DbContext.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fromCurrencyId);
            ps.setInt(2, toCurrencyId);
            ps.setInt(3, fromCurrencyId);
            ps.setInt(4, toCurrencyId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return id + ". " + fromCurrencyId + " - " + toCurrencyId + ": " + rate;
    }

    public BigDecimal calculateAmount(BigDecimal amount) {
        return amount.multiply(rate);
    }
}
