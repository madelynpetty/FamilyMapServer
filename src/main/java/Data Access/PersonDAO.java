/**
 * Data Access Object for the person. Inserts, deletes, and clears the person table in the database.
 */
public class PersonDAO {
    /**
     * Inserts the person into the person table in the database
     * @param person the person to be added to the database
     * @return a boolean of whether or not the person was added to the person table in the database
     */
    public boolean insert(Person person) {
        return false;
    }

    /**
     * Deletes the person from the person table in the database
     * @param person the person to be deleted from the database
     * @return a boolean of whether or not the pesron was deleted from the person table in the database
     */
    public boolean delete(Person person) {
        return false;
    }

    /**
     * Clears the person table in the database
     * @return a boolean of whether or not the person table in the database was cleared
     */
    public boolean clear() {
        return false;
    }

//    private String associatedUsername; // Name of user account this person belongs to
//    private String personID; //Person's unique ID
//    private String firstName; //Person's first name
//    private String lastName; //person's last name
//    private String gender; //Person's gender ("m" or "f")
//    private String fatherID; // ID of person’s father [OPTIONAL, can be missing]
//    private String motherID; // ID of person’s mother [OPTIONAL, can be missing]
//    private String spouseID; // ID of person’s spouse [OPTIONAL, can be missing]
//    private boolean success;

//    public PersonDAO(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
//        this.associatedUsername = associatedUsername;
//        this.personID = personID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.gender = gender;
//        this.fatherID = fatherID;
//        this.motherID = motherID;
//        this.spouseID = spouseID;
//    }
//    /**
//     * Returns associated username with the given person
//     * @return String
//     */
//    public String getAssociatedUsername() {
//        return associatedUsername;
//    }
//
//    /**
//     * Sets person's associated username
//     * @param associatedUsername String
//     */
//    public void setAssociatedUsername(String associatedUsername) {
//        this.associatedUsername = associatedUsername;
//    }
//
//    /**
//     * Returns the personID
//     * @return String
//     */
//    public String getPersonID() {
//        return personID;
//    }
//
//    /**
//     *  Sets personID inside the Person Data Access Object class
//     * @param personID
//     */
//    public void setPersonID(String personID) {
//        this.personID = personID;
//    }
//
//    /**
//     * Returns the person's first name
//     * @return the given person's first name
//     */
//    public String getFirstName() {
//        return firstName;
//    }
//
//    /**
//     *
//     * @param firstName
//     */
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getFatherID() {
//        return fatherID;
//    }
//
//    public void setFatherID(String fatherID) {
//        this.fatherID = fatherID;
//    }
//
//    public String getMotherID() {
//        return motherID;
//    }
//
//    public void setMotherID(String motherID) {
//        this.motherID = motherID;
//    }
//
//    public String getSpouseID() {
//        return spouseID;
//    }
//
//    public void setSpouseID(String spouseID) {
//        this.spouseID = spouseID;
//    }
}
