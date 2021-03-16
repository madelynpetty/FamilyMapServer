package Request;

import passoffmodels.Person;
import passoffmodels.User;
import passoffmodels.Event;

/**
 * Takes the request and turns it into a Java object
 */
public class LoadRequest {
    private User[] users;
    private Person[] persons;
    private Event[] events;
    private boolean success = false;
}
