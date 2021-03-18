package Services.Result;

import DataAccess.Database;

/**
 * The response based on the success of the service
 */
public class ClearResult {
    Database db = new Database();

    public ClearResult() {
        try {
            db.openConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }



        try {
            db.closeConnection(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
