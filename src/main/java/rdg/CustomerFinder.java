package rdg;

import main.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFinder {
    private static final CustomerFinder INSTANCE = new CustomerFinder();

    public static CustomerFinder getInstance() {
        return INSTANCE;
    }

    public Customer findByBirthNumber(String birthNumber) throws SQLException {

        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM customers WHERE birth_number = ?")) {
            ps.setString(1, birthNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();

                    c.setCustomerId(rs.getInt("id"));
                    c.setBirthNumber(rs.getString("birth_number"));
                    c.setFirstName(rs.getString("first_name"));
                    c.setLastName(rs.getString("last_name"));
                    c.setAddress(rs.getString("address"));

                    if (rs.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Customer> findAll() throws SQLException {
        try (PreparedStatement ps = DbContext.getConnection().prepareStatement("SELECT * FROM customers")) {
            try (ResultSet rs = ps.executeQuery()) {

                List<Customer> elements = new ArrayList<>();

                while (rs.next()) {
                    Customer c = new Customer();

                    c.setCustomerId(rs.getInt("id"));
                    c.setBirthNumber(rs.getString("birth_number"));
                    c.setFirstName(rs.getString("first_name"));
                    c.setLastName(rs.getString("last_name"));
                    c.setAddress(rs.getString("address"));

                    elements.add(c);
                }

                return elements;
            }
        }
    }
}
