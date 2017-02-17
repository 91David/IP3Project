import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

class User {

    // TODO - User Sessions and Log-In System

    // Object Variables
    private String forename;    // First Name
    private String password;    // Password
    private String role;        // Role of user (uses enum)
    private String surname;     // Last Name
    private String username;    // Unique, mandatory username

    /**
     * Constructor for User class.
     *
     * @param forename First Name
     * @param surname  Last Name
     * @param username Username
     * @param password Password
     * @param role     User Role
     */
    public User(String forename, String surname, String username, String password, String role) {
        this.forename = forename;
        this.password = password;
        this.role = role;
        this.surname = surname;
        this.username = username;
    }

    @SuppressWarnings("SameParameterValue")
    static void register(String fullName, Connection c) {

        // Create object
        User newUser = new User(fullName.split(" ", 2)[0],
                fullName.split(" ", 2)[1], fullName,
                "testPassword",
                "IDK");

        // Throw into DB
        // TODO error parameters
        try {

            String statement = String.format("INSERT INTO mydb.User (forename, surname, username, password, role) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s');", newUser.getForename(), newUser.getSurname(),
                    newUser.getUsername(), newUser.getPassword(), newUser.getRole());

            Statement s = c.createStatement();
            s.executeUpdate(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hashes passwords using jBCrypt.
     *
     * @param password The user's password.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {

        // Note: Do not modify gensalt beyond 30.
        String salt = BCrypt.gensalt(16);
        String hashedPassword = BCrypt.hashpw(password, salt);

        System.out.printf("Password: %s \n Hash: %s \n Salt: %s \n\n", password, hashedPassword, salt);
        return hashedPassword;
    }

    /**
     * Check that an unencrypted password matches one that has previously been hashed.
     * (It should NOT reveal the password, only verify whether the hashed matches the plain.)
     *
     * @param password       The user's password.
     * @param hashedPassword The hashed password.
     * @return Boolean verifying whether password matches.
     */
    public static Boolean verifyPassword(String password, String hashedPassword) {
        System.out.println(BCrypt.checkpw(password, hashedPassword));
        return BCrypt.checkpw(password, hashedPassword);
    }

    private String getForename() {
        return forename;
    }

    private String getSurname() {
        return surname;
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    private String getRole() {
        return role;
    }

}