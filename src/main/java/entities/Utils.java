package entities;

import main.DbContext;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Utils {
    //todo if empty str is scanned throw Exceotion? primary for loading bigdecimal from string// for bigdecimal use . as decimal sep
    public static String getStringFromInput(String message) {
        System.out.print(message + " ");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static Integer getIntFromInput(String message) {
        System.out.print(message + " ");

        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    public static BigDecimal getBigDecimalFromInput(String message) {//todo remove
        System.out.print(message + " ");

        Scanner sc = new Scanner(System.in);

        return sc.nextBigDecimal();
    }

    //todo check this 3 methods
    public static String uniqueRandomAccountNumber() {
        //SK3386785834159015377697197684//28 - 2 = 30
        boolean unique = false;
        String generatedAccountNumber = null;

        while(!unique) {
            generatedAccountNumber = generateAccountNumber();
            unique = uniqueAccountNumber(generatedAccountNumber);
        }

        return generatedAccountNumber;
    }

    private static String generateAccountNumber() {

        SecureRandom random = new SecureRandom();
        String CHARACTERS = "0123456789";

        StringBuilder sb = new StringBuilder();
        sb.append("SK");

        for(int i = 0; i < 28; i++) {//todo constant 28
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }

    private static Boolean uniqueAccountNumber(String accountNumber) {
        String sql = "SELECT * " +
                "FROM accounts " +
                "WHERE account_number = ?";

        Connection connection = DbContext.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    //todo ^^^

}
