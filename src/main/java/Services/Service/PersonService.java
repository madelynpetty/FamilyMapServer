package Services.Service;
import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import DataAccess.PersonDAO;
import Models.AuthToken;
import Models.Person;
import Services.Result.PersonListResult;
import Services.Result.PersonResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * The PersonService makes the request and returns the result
 */
public class PersonService {
    private final Database database = new Database();

    /**
     * Returns the result of calling the Person Service
     * @param personID The personID
     * @return The result of calling the person service
     */

    //FOR THE PATH /person/personID
    public PersonResult findPersonWithMatchingPersonID(String personID) {
        PersonResult personResult;
        try {
            Connection conn = database.getConnection();
            PersonDAO personDAO = new PersonDAO(conn);

            Person person = personDAO.find(personID);

            if (person == null) {
                System.out.println("Error: There is no matching person with personID " + personID + " in the database");
                personResult = null;
            }
            else {
                personResult = new PersonResult(person.getPersonID(), person.getAssociatedUsername(),
                        person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(),
                        person.getMotherID(), person.getSpouseID(), true);
            }
        }
        catch (Exception e) {
            personResult = new PersonResult("Error: There are no people in the database that match the given personID of " + personID, false);
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

    //for the path /person
    public PersonListResult returnFamilyMembers(String providedAuthToken) {
        PersonListResult personListResult;
        ArrayList<Person> familyMembers;
        try {
            Connection conn = database.getConnection();
            PersonDAO personDAO = new PersonDAO(conn);
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);

            AuthToken authToken = authTokenDAO.find(providedAuthToken);
            familyMembers = personDAO.findFamilyMembersWithUsername(authToken.getUsername());

            personListResult = new PersonListResult(familyMembers);
        }
        catch (Exception e) {
            personListResult = new PersonListResult("Error: There are no family members in the database that match the given person");
        }
        finally {
            try {
                database.closeConnection(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return personListResult;
    }
}
