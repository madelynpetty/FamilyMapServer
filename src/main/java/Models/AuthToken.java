/**
 * When a user logs in to your server, the login request sent from the client to the server must include the user’s username and password.  If login succeeds, your server should generate a unique “authorization token” string for the user, and return it to the client.  Subsequent requests sent from the client to your server should include the auth token so your server can determine which user is making the request.  This allows non-login requests to be made without having to include the user’s credentials, thus reducing the likelihood that a hacker will intercept them.  For this scheme to work, your server should store auth tokens in its database and record which user each token belongs to.  Also, to protect against the possibility that a hacker might intercept a user’s auth token, it is important that each new login request generate and return a unique auth token.  It should also be possible for the same user to be logged in from multiple clients at the same time, which means that the same user could have multiple active auth tokens simultaneously.
 */
public class AuthToken {
    private String authToken;

    /**
     * Assigns authToken to the person
     * @param authToken String
     */
    public AuthToken(String authToken) {
        this.authToken = authToken;
    }
}
