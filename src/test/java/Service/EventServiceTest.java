package Service;

import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDAO;
import Models.AuthToken;
import Models.Event;
import Services.Result.EventListResult;
import Services.Result.EventResult;
import Services.Service.EventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;

public class EventServiceTest {
    private Database database;
    private Event mockEvent;
    private Event mockEvent1;
    private Connection conn;
    private EventDAO eventDAO;
    private EventService eventService;

    @BeforeEach
    public void setUp() throws Exception {
        database = new Database();

        mockEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        mockEvent1 = new Event("123456", "Gale", "Gale123A",
                48.2f, 32, "USA", "Seattle",
                "Coding", 2021);

        try {
            database = new Database();
            conn = database.getConnection();
        } catch (DataAccessException e) {
            throw new Exception("Could not connect to the database");
        }

        database.clearTables();

        eventDAO = new EventDAO(conn);
        eventService = new EventService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        database.closeConnection(false);
    }

    @Test
    public void callEventServicePass() throws Exception {
        ArrayList<Event> events = new ArrayList<>();
        events.add(mockEvent);
        events.add(mockEvent1);
        EventListResult eventListResult = new EventListResult(events, true);

        eventDAO.insert(mockEvent);
        eventDAO.insert(mockEvent1);

        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);

        AuthToken authToken = new AuthToken("Gale", "12345678");
        authTokenDAO.insert(authToken);

        EventListResult compareTest = eventService.callEventService(null, authToken);

        assertEquals(eventListResult, compareTest);
    }

    @Test
    public void callEventServiceFail() {

    }

    @Test
    public void getEventPass() throws DataAccessException {
        eventDAO.insert(mockEvent);

        String eventID = "Biking_123A";
        EventResult compareTest = eventService.getEvent(eventID);

        assertEquals(mockEvent, compareTest);
    }

    @Test
    public void getEventFail() {

    }
}
