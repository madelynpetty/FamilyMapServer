package DataAccess;

import Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;


public class UserDAOTest {
    private Database db;
    private User bestUser;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws Exception {
        db = new Database();
        bestUser = new User("Gale", "pass123", "gale@gmail.com",
                "Gale", "Johnson", "m", "Gale123A");
        Connection conn = db.getConnection();
        DatabaseDAO databaseDAO = new DatabaseDAO(conn);
        databaseDAO.clearTables();
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(bestUser);
        assertThrows(DataAccessException.class, ()-> uDao.insert(bestUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        uDao.insert(bestUser);
        uDao.find(bestUser.getUsername());
        User compareTest = uDao.find(bestUser.getUsername());
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void loginPass() throws Exception {
        uDao.insert(bestUser);
        User user = uDao.login("Gale", "pass123");
        assertEquals(user, bestUser);
    }

    @Test
    public void loginFail() throws Exception {
        assertThrows(Exception.class, ()-> uDao.login("username", "passwordddd"));
    }

}
