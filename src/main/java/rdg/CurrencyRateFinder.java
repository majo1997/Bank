package rdg;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRateFinder {
    private static final CurrencyRateFinder INSTANCE = new CurrencyRateFinder();

    /**
     * @return instance of currency rate finder
     * */
    public static CurrencyRateFinder getInstance() {
        return INSTANCE;
    }

    /**
     * @param fromId source currency ID
     * @param toId destination currency ID
     *
     * @return currency rate by specified ids
     * */
    public CurrencyRate findCurrencyRateByCurrencyIds(Integer fromId, Integer toId) throws SQLException {

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM currency_rates WHERE from_id = ? AND to_id = ?")) {
            ps.setInt(1, fromId);
            ps.setInt(2, toId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CurrencyRate cr = new CurrencyRate();

                    cr.setId(rs.getInt("id"));
                    cr.setFromCurrencyId(rs.getInt("from_id"));
                    cr.setToCurrencyId(rs.getInt("to_id"));
                    cr.setRate(rs.getBigDecimal("rate"));

                    if (rs.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return cr;
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * @return all currency rates list
     * */
    public List<CurrencyRate> findAllCurrencyRates() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM currency_rates")) {

            try (ResultSet rs = ps.executeQuery()) {

                List<CurrencyRate> elements = new ArrayList<>();

                while (rs.next()) {
                    CurrencyRate cr = new CurrencyRate();

                    cr.setId(rs.getInt("id"));
                    cr.setFromCurrencyId(rs.getInt("from_id"));
                    cr.setToCurrencyId(rs.getInt("to_id"));
                    cr.setRate(rs.getBigDecimal("rate"));

                    elements.add(cr);
                }

                return elements;
            }
        }
    }
}
