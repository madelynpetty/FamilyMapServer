package Services.Result;

/**
 * The response based on the success of the service
 */
public class RegisterResult {
    public String authtoken;
    public String personID;
    public String username;
    public boolean success = false;

    public RegisterResult(String authtoken, String username, String personID) {
        this.personID = personID;
        this.username = username;
        this.authtoken = authtoken;
        success = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof RegisterResult) {
            RegisterResult registerResult = (RegisterResult) o;
            return registerResult.success == (success) &&
                    registerResult.authtoken.equals(authtoken) &&
                    registerResult.personID.equals(personID) &&
                    registerResult.username.equals(username);
        } else {
            return false;
        }
    }

}