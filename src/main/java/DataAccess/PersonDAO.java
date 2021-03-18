package DataAccess;
import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for the person. Inserts, deletes, and clears the person table in the database.
 */
public class PersonDAO {
    private final Connection conn;

    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts the person into the person table in the database
     * @param person the person to be added to the database
     * @return a boolean of whether or not the event was added to the person table in the database
     */
    public void insert(Person person) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO person (personID, username, firstName, lastName, gender, " +
                "fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    public Person find(String personID) throws DataAccessException {
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("username"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("fatherID"),
                        rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Clears the person table in the database
     * @return a boolean of whether or not the person table in the database was cleared
     */
    public boolean clear() {
        return false;
    }

}
