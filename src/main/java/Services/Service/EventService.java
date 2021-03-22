package Services.Service;
import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import DataAccess.EventDAO;
import Models.AuthToken;
import Models.Event;
import Services.Request.EventRequest;
import Services.Result.EventListResult;
import Services.Result.EventResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * The EventService makes the request and returns the result
 */
public class EventService {
    private Database database = new Database();

    //event
    /**
     * Returns the result of calling the Event Service
     * @return The result of calling the event service
     */
    public EventListResult callEventService(String authToken) throws Exception {
        try {
            Connection conn = database.openConnection();

            EventDAO eventDAO = new EventDAO(conn);
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            AuthToken authToken1 = authTokenDAO.find(authToken);

            if (authToken1 == null) {
                throw new Exception("Error: could not find authtoken in the database");
            }

            ArrayList<Event> events = eventDAO.findByUsername(authToken1.getUsername());

            return new EventListResult(events, true);
        }
        catch (Exception e) {
            throw new Exception("Error: Could not retrieve events for the family members of the current user.");
        }
        finally {
            try {
                database.closeConnection(false);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public EventResult getEvent(String eventID) throws Exception {
        try {
            Connection conn = database.openConnection();

            EventDAO eventDAO = new EventDAO(conn);
            Event event = eventDAO.find(eventID);

            if (event == null) {
                throw new Exception("Error: Could not find event.");
            }

            return new EventResult(event.getAssociatedUsername(), event.getEventID(), event.getPersonID(),
                    event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(),
                    event.getYear());
        }
        catch (Exception e) {
            throw new Exception("Error: Could not retrieve event.");
        }
        finally {
            try {
                database.closeConnection(false);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
