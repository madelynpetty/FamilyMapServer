package Services.Result;

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

    public String getMessage() {
        return message;
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