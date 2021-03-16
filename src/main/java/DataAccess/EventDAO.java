package DataAccess;
import Models.Event;

/**
 * Data Access Object for the event. Inserts, deletes, and clears the event table in the database.
 */
public class EventDAO {
    /**
     * Inserts the event into the event table in the database
     * @param event the event to be added to the database
     * @return a boolean of whether or not the event was added to the event table in the database
     */
    public boolean insert(Event event) {
        return false;
    }

    /**
     * Deletes the event from the event table in the database
     * @param event the event to be deleted from the database
     * @return a boolean of whether or not the event was deleted from the event table in the database
     */
    public boolean delete(Event event) {
        return false;
    } //don't think i need this method

    public boolean find(Event event) {
        return false;
    }

    /**
     * Clears the event table in the database
     * @return a boolean of whether or not the event table in the database was cleared
     */
    public boolean clear() {
        return false;
    }


//    private String associatedUsername; // Username of user account this event belongs to (non-empty string)
//    private String eventID; // Event’s unique ID (non-empty string)
//    private String personID; // ID of the person this event belongs to (non-empty string)
//    private double latitude; // Latitude of the event’s location (number)
//    private double longitude; // Longitude of the event’s location (number)
//    private String country; // Name of country where event occurred (non-empty string)
//    private String city; // Name of city where event occurred (non-empty string)
//    private String eventType; // Type of event (“birth”, “baptism”, etc.) (non-empty string)
//    private int year; //Year the event occurred (integer)
//    private boolean success = false; //boolean identifier
//    public String getAssociatedUsername() {
//        return associatedUsername;
//    }
//
//    public void setAssociatedUsername(String associatedUsername) {
//        this.associatedUsername = associatedUsername;
//    }
//
//    public String getEventID() {
//        return eventID;
//    }
//
//    public void setEventID(String eventID) {
//        this.eventID = eventID;
//    }
//
//    public String getPersonID() {
//        return personID;
//    }
//
//    public void setPersonID(String personID) {
//        this.personID = personID;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getEventType() {
//        return eventType;
//    }
//
//    public void setEventType(String eventType) {
//        this.eventType = eventType;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }

}
