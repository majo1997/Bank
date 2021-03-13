package main;

import user_interface.MainMenu;

/**
 * Main class
 * */
public class Main {

    /**
     * Application entry point
     *
     * @param args program arguments
     * */
    public static void main(String[] args) {
        EnvVariables.loadEnvVars();

        DbContext.getConnection();

        MainMenu menu = new MainMenu();

        boolean close = false;
        while(!close) {
            close = menu.showAndSelect();
        }

//        Customers customers = new Customers();
//        customers.print();


//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank", "postgres", "root")) {
//
//            System.out.println("Java JDBC PostgreSQL Example");
//            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
//            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
////          Class.forName("org.postgresql.Driver");
//
//            System.out.println("Connected to PostgreSQL database!");
//            Statement statement = connection.createStatement();
//            System.out.println("Reading test records...");
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
//            while (resultSet.next()) {
//                System.out.printf("%s", resultSet.getString("name"));
//            }
//
//        } /*catch (ClassNotFoundException e) {
//            System.out.println("PostgreSQL JDBC driver not found.");
//            e.printStackTrace();
//        }*/ catch (SQLException e) {
//            System.out.println("Connection failure.");
//            e.printStackTrace();
//        }

        DbContext.closeConnection();
    }
}