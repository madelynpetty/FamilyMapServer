package Services.Service;
import DataAccess.*;
import Models.AuthToken;
import Models.User;
import Services.Result.LoadResult;
import Services.Request.LoadRequest;
import Services.Result.LoginResult;
import Utils.LoadDataUtils;
import Utils.StringUtil;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * The LoadService makes the request and returns the result
 */
public class LoadService {
    /**
     * Returns the result of calling the Load Service
     *
     * @param request The load request
     * @return The result of calling the load service
     */
    public LoadResult callLoadService(LoadRequest request) throws Exception {
        System.out.println("Called callLoadService");
        Database database = new Database();
        LoadResult loadResult;
        try {
            Connection conn = database.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);

//            request.setUsers(LoadDataUtils.getUserArray());
//            request.setPersons(LoadDataUtils.getPersonArray());
//            request.setEvents(LoadDataUtils.getEventArray());

            userDAO.clear(); //don't need to capture the return value

            for (int i = 0; i < request.getUsers().size(); i++) {
                userDAO.insert(request.getUsers().get(i));
            }

            for (int i = 0; i < request.getPersons().size(); i++) {
                personDAO.insert(request.getPersons().get(i));
            }

            for (int i = 0; i < request.getEvents().size(); i++) {
                eventDAO.insert(request.getEvents().get(i));
            }

            loadResult = new LoadResult("Successfully added " + request.getUsers().size() +
                    " users, " + request.getPersons().size() + " persons, and " +
                    request.getEvents().size() + " events to the database.", true);

        } catch (DataAccessException e) {
            loadResult = new LoadResult("Error: could not connect to the database.", false);
//            throw new Exception("Error: cannot get connection to database");
        } finally {
            try {
                database.closeConnection(true);
            } catch (Exception e) {
                throw new Exception("Error: could not load data");
            }
        }

        return loadResult;
    }
}
