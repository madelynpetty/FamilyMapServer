package Server.Handler;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import Models.AuthToken;
import Services.Request.PersonRequest;
import Services.Result.ErrorResult;
import Services.Result.PersonListResult;
import Services.Service.PersonService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;

public class PersonHandler implements HttpHandler {

    //for /person
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                PersonService personService = new PersonService();
                String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

                String token = "";
                Headers reqHeaders = exchange.getRequestHeaders();
                Database database;
                Connection conn;
                AuthTokenDAO authTokenDAO;
                AuthToken authToken;

                try {
                    database = new Database();
                    conn = database.openConnection();
                    authTokenDAO = new AuthTokenDAO(conn);
                } catch (Exception e) {
                    throw new Exception("Error: cannot get a connection to the database.");
                }

                if (reqHeaders.containsKey("Authorization")) {
                    token = reqHeaders.getFirst("Authorization");
                    try {
                        authToken = authTokenDAO.find(token);
                    } catch (Exception e) {
                        throw new Exception("Error: Invalid Authorization token (you may not be logged in)");
                    }
                } else {
                    throw new Exception("Error: Authorization token missing.");
                }

                Gson gson = new Gson();
                PersonListResult personListResult = null;

                personListResult = personService.returnFamilyMembers(authToken.getAuthToken());
                String response = gson.toJson(personListResult);
                OutputStream outputStream = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            }
            else {
                throw new Exception("Error: Unable to return person's family members");
            }
        }
        catch (Exception e) {
            Gson gson = new Gson();
            ErrorResult errorResult = new ErrorResult(e.getMessage());
            String response = gson.toJson(errorResult);
            OutputStream outputStream = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        finally {
            try {
                exchange.getResponseBody().close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
