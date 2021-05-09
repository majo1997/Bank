package entities;

import main.DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customers {
    private final List<Customer> customers;

    public Customers() {
        customers = new ArrayList<>();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        String sql = "SELECT * FROM customers";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer customerId = rs.getInt(1);
                String birthNumber = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String address = rs.getString(5);

                Customer c = new Customer(customerId, birthNumber, firstName, lastName, address);
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for(Customer c: customers) {
            System.out.println(c);
        }
    }
}
