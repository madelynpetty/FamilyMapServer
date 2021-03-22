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

//We will use this to test that our insert method is working and failing in the right ways
public class DatabaseDAOTest {
    private Database db;
    private DatabaseDAO databaseDAO;

    @BeforeEach
    public void setUp() throws Exception {
        db = new Database();
        Connection conn = db.getConnection();

        databaseDAO.clearTables();

        databaseDAO = new DatabaseDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void clearPass() throws DataAccessException, SQLException {

    }

    @Test
    public void clearFail() throws DataAccessException, SQLException {

    }
}
