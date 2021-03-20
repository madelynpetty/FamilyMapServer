package Services.Result;

import Models.Person;

/**
 * The response based on the success of the service
 */
public class PersonResult {
    private String message;
    private String associatedUsername;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;
    private boolean success = false;

    public PersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
}