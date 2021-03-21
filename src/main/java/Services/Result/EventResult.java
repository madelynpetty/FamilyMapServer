package Services.Result;

import Models.Event;

import java.util.ArrayList;

/**
 * The response based on the success of the service
 */
public class EventResult {
    private String message;
    private String associatedUsername;
    private String eventID;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;
    private boolean success = false;

    public EventResult(String message) {
        this.message = message;
        success = false;
    }

    public EventResult(String associatedUsername, String eventID, String personID, float latitude,
                       float longitude, String country, String city, String eventType, int year) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        success = true;
    }


    public String getMessage() {
        return message;
    }
}