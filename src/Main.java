import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        /*
        // Establishing database connection
        Connection databaseConnection = Connector.connect();
        if (databaseConnection == null) {
            System.out.println("Failure to Connect to DB. Exiting...");
            System.exit(0);
        }
        */

        // 1.   Registration of Users to a database.
        // User.register("A Person", databaseConnection);
			
        // 2.   Hashing using BCrypt.
        // This is supposed to take a while.
        String hash = User.hashPassword("SomeonesPassword");
        User.verifyPassword("SomeonesPassword", hash);

    }
}