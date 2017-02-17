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
     * Uses the SHA-256 hashing algorithm to generate a hash value for a password.
     *
     * @param password
     * @return Hash value for input passcode
     */
     static String hashPassword(String password) {

        // TODO Needs Salt.
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
         for (byte aByteData : byteData) {
             sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
         }

        return password + " ==HASH== " + sb.toString();
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