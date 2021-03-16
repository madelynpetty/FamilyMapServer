package Models;

/**
 * Each generated Person has the following properties: personID, username, firstName, lastName, gender, fatherID, motherID, spouseID
 */
public class Person {
    private String personID;
    private String username;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    /**
     * Creates a Person with the following objects: personID, username, firstName, lastName, gender, fatherID, motherID, spouseID
     * @param personID Unique identifier for this person (non-empty string)
     * @param username User (Username) to which this person belongs
     * @param firstName Person’s first name (non-empty string)
     * @param lastName Person’s last name (non-empty string)
     * @param gender Person’s gender (string: “f” or “m”)
     * @param fatherID Person ID of person’s father (possibly null)
     * @param motherID Person ID of person’s mother (possibly null)
     * @param spouseID Person ID of person’s spouse (possibly null)
     */
    public Person(String personID, String username, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
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
     * @param personID Unique Person ID assigned to this user’s generated Person object
     */
    public void setPersonID(String personID) {
        this.personID = personID;
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
     * @param username user's unique username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param firstName User’s first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Returns the user’s gender (string: “f” or “m”)
     * @return String
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets user's gender
     * @param gender User’s gender (string: “f” or “m”)
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the user's fatherID
     * @return String
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * Sets Person ID of person’s father
     * @param fatherID Person ID of person’s father (possibly null)
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * Returns the user's motherID
     * @return String
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * Sets Person ID of person’s mother
     * @param motherID Person ID of person’s mother (possibly null)
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * Returns the user's fatherID
     * @return String
     */
    public String getSpouseID() {
        return spouseID;
    }

    /**
     * Sets Person ID of person’s spouse
     * @param spouseID Person ID of person’s spouse (possibly null)
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}
