package Services.Request;

/**
 * Takes the request and turns it into a Java object
 */
public class LoginRequest {
    private String username;
    private String password;
    private String authToken;
    private String personID;
    private boolean success = false;
}
