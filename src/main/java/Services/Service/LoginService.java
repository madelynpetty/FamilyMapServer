package Services.Service;
import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.User;
import Utils.StringUtil;
import Services.Result.LoginResult;
import Services.Request.LoginRequest;

import java.sql.Connection;

/**
 * The LoginService makes the request and returns the result
 */
public class LoginService {
    public LoginService() {

    }
    Database database = new Database();

    /**
     * Returns the result of calling the Login Service
     * @param request The login request
     * @return The result of calling the login service
     */
    public LoginResult callLoginService(LoginRequest request) throws Exception {
        Connection conn = null;
        boolean doCommit = false;
        try {
            conn = database.openConnection();

            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.login(request.getUsername(), request.getPassword());

            if (user == null) {
                throw new Exception("Error: Invalid username/password combination");
            }
            else {
                AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
                String authTokenID = StringUtil.getRandomAuthToken(conn);
                AuthToken authToken = new AuthToken(request.getUsername(), authTokenID);
                authTokenDAO.insert(authToken);

                doCommit = true;
                return new LoginResult(authToken.getAuthToken(), user.getUsername(), user.getPersonID());
            }
        }
        catch (DataAccessException e) {
            doCommit = false;
            throw new Exception("Error: cannot get connection to database");
        }
        catch (Exception e) {
            doCommit = false;
            throw e;
        }
        finally {
            try {
                database.closeConnection(doCommit);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
