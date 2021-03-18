package Models;

/**
 * In order to use Family Map, one must first create a user account. The server stores information about each user account in its database.
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID; //non empty
//    private String authToken;


    /**
     * Creates a user with the following objects: username, password, email, firstName, lastName, gender, and personID.
     * @param username User's unique username
     * @param password User's password
     * @param email User's email address
     * @param firstName User's first name
     * @param lastName User's last name
     * @param gender User's gender ("f" or "m")
     * @param personID Unique Person ID assigned to this user’s generated Person object
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Returns the user’s gender (string: “f” or “m”)
     * @return String
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets user's gender (string: “f” or “m”)
     * @param gender String ("f" or "m")
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the unique Person ID assigned to this user’s generated Person object
     * @return String
     */
    public String getPersonID() {
        return personID;
    }

    /**
     *Sets the unique Person ID assigned to this user’s generated Person object
     * @param personID String
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Returns user’s last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets user's last name
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user’s first name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns user’s email address
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user’s email address
     * @param emailAddress String
     */
    public void setEmail(String emailAddress) {
        this.email = email;
    }

    /**
     * Returns user’s password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user’s password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user's unique username
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's unique username
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }
//
//    /**
//     * Returns the user's unique authtoken
//     * @return String
//     */
//    public String getAuthToken() {
//        return authToken;
//    }
//
//    /**
//     * Sets user's unique authToken
//     * @param authToken String
//     */
//    public void setAuthToken(String authToken) {
//        this.authToken = authToken;
//    }

}
