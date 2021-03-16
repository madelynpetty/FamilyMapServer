package Services.Request;

import Models.Person;
import Models.User;
import Models.Event;

/**
 * Takes the request and turns it into a Java object
 */
public class LoadRequest {
    private User[] users;
    private Person[] persons;
    private Event[] events;
    private boolean success = false;
}
