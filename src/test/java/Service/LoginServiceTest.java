package Service;

import DataAccess.Database;
import DataAccess.DatabaseDAO;
import DataAccess.UserDAO;
import Models.User;
import Services.Request.LoginRequest;
import Services.Result.LoginResult;
import Services.Service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    private LoginService loginService;
    private UserDAO userDAO;
    private Database database;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        loginService = new LoginService();

        try {
            database = new Database();
            connection = database.getConnection();

            DatabaseDAO databaseDAO = new DatabaseDAO(connection);
            databaseDAO.clearTables();
            connection.commit();

            userDAO = new UserDAO(connection);
        }
        catch (Exception e) {
            throw new Exception("could not connect to database");
        }
    }

    @Test
    public void testCallLoginServicePass() throws Exception {
        LoginRequest loginRequest = new LoginRequest("maddie", "pass123");
        userDAO.insert(new User("maddie", "pass123", "maddie@gmail.com",
                "maddie", "petty", "f", "mad12345"));

        connection.commit();
        LoginResult compareTest = loginService.callLoginService(loginRequest);
        LoginResult expectedResult = new LoginResult(compareTest.authtoken, "maddie", "mad12345");

        assertEquals(expectedResult, compareTest);
    }

    @Test
    public void testCallLoginServiceFail() throws Exception {
        //we are expecting "Error: No matching username and password"

        LoginRequest loginRequest = new LoginRequest("maddie", "pass123");
        assertThrows(Exception.class, ()-> loginService.callLoginService(loginRequest));
    }
}
