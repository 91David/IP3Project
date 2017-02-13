import java.sql.*;

public class User {

    // TODO - User Sessions and Log-In System
    // TODO - File storage: https://dev.mysql.com/doc/refman/5.7/en/blob.html

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

        // Throw into DB TODO
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