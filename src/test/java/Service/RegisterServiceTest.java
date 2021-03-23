package Service;

import DataAccess.*;
import Services.Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {
    private Database database;
    private Connection conn;
    private RegisterService registerService;

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

        registerService = new RegisterService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        database.closeConnection(false);
    }

    @Test
    public void callRegisterServicePass() throws Exception {

    }

    @Test
    public void callRegisterServiceFail() {
        assertThrows(Exception.class, ()-> registerService.callRegisterService(null));
    }
}
