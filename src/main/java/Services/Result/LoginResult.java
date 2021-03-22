package Services.Result;

/**
 * The response based on the success of the service
 */
public class LoginResult {
    public boolean success = false;
    public String authtoken;
    public String personID;
    public String username;

    public LoginResult(String authtoken, String username, String personID) {
        success = true;
        this.personID = personID;
        this.username = username;
        this.authtoken = authtoken;
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