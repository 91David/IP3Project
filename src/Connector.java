import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

/**
 * Class for handling connections to databases and third party applications
 */
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
    static Connection connectDB() {

        // Try-Catch to create connection instance.
        Connection conn;
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

    /**
     * Attempts a connection to DropBox through API, attempts to test connection.
     *
     * @return Instance of Dropbox client.
     */
    @SuppressWarnings("deprecation")
    static DbxClientV2 connectDropbox() {

        // Establish connection to DropBox.
        final String ACCESS_TOKEN = "INSERT ACCESS TOKEN HERE!";
        DbxRequestConfig config = new DbxRequestConfig("dropbox/IdeaGen_DocumentServer", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Check there is a connection.
        try {
            FullAccount account = client.users().getCurrentAccount();
            System.out.println("DropBox sucessfully connected: " + account.getName().getDisplayName());
            return client;
        } catch (DbxException e) {
            e.printStackTrace();
            return null;
        }

    }


}
