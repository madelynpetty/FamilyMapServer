package Service;

import DataAccess.Database;
import DataAccess.UserDAO;
import Models.User;
import Services.Result.ClearResult;
import Services.Service.ClearService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {
    @Test
    public void callClearServicePass() throws Exception {
        ClearResult clearResult = new ClearResult("Clear succeeded.", true);

        ClearService clearService = new ClearService();
        ClearResult compareTest = clearService.callClearService();

        assertEquals(clearResult, compareTest);
    }

    @Test
    public void callClearServiceFail() throws Exception {
        ClearResult clearResult = new ClearResult("Error: Could not clear the database", false);

        ClearService clearService = new ClearService();
        ClearResult compareTest = clearService.callClearService();

        Database database = new Database();
        try {
            Connection conn = database.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            userDAO.insert(new User("Gale", "pass123", "gale@gmail.com",
                    "Gale", "Johnson", "m", "Gale123A"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertNotEquals(compareTest, clearResult);
    }
}
