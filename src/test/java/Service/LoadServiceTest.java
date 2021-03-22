package Service;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDAO;
import DataAccess.UserDAO;
import Models.Event;
import Models.Person;
import Models.User;
import Services.Request.LoadRequest;
import Services.Result.LoadResult;
import Services.Service.LoadService;
import Utils.LoadDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;

public class LoadServiceTest {
    private ArrayList<User> users;
    private ArrayList<Event> events;
    private ArrayList<Person> persons;
    private LoadService loadService;

    @BeforeEach
    public void setUp() throws Exception {
        users = LoadDataUtils.getMockUserArray();
        events = LoadDataUtils.getMockEventArray();
        persons = LoadDataUtils.getPersonArray();

        loadService = new LoadService();
    }

//    @AfterEach
//    public void tearDown() throws DataAccessException {
//        database.closeConnection(false);
//    }

    @Test
    public void testCallLoadServicePass() throws Exception {
        LoadResult loadResult = new LoadResult("Successfully added " + 2 +
                " users, " + 0 + " persons, and " + 19 + " events to the database.",
                true);

        LoadRequest loadRequest = new LoadRequest();
        loadRequest.setPersons(persons);
        loadRequest.setUsers(users);
        loadRequest.setEvents(events);

        LoadResult compareTest = loadService.callLoadService(loadRequest);
        assertEquals(loadResult, compareTest);
    }

    @Test
    public void testCallLoadServiceFail() throws Exception {
        LoadResult loadResult = new LoadResult("Successfully added " + 0 +
                " users, " + 0 + " persons, and " + 0 + " events to the database.",
                true);

        LoadRequest loadRequest = new LoadRequest();
        ArrayList<User> u = new ArrayList<>();
        ArrayList<Person> p = new ArrayList<>();
        ArrayList<Event> e = new ArrayList<>();
        loadRequest.setPersons(p);
        loadRequest.setUsers(u);
        loadRequest.setEvents(e);

        LoadResult compareTest = loadService.callLoadService(loadRequest);
        assertEquals(loadResult, compareTest);
    }
}
