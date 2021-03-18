package DataAccess;
import Models.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for the event. Inserts, deletes, and clears the event table in the database.
 */
public class EventDAO {
    private final Connection conn;

    public EventDAO(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Inserts the event into the event table in the database
     * @param event the event to be added to the database
     * @return a boolean of whether or not the event was added to the event table in the database
     */
    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO event (eventID, username, personID, latitude, longitude, " +
                "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs = null;
        String sql = "SELECT * FROM event WHERE eventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("username"),
                        rs.getString("personID"), rs.getFloat("latitude"),
                        rs.getFloat("longitude"), rs.getString("country"),
                        rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Deletes the event from the event table in the database
     * @param event the event to be deleted from the database
     * @return a boolean of whether or not the event was deleted from the event table in the database
     */
    public boolean delete(Event event) {
        return false;
    } //don't think i need this method


    /**
     * Clears the event table in the database
     * @return a boolean of whether or not the event table in the database was cleared
     */
    public boolean clear() {
        return false;
    }
}
