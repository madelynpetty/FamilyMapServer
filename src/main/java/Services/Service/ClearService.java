package Services.Service;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.User;
import Services.Result.ClearResult;

import java.sql.Connection;

/**
 * The ClearService makes the request and returns the result
 */
public class ClearService {
    private Database database = new Database();

    /**
     * Returns the result of calling the Clear Service
     */
    public ClearResult callClearService() throws Exception {
        ClearResult clearResult;
        try {
            Connection conn = database.getConnection();
            database.clearTables();

            clearResult = new ClearResult("Clear succeeded.", true);
        }
        catch (Exception e) {
            clearResult = new ClearResult("Error: Could not clear the database", false);
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
}
