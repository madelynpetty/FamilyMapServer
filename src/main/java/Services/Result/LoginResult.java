package Services.Result;

/**
 * The response based on the success of the service
 */
public class LoginResult {
    public String authtoken;
    public String username;
    public String personID;
    public boolean success = false;

    public LoginResult(String authtoken, String username, String personID) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        success = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof LoginResult) {
            LoginResult loginResult = (LoginResult) o;
            return loginResult.success == (success) &&
                    loginResult.personID.equals(personID) &&
                    loginResult.username.equals(username) &&
                    loginResult.authtoken.equals(authtoken);
        } else {
            return false;
        }
    }
}