package DataAccess;

import Models.Person;
import Utils.RandomDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class PersonDAOTest {

    private Database db;
    private Person bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws Exception {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //and a new person with random data
        bestPerson = new Person("Gale123A", "Gale", "Gale",
                "Johnson", "m", "Mark123A", "Mother12", "Spouse12");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        DatabaseDAO databaseDAO = new DatabaseDAO(conn);
        databaseDAO.clearTables();
        //Then we pass that connection to the personDAO so it can access the database
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        pDao.insert(bestPerson);
        //So lets use a find method to get the person that we just put in back out
        Person compareTest = pDao.find(bestPerson.getPersonID());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        pDao.insert(bestPerson);
        //but our sql table is set up so that "person" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
        assertThrows(DataAccessException.class, ()-> pDao.insert(bestPerson));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.find(bestPerson.getPersonID());
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void findByUsernamePass() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.findByUsername(bestPerson.getAssociatedUsername());
        Person compareTest = pDao.findByUsername(bestPerson.getAssociatedUsername());
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void findByUsernameFail() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.findByUsername(bestPerson.getAssociatedUsername());
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void findFamilyMembersWithUsernamePass() throws DataAccessException {
        ArrayList<Person> bestPersons = new ArrayList<>();
        Person bestPerson1 = new Person("Gale123B", "Gale",
                "Gale", "Johnson", "m", "dad123A",
                "mom123A", "spouse12");
        bestPersons.add(bestPerson);
        bestPersons.add(bestPerson1);

        pDao.insert(bestPerson);
        pDao.insert(bestPerson1);
        ArrayList<Person> compareTest = pDao.findFamilyMembersWithUsername(bestPerson.getAssociatedUsername());

        assertEquals(compareTest, bestPersons);
    }

    @Test
    public void findFamilyMembersWithUsernameFail() throws DataAccessException {
        ArrayList<Person> bestPersons = new ArrayList<>();
        Person bestPerson1 = new Person("Gale123B", "Gale",
                "Gale", "Johnson", "m", "dad123A",
                "mom123A", "spouse12");

        bestPersons.add(bestPerson);
        bestPersons.add(bestPerson1);

        pDao.insert(bestPerson);
        ArrayList<Person> compareTest = pDao.findFamilyMembersWithUsername(bestPerson.getAssociatedUsername());

        assertNotEquals(compareTest, bestPersons);
    }

    @Test
    public void updatePass() throws DataAccessException {
        pDao.update(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertEquals(null, compareTest);
    }

    @Test
    public void updateFail() throws DataAccessException {
        Person person = new Person("Maddie12", "maddie", "Maddie",
                "Petty", "f", "Shey123", "Jody123", "Branson1");
        pDao.update(person);
        Person compareTest = pDao.find(person.getPersonID());
        assertNotEquals(person, compareTest);
    }

    @Test
    public void clearPass() throws DataAccessException, SQLException {
        pDao.insert(bestPerson);
        pDao.clear(bestPerson.getPersonID());
        Person notAPerson = pDao.find(bestPerson.getPersonID());
        assertEquals(notAPerson, bestPerson);
    }

    @Test
    public void clearFail() throws DataAccessException, SQLException {
        pDao.insert(bestPerson);
        pDao.clear(bestPerson.getAssociatedUsername());
        Person actualPerson = pDao.find(bestPerson.getPersonID()); //since we didn't actually clear anything out
        assertEquals(actualPerson, null);
    }

}
