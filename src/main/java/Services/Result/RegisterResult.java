package Services.Result;

/**
 * The response based on the success of the service
 */
public class RegisterResult {
    public boolean success = false;
    public String authToken;
    public String personID;
    public String username;

    public String message;

    public RegisterResult(String message) {
        success = false;
        this.message = message;
        personID = null;
        username = null;
        authToken = null;
    }

    public RegisterResult(String authToken, String username, String personID) {
        message = null;
        success = true;
        this.personID = personID;
        this.username = username;
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

}