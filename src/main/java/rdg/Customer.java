package rdg;

import main.DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer {
    private Integer customerId;
    private String birthNumber;
    private String firstName;
    private String lastName;
    private String address;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void update() throws SQLException {
        if(customerId == null) {
            throw new IllegalStateException("Customer ID is not set");
        }

        Connection conn = DbContext.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("UPDATE customers SET first_name = ?, last_name = ?, address = ? WHERE id = ?")) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setInt(4, customerId);

            ps.executeUpdate();
        }
    }

}
