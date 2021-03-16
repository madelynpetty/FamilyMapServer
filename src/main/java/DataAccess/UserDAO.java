package DataAccess;
import Models.User;
/**
 * Data Access Object for the user. Inserts, deletes, and clears the user table in the database.
 */
public class UserDAO {
    /**
     * Inserts the user into the user table in the database
     *
     * @param user the user to be added to the database
     * @return a boolean of whether or not the user was added to the user table in the database
     */
    public boolean insert(User user) {
        return false;
    }

    /**
     * Deletes the user from the user table in the database
     *
     * @param user the user to be deleted from the database
     * @return a boolean of whether or not the user was deleted from the user table in the database
     */
    public boolean delete(User user) {
        return false;
    } //don't think i need this

    public boolean find(User user) {
        return false;
    }

    /**
     * Clears the user table in the database
     *
     * @return a boolean of whether or not the user table in the database was cleared
     */
    public boolean clear() {
        return false;
    }
}