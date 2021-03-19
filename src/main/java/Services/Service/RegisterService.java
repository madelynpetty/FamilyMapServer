package Services.Service;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.User;
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
        try {
            Connection conn = database.getConnection();
            UserDAO userDAO = new UserDAO(conn);

            String personID = StringUtil.getRandomID();
            User user = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender(), personID);
            userDAO.insert(user);
            //now call fill service
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            try {
                database.closeConnection(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
