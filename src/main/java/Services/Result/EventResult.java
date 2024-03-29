package Services.Result;

/**
 * The response based on the success of the service
 */
public class EventResult {
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

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof EventResult) {
            EventResult eventResult = (EventResult) o;
            return eventResult.associatedUsername.equals(associatedUsername) &&
                    eventResult.eventID.equals(eventID) &&
                    eventResult.personID.equals(personID) &&
                    eventResult.latitude == (latitude) &&
                    eventResult.longitude == (longitude) &&
                    eventResult.country.equals(country) &&
                    eventResult.city.equals(city) &&
                    eventResult.eventType.equals(eventType) &&
                    eventResult.year == (year);
        } else {
            return false;
        }
    }
}