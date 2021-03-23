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

public class PersonDAOTest {

    private Database db;
    private Person bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws Exception {
        db = new Database();
        bestPerson = new Person("Gale123A", "Gale", "Gale",
                "Johnson", "m", "Mark123A", "Mother12", "Spouse12");
        Connection conn = db.getConnection();
        DatabaseDAO databaseDAO = new DatabaseDAO(conn);
        databaseDAO.clearTables();
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(bestPerson);
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
