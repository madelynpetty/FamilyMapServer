package Service;

import DataAccess.*;
import Models.AuthToken;
import Models.Person;
import Services.Result.PersonListResult;
import Services.Service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {
    private Database database;
    private Connection conn;
    private PersonService personService;

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

        personService = new PersonService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        database.closeConnection(false);
    }

    @Test
    public void findPersonWithMatchingIDPass() throws Exception {
        Person person = new Person("person1", "maddie", "madelyn",
                "petty", "f", "dad12", "mom12", "spouse12");
        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(person);

        assertEquals(person, personService.findPersonWithMatchingPersonID("person1"));
        assertThrows(Exception.class, ()->personService.findPersonWithMatchingPersonID("person1"));
    }

    @Test
    public void findPersonWithMatchingIDFail() throws Exception {
        assertThrows(Exception.class, ()-> personService.findPersonWithMatchingPersonID(null));
    }

    @Test
    public void returnFamilyMembersPass() throws Exception {
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
        AuthToken authToken = new AuthToken("maddie", "1234");
        authTokenDAO.insert(authToken);

        Person person1 = new Person("person1", "maddie", "madelyn",
                "petty", "f", "dad12", "mom12", "spouse12");
        Person person2 = new Person("person2", "maddie", "bob",
                "theBuilder", "m", "dad21", "mom21", "spouse21");

        PersonDAO personDAO = new PersonDAO(conn);
        personDAO.insert(person1);
        personDAO.insert(person2);

        PersonListResult personListResult = personService.returnFamilyMembers("1234");

        assertEquals(personListResult, personService.returnFamilyMembers("1234"));
        assertThrows(Exception.class, ()->personService.returnFamilyMembers("1234"));
    }

    @Test
    public void returnFamilyMembersFail() throws Exception {
        assertThrows(Exception.class, ()-> personService.returnFamilyMembers("1234"));
    }
}
