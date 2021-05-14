package entities;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReacquiredCustomerStatisticFinder {
    private static final ReacquiredCustomerStatisticFinder INSTANCE = new ReacquiredCustomerStatisticFinder();

    public static ReacquiredCustomerStatisticFinder getInstance() {
        return INSTANCE;
    }

    private ReacquiredCustomerStatisticFinder() { }

    public List<ReacquiredCustomerStatistic> findAll() throws SQLException {

        //todo add also years and ns where it is 0...
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement(
            "SELECT " +
                    "EXTRACT(year FROM datetime) AS y, " +
                    "(EXTRACT(years FROM justify_interval(datetime - (" +
                        "SELECT datetime \n" +
                        "FROM activation_changes ac2 " +
                        "WHERE ac.customer_id = ac2.customer_id and " +
                        "ac2.active IS FALSE AND " +
                        "ac.active IS TRUE AND " +
                        "ac.datetime > ac2.datetime " +
                        "ORDER BY ac2.datetime DESC " +
                        "LIMIT 1" +
                    ")))) AS n, " +
                    "COUNT(EXTRACT(years FROM justify_interval(datetime - (" +
                        "SELECT datetime " +
                        "FROM activation_changes ac2 " +
                        "WHERE ac.customer_id = ac2.customer_id and " +
                            "ac2.active IS FALSE AND " +
                            "ac.active IS TRUE AND " +
                            "ac.datetime > ac2.datetime  " +
                        "ORDER BY ac2.datetime DESC  " +
                        "LIMIT 1 " +
                    ")))) AS count " +
                "FROM activation_changes ac " +
                "GROUP BY y, n " +
                    "HAVING (EXTRACT(years FROM justify_interval(datetime - (" +
                        "SELECT datetime " +
                        "FROM activation_changes ac2 " +
                        "WHERE ac.customer_id = ac2.customer_id and " +
                        "ac2.active IS FALSE AND " +
                        "ac.active IS TRUE AND " +
                        "ac.datetime > ac2.datetime " +
                        "ORDER BY ac2.datetime DESC " +
                        "LIMIT 1 " +
                    ")))) IS NOT NULL " +
                "ORDER BY y DESC, n"
        )) {
            try (ResultSet rs = ps.executeQuery()) {

                List<ReacquiredCustomerStatistic> elements = new ArrayList<>();

                while (rs.next()) {
                    ReacquiredCustomerStatistic statistic = new ReacquiredCustomerStatistic();

                    statistic.setYear(rs.getInt("y"));
                    statistic.setYearsSinceDeactivated(rs.getInt("n"));
                    statistic.setCustomerCount(rs.getInt("count"));

                    elements.add(statistic);
                }

                return elements;
            }
        }
    }
}
