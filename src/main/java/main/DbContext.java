package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class works with database connection
 * */
public class DbContext {
    private static Connection connection;

    private static void createConnection() {
        String dbUrl = EnvVariables.getEnvVar("DATABASE_URL");
        String dbUser = EnvVariables.getEnvVar("DATABASE_USER");
        String dbPassword = EnvVariables.getEnvVar("DATABASE_PASSWORD");

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+dbUrl, dbUser, dbPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Returns connection, if wasn't created then creates a new connection
     *
     * @return connection
     * */
    public static Connection getConnection() {
        if(connection == null) {
            createConnection();
        }
        return connection;
    }


    /**
     * close Connection to database
     * */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
