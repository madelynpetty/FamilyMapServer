package Models;

/**
 * Each generated Event has the following properties: eventID, username, personID, latitude, longitude, country, city, eventType, and year.
 */
public class Event {
    private String eventID;
    private String username;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    /**
     * Creates an event with the following properties: eventID, username, personID, latitude, longitude, country, city, eventType, and year.
     * @param eventID Unique identifier for this event (non-empty string)
     * @param username User (Username) to which this person belongs
     * @param personID ID of person to which this event belongs
     * @param latitude Latitude of event’s location
     * @param longitude Longitude of event’s location
     * @param country Country in which event occurred
     * @param city City in which event occurred
     * @param eventType Type of event (birth, baptism, christening, marriage, death, etc.)
     * @param year Year in which event occurred
     */
    public Event(String eventID, String username, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.username = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * Returns Unique identifier for this event
     * @return String
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets unique identifier for this event
     * @param eventID String
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * Returns user (Username) to which this person belongs
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets user (Username) to which this person belongs
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns ID of person to which this event belongs
     * @return String
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets ID of person to which this event belongs
     * @param personID String
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Returns country in which event occurred
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country in which event occurred
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns city in which event occurred
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city in which event occurred
     * @param city String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns latitude of event’s location
     * @return float
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude of event’s location
     * @param latitude float
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude of event’s location
     * @return String
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude of event’s location
     * @param longitude float
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets type of event (birth, baptism, christening, marriage, death, etc.)
     * @return String
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets type of event (birth, baptism, christening, marriage, death, etc.)
     * @param eventType String
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Returns year in which event occurred
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year in which event occurred
     * @param year int
     */
    public void setYear(int year) {
        this.year = year;
    }
}
