package Service;

import DataAccess.*;
import Models.Person;
import Models.User;
import Services.Request.FillRequest;
import Services.Result.FillResult;
import Services.Service.FillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {
    private Database database;
    private Connection conn;
    private FillService fillService;

    @BeforeEach
    public void setUp() throws Exception {
        database = new Database();

        try {
            database = new Database();
            conn = database.getConnection();
        } catch (DataAccessException e) {
            throw new Exception("Could not connect to the database");
        }

        DatabaseDAO databaseDAO = new DatabaseDAO(conn);
        databaseDAO.clearTables();
        fillService = new FillService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        database.closeConnection(false);
    }

    @Test
    public void callFillServicePass() throws Exception {
        User user = new User("maddie", "pass123", "maddie@gmail.com",
                "maddie", "petty", "f", "12345");
        UserDAO userDAO = new UserDAO(conn);
        userDAO.insert(user);

        Person person = new Person("12", "maddie", "joe", "johnson",
                "m", "dad123", "mom123", "spouse123");
        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(person);


        FillRequest fillRequest = new FillRequest("maddie", 3);
        FillResult fillResult = fillService.callFillService(fillRequest);

        FillResult expectedResult = new FillResult("Error: Could not fill the database", false);
        assertEquals(fillResult, expectedResult);
    }

    @Test
    public void callFillServiceFail() throws Exception {
        FillResult fillResult = new FillResult("Error: Could not fill the database", false);
        FillRequest fillRequest = new FillRequest("hi", 4);
        assertEquals(fillResult, fillService.callFillService(fillRequest));
    }
}
