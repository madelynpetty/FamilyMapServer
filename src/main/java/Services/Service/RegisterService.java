package Services.Service;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.User;
import Services.Result.RegisterResult;
import Services.Request.RegisterRequest;

import java.sql.Connection;

/**
 * The RegisterService makes the request and returns the result
 */
public class RegisterService {
    Database database = new Database();

    /**
     * Returns the result of calling the Register Service
     * @param request The register request
     * @return The result of calling the register service
     */
    public RegisterResult callRegisterService(RegisterRequest request) throws DataAccessException {
//        try {
//            Connection conn = database.getConnection();
//            UserDAO userDAO = new UserDAO(conn);
//            User user = new User(request.getUsername(), request.getPassword(), //etc)
//            userDAO.insert();
//            //now call fill service
//        }
//        catch () {
//
//        }




        //close connection
        return null;
    }
}
