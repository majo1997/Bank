package entities;

import main.DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
//    private List<Transaction> transactions;
//
//    //todo acc id or acc number??
//    public getByAccountId(String accountId) {
//        String sql = "SELECT * FROM customers";
//
//        Connection connection = DbContext.getConnection();
//
//        try(PreparedStatement ps = connection.prepareStatement(sql)){
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                Integer customerId = rs.getInt(1);
//                String birthNumber = rs.getString(2);
//                String firstName = rs.getString(3);
//                String lastName = rs.getString(4);
//                String address = rs.getString(5);
//
//                Customer c = new Customer(customerId, birthNumber, firstName, lastName, address);
//                customers.add(c);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public printByAccountId(Integer accountId) {
//
//    }
}
