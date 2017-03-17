import org.mindrot.jbcrypt.BCrypt;

class User {

    // TODO - User Sessions and Log-In System

    // Object Variables
    private String userID;      // Unique Identifier.
    private String forename;    // First Name
    private String password;    // Password
    private String email;       // E-Mail Address
    private int role;           // Role ID
    private boolean activeUser;  // Is user active?
    private String surname;     // Last Name
    private String username;    // Unique, mandatory username

    /**
     * Default Instructor
     *
     */
    public User() {
        this.forename = null;
        this.password = null;
        this.email = null;
        this.role = 0;
        this.activeUser = false;
        this.surname = null;
        this.username = null;
    }



    /**
     * Constructor for newUser
     *
     * @param forename   First Name
     * @param password   Password
     * @param email      Email Address
     * @param role       Role ID
     * @param activeUser Is User Active?
     * @param surname    Surname
     * @param username   Username
     */
    public User(String forename, String password, String email, int role, boolean activeUser, String surname, String username) {
        this.forename = forename;
        this.password = password;
        this.email = email;
        this.role = role;
        this.activeUser = activeUser;
        this.surname = surname;
        this.username = username;
    }

    /**
     * Constructor for changeUserDetails
     *
     * @param forename First Name
     * @param password Password
     * @param email    Email Address
     * @param surname  Surname
     * @param username Username
     */
    public User(String userID, String forename, String password, String email, String surname, String username) {
        this.userID = userID;
        this.forename = forename;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.username = username;
    }

    /**
     * Constructor for changePassword
     *
     * @param userID   Unique Identifier
     * @param password Old Password / New Password
     */
    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    /**
     * Constructor for changeRole
     *
     * @param userID Unique Identifier
     * @param role   Role
     */
    public User(String userID, Integer role) {
        this.userID = userID;
        this.role = role;
    }


    /**
     * Creates a User field and checks their password is good before hashing.
     *
     * @param forename   First Name
     * @param password   Password (NOTE: This will be hashed in the method.)
     * @param email      Email Address
     * @param role       Role ID
     * @param activeUser Is User Active?
     * @param surname    Surname
     * @param username   Username
     */
    public static void newUser(String forename, String password, String email, int role, boolean activeUser, String surname, String username) {

        // Create and Fill object with data input.
        User newUser = new User(forename, password, email, role, true, surname, username);

        // Create Object if password is good.
        if (isPasswordGood(password)) {
            newUser.setPassword(hashPassword(newUser.getPassword()));
            // TODO - Parse object into SQL query, add to Database
        } else {
            // TODO - Write Log, Return error to website somehow.
        }

    }

    /**
     * Module for changing a user's password
     * (Could be boolean)
     */
    public static void changePassword() {

        // Pulls User ID and Password
        User u = new User("User", "Password");

        boolean confirm = false;
        String newPass = null;

        // TODO - Get new password + Confirm
        if (isPasswordGood(newPass) && confirm == true) {
            u.setPassword(hashPassword(newPass));
        }

        // TODO - Store new password to DB.

    }

    /**
     * Module for changing a user's password
     * (Could be boolean)
     */
    public static void updateUserDetails() {

        // Pulls Details
        // TODO

        // Collect details to change
        // TODO

        // TODO - Store new password to DB.

    }


    /**
     * Module for changing a user's role
     * (Could be boolean)
     */
    public static void changeRole() {

        // Pulls User ID and Role
        User u = new User("User", 3);

        boolean confirm = false;
        Integer newRole = 1;

        // TODO - Get new password + Confirm
        if (confirm == true) {
            u.setRole(newRole);
        }

        // TODO - Store new password to DB.

    }

    /**
     * Method checks the password contains a number and a capital letter
     * and that the length is greater than eight characters.
     *
     * @param password Users password to check
     * @return true/false dependent on password being applicable.
     */
    private static boolean isPasswordGood(String password) {

        boolean hasNumber = false;
        boolean hasCaps = false;

        // Loop to check each character.
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            // Upper case.
            if (Character.isUpperCase(ch)) {
                hasCaps = true;
            }

            // Contains Number
            if (Character.isDigit(ch)) {
                hasNumber = true;
            }
        }

        // Final check + Password Length
        if (hasNumber == true && hasCaps == true && password.length() >= 8) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * Hashes passwords using jBCrypt.
     *
     * @param password The user's password.
     * @return The hashed password.
     */
    private static String hashPassword(String password) {

        // Note: Do not modify gensalt beyond 30.
        String salt = BCrypt.gensalt();
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
