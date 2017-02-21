import com.dropbox.core.v2.DbxClientV2;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        // DropBox Connection
        DbxClientV2 dropboxConnection = Connector.connectDropbox();
        if (dropboxConnection == null) {
            System.out.println("Failure to Connect to DropBox. Exiting...");
            // System.exit(0);
        }

        // Database connection
        Connection databaseConnection = Connector.connectDB();
        if (databaseConnection == null) {
            System.out.println("Failure to Connect to DB. Exiting...");
            // System.exit(0);
        }

        // 1.   Registration of Users to a database.
        // User.register("A Person", databaseConnection);

        // 2.   Hashing using BCrypt.
        // String hash = User.hashPassword("SomeonesPassword");
        // User.verifyPassword("SomeonesPassword", hash);

        // 3.    Generate MD5 checksum for file.
        System.out.println(Document.generateChecksum("test.txt"));

        // 4.   Upload and Download files to dropbox
        Document.uploadFile("test.txt", dropboxConnection);

        // 5.   View DropBox folder contents.
        Document.viewFolder("", dropboxConnection);

        // Exit application.
        System.exit(0);
    }
}