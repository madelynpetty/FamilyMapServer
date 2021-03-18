package Services.Service;
import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.User;
import Services.Result.LoginResult;
import Services.Request.LoginRequest;

import javax.xml.crypto.Data;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.Random;
import java.net.HttpURLConnection;

/**
 * The LoginService makes the request and returns the result
 */
public class LoginService {
    public LoginService() {

    }
    private LoginResult loginResult = null;
    Database database = new Database();

    /**
     * Returns the result of calling the Login Service
     * @param request The login request
     * @return The result of calling the login service
     */
    public LoginResult callLoginService(LoginRequest request) throws Exception {
        System.out.println("Called callLoginService");
        try {
            Connection conn = database.getConnection();

            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.login(request.getUsername(), request.getPassword());

            if (user == null) {
                loginResult = new LoginResult("Error: Invalid username/password combination");
            }
            else {
                //create authtoken here for logged in user
                AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
                AuthToken authToken = null;
                String newAuthToken = null;
                do {
                    //TODO CHECK TO MAKE SURE THIS CODE WORKS
                    byte[] array = new byte[7]; // length is bounded by 7
                    new Random().nextBytes(array);
                    newAuthToken = new String(array, Charset.forName("UTF-8"));
                    authToken = authTokenDAO.find(newAuthToken);
                } while (authToken != null && authToken.getAuthToken() == newAuthToken);

                authToken = new AuthToken(request.getUsername(), newAuthToken);
                authTokenDAO.insert(authToken);

                loginResult = new LoginResult(authToken.getAuthToken(), user.getUsername(), user.getPersonID());

            }
        }
        catch (DataAccessException e) {
            throw new Exception("Error: cannot get connection to database");
        }
        catch (Exception e) {
            throw new Exception("Error: Something went wrong with LoginService");
        }
        finally {
            try {
                database.closeConnection(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return loginResult;
    }
}
