package Services.Result;

import Models.User;
import Services.Service.ClearService;

/**
 * The response based on the success of the service
 */
public class ClearResult {
    private String message;
    private boolean success = false;

    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof ClearResult) {
            ClearResult cr = (ClearResult) o;
            return cr.success == (success);
        } else {
            return false;
        }
    }
}











/*
    Database db = new Database();
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
 */