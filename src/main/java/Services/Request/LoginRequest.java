package Services.Request;

/**
 * Takes the request and turns it into a Java object
 */
public class LoginRequest {
    private String username;
    private String password;

    private boolean success = false;

    public LoginRequest(String username, String password) throws Exception {
        if (username == null || username.length() == 0) {
            throw new Exception("Error: Username is required");
        }
        if (password == null || password.length() == 0) {
            throw new Exception("Error: Password is required");
        }

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
