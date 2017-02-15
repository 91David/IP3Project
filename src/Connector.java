import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

class Connector {

    // Database Details
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String username = "0h2JO5CXxGjjNChe";
    private static final String password = "s3kBqPoTdKPFl4kE0J847ozVrUgwM70UYwIu3NtpDhz19U7iIYviR6sarj3XySmK"; // TODO - encryption?

    /**
     * Returns a connection object, based on the Connector's variables.
     *
     * @return connection object for database.
     */
    static Connection connect() {

        System.out.println("Attempting to connect to database...");
        Connection conn;

        // Try-Catch to create connection instance.
        try {
            new Driver();
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // Confirming a connection instance exists.
        if (conn != null) {
            System.out.println("Database successfully connected.");
            return conn;
        } else return null;

    }


}
