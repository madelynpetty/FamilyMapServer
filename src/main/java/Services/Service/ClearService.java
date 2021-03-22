package Services.Service;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Services.Result.ClearResult;

import java.sql.Connection;

/**
 * The ClearService makes the request and returns the result
 */
public class ClearService {
    private Database database = new Database();
    private static String message;
    private static boolean success;

    /**
     * Returns the result of calling the Clear Service
     */
    public ClearResult callClearService() throws Exception {
        ClearResult clearResult;
        try {
            Connection conn = database.getConnection();
            UserDAO userDAO = new UserDAO(conn);

            success = true;
            message = "Clear succeeded";
            clearResult = new ClearResult("Database was successfully cleared", userDAO.clear());
        }
        catch (Exception e) {
            success = false;
            message = "Error: Could not clear the database";
            clearResult = new ClearResult("Error: Could not clear the database", false);
//            throw e;
        }
        finally {
            try {
                database.closeConnection(true);
            }
            catch (Exception e){
                throw new Exception("Error: Could not close database");
            }
        }

        return clearResult;
    }

    public static String getMessage() {
        return message;
    }

    public static boolean getSuccess() {
        return success;
    }
}
