package entities;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewCustomerCountsStatisticFinder {
    private static final NewCustomerCountsStatisticFinder INSTANCE = new NewCustomerCountsStatisticFinder();

    public static NewCustomerCountsStatisticFinder getInstance() {
        return INSTANCE;
    }

    private NewCustomerCountsStatisticFinder() { }

    public List<NewCustomerCountsStatistic> findAll() throws SQLException {

        //todo add also year quater where it is 0
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement(
                "SELECT EXTRACT(year FROM datetime) y, " +
                        "EXTRACT(quarter FROM datetime) quarter, " +
                        "SUM(CASE WHEN active THEN 1 WHEN NOT active THEN -1 END) AS count " +
                        "FROM activation_changes " +
                        "GROUP BY EXTRACT(year FROM datetime), EXTRACT(quarter FROM datetime) " +
                        "ORDER BY EXTRACT(year FROM datetime), EXTRACT(quarter FROM datetime);")) {
            try (ResultSet rs = ps.executeQuery()) {

                List<NewCustomerCountsStatistic> elements = new ArrayList<>();

                while (rs.next()) {
                    NewCustomerCountsStatistic statistic = new NewCustomerCountsStatistic();

                    statistic.setYear(rs.getInt("y"));
                    statistic.setQuadYear(rs.getInt("quarter"));
                    statistic.setCount(rs.getInt("count"));

                    elements.add(statistic);
                }

                return elements;
            }
        }
    }

}
