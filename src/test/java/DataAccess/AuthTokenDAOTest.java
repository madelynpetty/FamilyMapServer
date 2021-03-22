package DataAccess;

import Models.AuthToken;
import Utils.RandomDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
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
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        database.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        atDao.insert(bestAuthToken);
        //So lets use a find method to get the event that we just put in back out
        AuthToken compareTest = atDao.find(bestAuthToken.getAuthToken());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(bestAuthToken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        atDao.insert(bestAuthToken);
        //but our sql table is set up so that "eventID" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
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
