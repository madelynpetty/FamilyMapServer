package Service;

import DataAccess.DataAccessException;
import DataAccess.Database;
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
            userDAO = new UserDAO(connection);
        }
        catch (Exception e) {
            throw new Exception("could not connect to database");
        }
    }

    @Test
    public void testCallLoginServicePass() throws Exception {
        LoginResult expectedResult = new LoginResult(null, "maddie", "mad12345");
        LoginRequest loginRequest = new LoginRequest("maddie", "pass123");
        userDAO.insert(new User("maddie", "pass123", "maddie@gmail.com",
                "maddie", "petty", "f", "mad12345"));

        LoginResult compareTest = loginService.callLoginService(loginRequest);
        assertEquals(expectedResult, compareTest);
    }

    @Test
    public void testCallLoginServiceFail() throws Exception {
        //we are expecting "Error: No matching username and password"

        LoginRequest loginRequest = new LoginRequest("maddie", "pass123");
        assertThrows(Exception.class, ()-> loginService.callLoginService(loginRequest));
    }
}
