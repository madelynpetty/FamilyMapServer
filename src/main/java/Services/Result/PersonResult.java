package Services.Result;

/**
 * The response based on the success of the service
 */
public class PersonResult {
    private String associatedUsername;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;
    private boolean success = false;

    /**
     * Constructor for /person/[personID] success
     *
     * @param associatedUsername
     * @param personID
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     * @param success
     */
    public PersonResult(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof PersonResult) {
            PersonResult personResult = (PersonResult) o;
            return personResult.associatedUsername.equals(associatedUsername) &&
                    personResult.personID.equals(personID) &&
                    personResult.firstName.equals(firstName) &&
                    personResult.lastName == (lastName) &&
                    personResult.gender == (gender) &&
                    personResult.fatherID.equals(fatherID) &&
                    personResult.motherID.equals(motherID) &&
                    personResult.spouseID.equals(spouseID) &&
                    personResult.success == (success);
        } else {
            return false;
        }
    }
}