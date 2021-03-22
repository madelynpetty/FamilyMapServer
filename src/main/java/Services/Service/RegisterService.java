package Services.Service;
import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.User;
import Services.Request.FillRequest;
import Utils.StringUtil;
import Services.Result.RegisterResult;
import Services.Request.RegisterRequest;

import java.sql.Connection;

/**
 * The RegisterService makes the request and returns the result
 */
public class RegisterService {
    private Database database = new Database();

    /**
     * Returns the result of calling the Register Service
     * @param request The register request
     * @return The result of calling the register service
     */
    public RegisterResult callRegisterService(RegisterRequest request) throws Exception {
        Connection conn = null;
        RegisterResult registerResult;
        try {
            conn = database.getConnection();
            UserDAO userDAO = new UserDAO(conn);

            String personID = StringUtil.getRandomPersonID();
            User user = new User(request.getUsername(), request.getPassword(), request.getEmail(),
                    request.getFirstName(), request.getLastName(), request.getGender(), personID);
            userDAO.insert(user);

            //logging in, so we create an authToken here
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            String authTokenID = StringUtil.getRandomAuthToken();
            AuthToken authToken = new AuthToken(request.getUsername(), authTokenID);
            authTokenDAO.insert(authToken);

            registerResult = new RegisterResult(authTokenID, user.getUsername(), user.getPersonID());

            if (conn != null) {
                database.closeConnection(true);
            }
            //now call fill service
            FillRequest fillRequest = new FillRequest(request.getUsername(), 4);
            FillService fillService = new FillService();
            fillService.callFillService(fillRequest);

            return registerResult;
        }
        catch (Exception e) {
            throw new Exception("Error: Could not register user.");
        }
        finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    database.closeConnection(true);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
