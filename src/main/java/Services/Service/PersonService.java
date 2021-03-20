package Services.Service;
import DataAccess.Database;
import DataAccess.PersonDAO;
import DataAccess.UserDAO;
import Models.Person;
import Models.User;
import Services.Result.ClearResult;
import Services.Result.PersonResult;
import Services.Request.PersonRequest;

import java.sql.Connection;

/**
 * The PersonService makes the request and returns the result
 */
public class PersonService {
    private Database database = new Database();

    /**
     * Returns the result of calling the Person Service
     * @param request The person request
     * @return The result of calling the person service
     */

    //FOR THE PATH /person/personID
    public PersonResult findPersonWithMatchingPersonID(PersonRequest request) {
        PersonResult personResult;
        try {
            Connection conn = database.getConnection();
            PersonDAO personDAO = new PersonDAO(conn);

            Person person = personDAO.find(request.getPersonID());

            if (person == null) {
                System.out.println("There is no matching person with personID " + request.getPersonID() + " in the database");
                personResult = null;
            }
            else {
                personResult = new PersonResult(person.getPersonID(), person.getUsername(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
            }
        }
        catch (Exception e) {
            personResult = new PersonResult("Error: There are no people in the database that match the given personID", false);
        }
        finally {
            try {
                database.closeConnection(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return personResult;
    }

    public PersonResult returnFamilyMembers(PersonRequest request) {
        return null;
    }
}
