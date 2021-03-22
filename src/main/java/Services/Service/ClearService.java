package Services.Service;
import DataAccess.Database;
import DataAccess.DatabaseDAO;
import Services.Result.ClearResult;

import java.sql.Connection;

/**
 * The ClearService makes the request and returns the result
 */
public class ClearService {

    /**
     * Returns the result of calling the Clear Service
     */
    public ClearResult callClearService() throws Exception {
        ClearResult clearResult;
        Database database = null;
        boolean doCommit = false;

        try {
            database = new Database();
            Connection conn = database.openConnection();
            DatabaseDAO databaseDAO = new DatabaseDAO(conn);
            databaseDAO.clearTables();

            doCommit = true;
            clearResult = new ClearResult("Clear succeeded.", true);
        }
        catch (Exception e) {
            doCommit = false;
            clearResult = new ClearResult("Error: Could not clear the database", false);
        }
        finally {
            try {
                database.closeConnection(doCommit);
            } catch (Exception e) {
                throw new Exception("Error: Could not close database");
            }
        }

        return clearResult;
    }
}
