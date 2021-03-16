/**
 * Data Access Object for the authToken. Inserts, deletes, and clears the authToken table in the database.
 */
public class AuthTokenDAO {
    /**
     * Inserts an authToken into the authToken table in the database
     * @param authtoken the authToken to be added to the database
     * @return a boolean of whether or not the authToken was added to the authToken table in the database
     */
    public boolean insert(AuthToken authtoken) {
        return false;
    }

    /**
     * Deletes the authToken from the authToken table in the database
     * @param authtoken the authToken to be deleted from the database
     * @return a boolean of whether or not the authToken was deleted from the authToken table in the database
     */
    public boolean delete(AuthToken authtoken) {
        return false;
    }

    /**
     * Clears the authToken table in the database
     * @return a boolean of whether or not the authToken table in the database was cleared
     */
    public boolean clear() {
        return false;
    }

//    String username;
//    String password;
//    String authToken;
//    public String getUsername(User user) {
//        return user.getUsername();
//    }
//
//    public String getPassword(User user) {
//        return user.getPassword();
//    }
//
//    public String getAuthToken(User user) {
//        return user.getAuthToken();
//    }
}
