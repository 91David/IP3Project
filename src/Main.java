import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
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
        // System.out.println(Document.generateChecksum("md5test.txt"));

        // Exit application.
        System.exit(0);
    }
}