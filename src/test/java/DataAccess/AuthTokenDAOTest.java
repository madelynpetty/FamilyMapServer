package DataAccess;

import Models.AuthToken;
import Utils.RandomDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database database;
    private AuthToken bestAuthToken;
    private AuthTokenDAO atDao;

    @BeforeEach
    public void setUp() throws Exception {
        try {
            bestAuthToken = new AuthToken("Gale", "Gale123A");

            database = new Database();
            Connection conn = database.openConnection();
            DatabaseDAO databaseDAO = new DatabaseDAO(conn);
            databaseDAO.clearTables();

            atDao = new AuthTokenDAO(conn);
        }
        catch (Exception e) {
            throw new Exception("Error: cannot set up AuthTokenTest");
        }
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        database.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        atDao.insert(bestAuthToken);
        AuthToken compareTest = atDao.find(bestAuthToken.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(bestAuthToken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        atDao.insert(bestAuthToken);
        assertThrows(DataAccessException.class, ()-> atDao.insert(bestAuthToken));
    }

    @Test
    public void findPass() throws DataAccessException {
        atDao.insert(bestAuthToken);
        atDao.find(bestAuthToken.getAuthToken());
        AuthToken compareTest = atDao.find(bestAuthToken.getAuthToken());
        assertEquals(bestAuthToken, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        atDao.insert(bestAuthToken);
        AuthToken compareTest = atDao.find(bestAuthToken.getAuthToken());
        assertEquals(bestAuthToken, compareTest);
    }


}
