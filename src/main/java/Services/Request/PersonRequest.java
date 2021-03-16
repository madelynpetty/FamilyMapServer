package Request;

import passoffmodels.Person;

/**
 * Takes the request and turns it into a Java object
 */
public class PersonRequest {
    private Person[] persons;
    private String personID;
    private String username;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;
    private boolean success = false;
}
