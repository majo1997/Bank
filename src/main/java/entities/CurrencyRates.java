package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRates {
    private final List<CurrencyRate> currencyRates;

    public CurrencyRates() {
        currencyRates = new ArrayList<>();
        loadCurrencyRates();
    }

    public void loadCurrencyRates() {
        String sql = "SELECT * FROM currency_rates";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer currencyRateId = rs.getInt(1);
                Integer fromCurrencyId = rs.getInt(2);
                Integer toCurrencyId = rs.getInt(3);
                BigDecimal rate = rs.getBigDecimal(4);

                CurrencyRate currencyRate = new CurrencyRate(currencyRateId, fromCurrencyId, toCurrencyId, rate);
                currencyRates.add(currencyRate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for(CurrencyRate currencyRate: currencyRates) {
            System.out.println(currencyRate);
        }
    }

}
