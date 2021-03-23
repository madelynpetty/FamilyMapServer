package DataAccess;

import Models.AuthToken;
import Models.Event;
import Models.Person;
import Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseDAOTest {
    private Database db;
    private DatabaseDAO databaseDAO;
    private Connection conn;

    @BeforeEach
    public void setUp() throws Exception {
        db = new Database();
        conn = db.getConnection();

        databaseDAO = new DatabaseDAO(conn);
        databaseDAO.clearTables();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    //this test clears an already empty database that only has events in it
    @Test
    public void clearOnlyEvents() throws DataAccessException, SQLException {
        EventDAO eventDAO = new EventDAO(conn);
        Event mockEvent= new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        eventDAO.insert(mockEvent);

        databaseDAO.clearTables();
        assertNotNull(databaseDAO);
    }

    //this clears the database when it has only people and users in it
    @Test
    public void clearPeopleAndUsers() throws DataAccessException, SQLException {
        Person mockPerson = new Person("Gale123A", "Gale", "Gale",
                "Johnson", "m", "Mark123A", "Mother12", "Spouse12");
        User mockUser = new User("Gale", "pass123", "gale@gmail.com",
                "Gale", "Johnson", "m", "Gale123A");

        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(mockPerson);

        UserDAO userDAO = new UserDAO(conn);
        userDAO.insert(mockUser);

        databaseDAO.clearTables();
        assertNotNull(databaseDAO);
    }

    //this clears the database with something in each table
    @Test
    public void clearAll() throws DataAccessException {
        Event mockEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        EventDAO eventDAO = new EventDAO(conn);
        eventDAO.insert(mockEvent);

        Person mockPerson = new Person("Gale123A", "Gale", "Gale",
                "Johnson", "m", "Mark123A", "Mother12", "Spouse12");
        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(mockPerson);

        User mockUser = new User("Gale", "pass123", "gale@gmail.com",
                "Gale", "Johnson", "m", "Gale123A");
        UserDAO userDAO = new UserDAO(conn);
        userDAO.insert(mockUser);

        AuthToken mockAuthToken = new AuthToken("maddie", "123456");
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        authTokenDAO.insert(mockAuthToken);

        databaseDAO.clearTables();
        assertNotNull(databaseDAO);
    }
}
