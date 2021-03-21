package Services.Service;
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
     * @param request The event request
     * @return The result of calling the event service
     */
    public EventListResult callEventService(EventRequest request, AuthToken authToken) {
        EventListResult eventListResult = null;
        try {
            Connection conn = database.getConnection();
//
//            PersonDAO personDAO = new PersonDAO(conn);
//            Person person = personDAO.findByUsername(authToken.getUsername());

            EventDAO eventDAO = new EventDAO(conn);
            ArrayList<Event> events = eventDAO.findByUsername(authToken.getUsername());

            eventListResult = new EventListResult(events, true);
        }
        catch (Exception e) {
            eventListResult = new EventListResult("Error: Could not retrieve events for the family members of the current user.", false);
        }
        finally {
            try {
                database.closeConnection(false);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return eventListResult;
    }

    public EventResult getEvent(String eventID) {
        EventResult eventResult = null;
        try {
            Connection conn = database.getConnection();

            EventDAO eventDAO = new EventDAO(conn);
            Event event = eventDAO.find(eventID);

            eventResult = new EventResult(event.getAssociatedUsername(), event.getEventID(), event.getPersonID(),
                    event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(),
                    event.getYear());
        }
        catch (Exception e) {
            eventResult = new EventResult("Error: Could not retrieve event.");
        }
        finally {
            try {
                database.closeConnection(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return eventResult;
    }
}
