package Services.Request;
import Models.Event;

/**
 * Takes the request and turns it into a Java object
 */
public class EventRequest {
    private Event[] events;
    private String eventID;
    private String username;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;
}
