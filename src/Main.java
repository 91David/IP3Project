import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    // Database Details (These worked for my home computer running Debian. May need reconfigured for other systems..
    private static final String databaseURL = "jdbc:mysql://localhost:3306/";
    private static final String databaseUsername = "admin";
    private static final String databasePassword = "nope"; // TODO - encryption?

    public static void main(String[] args) {

        // Establishing database connection
        Connection databaseConnection = testDatabase(databaseURL, databaseUsername, databasePassword);

        // Test to ensure we can make use of the DB
        if (databaseConnection == null) {
            System.out.println("Failure to Connect to DB. Exiting...");
            System.exit(0);
        }

        System.out.println("Go write some crap here.");

        User.register("A Person", databaseConnection);


    }

    /**
     * Module to test that the database can be connected to.
     * Obviously doesn't check if database gets removed/crashed mid program.
     *
     * @param url      JDBC address of running MySQL database
     * @param username Username for DB
     * @param password Password for DB
     * @return true if database is running correctly.
     */
    private static Connection testDatabase(String url, String username, String password) {

        // Try-Catch to find the MySQL Driver
        // Note: You need to download the mysql-connector-java library.
        try {
            System.out.println("Finding JDBC Driver...");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find DB Driver. Check console.");
            e.printStackTrace();
            return null;
        }

        System.out.println("JDBC Driver found. Attempting to connect to database...");
        Connection c;

        // Try-Catch to create connection instance.
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("DB Connection failed. Check console.");
            e.printStackTrace();
            return null;
        }

        // Confirming a connection instance exists.
        if (c != null) {
            System.out.println("Database successfully connected.");
            return c;
        }
        else return null;

    }

}