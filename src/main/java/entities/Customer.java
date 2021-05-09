package entities;

import main.DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer implements Entity {

    private Integer customerId;
    private String birthNumber;
    private String firstName;
    private String lastName;
    private String address;

    public Customer(String birthNumber) throws InvalidValueException {
        boolean loaded = loadCustomerPropertiesByBirthNumber(birthNumber);

        if(!loaded) {
            throw new InvalidValueException("Invalid customers birth number!");
        }
    }

    public Customer(Integer customerId, String birthNumber, String firstName, String lastName, String address) {
        this.customerId = customerId;
        this.birthNumber = birthNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    private boolean loadCustomerPropertiesByBirthNumber(String birthNumber) {
        String sql = "SELECT * " +
                "FROM customers " +
                "WHERE birth_number = ?";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, birthNumber);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Integer customerId = rs.getInt(1);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String address = rs.getString(5);

                this.customerId = customerId;
                this.birthNumber = birthNumber;
                this.firstName = firstName;
                this.lastName = lastName;
                this.address = address;

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void update() {
        String sql = "UPDATE customers " +
                "SET first_name = ?, last_name = ?, address = ? " +
                "WHERE id = ?";

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setInt(4, customerId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String toString() {
        return customerId + ". " + birthNumber + ", " + firstName + " " + lastName + ", " + address;
    }

    public void showAccounts() {

    }
}

