package DataAccess;

import Models.AuthToken;
import Models.Event;
import Utils.RandomDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EventDAOTest {
    private Database db;
    private Event bestEvent;
    private EventDAO eDao;

    @BeforeEach
    public void setUp() throws Exception {
        db = new Database();
        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        Connection conn = db.getConnection();
        DatabaseDAO databaseDAO = new DatabaseDAO(conn);
        databaseDAO.clearTables();
        eDao = new EventDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        eDao.insert(bestEvent);
        Event compareTest = eDao.find(bestEvent.getEventID());
        assertNotNull(compareTest);
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        eDao.insert(bestEvent);
        assertThrows(DataAccessException.class, ()-> eDao.insert(bestEvent));
    }

    @Test
    public void findPass() throws DataAccessException {
        eDao.insert(bestEvent);
        eDao.find(bestEvent.getEventID());
        Event compareTest = eDao.find(bestEvent.getEventID());
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        eDao.insert(bestEvent);
        Event compareTest = eDao.find(bestEvent.getEventID());
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void findByUsernamePass() throws DataAccessException {
        ArrayList<Event> bestEvents = new ArrayList<>();
        Event bestEvent1 = new Event("12345678", "Gale", "Gale123A",
                12, 34, "United States", "Kaysville", "Coding", 2021);
        bestEvents.add(bestEvent);
        bestEvents.add(bestEvent1);

        eDao.insert(bestEvent);
        eDao.insert(bestEvent1);
        ArrayList<Event> compareTest = eDao.findByUsername(bestEvent.getAssociatedUsername());

        assertEquals(compareTest, bestEvents);
    }

    @Test
    public void findByUsernameFail() throws DataAccessException {
        ArrayList<Event> bestEvents = new ArrayList<>();
        Event bestEvent1 = new Event("Biking_123A", "Gale", "Gale123A",
                48, 16, "USA", "Kaysville",
                "coding", 2021);

        bestEvents.add(bestEvent);
        bestEvents.add(bestEvent1);

        eDao.insert(bestEvent);
        ArrayList<Event> compareTest = eDao.findByUsername(bestEvent.getAssociatedUsername());

        assertNotEquals(compareTest, bestEvents);
    }

    @Test
    public void clearPass() throws DataAccessException, SQLException {
        eDao.insert(bestEvent);
        eDao.clear(bestEvent.getAssociatedUsername());
        Event notAnEvent = eDao.find(bestEvent.getEventID());
        assertEquals(notAnEvent, null);
    }

    @Test
    public void clearFail() throws DataAccessException, SQLException {
        eDao.insert(bestEvent);
        eDao.clear(bestEvent.getCity());
        Event actualEvent = eDao.find(bestEvent.getEventID()); //since we didn't actually clear anything out
        assertEquals(actualEvent, bestEvent);
    }
}
