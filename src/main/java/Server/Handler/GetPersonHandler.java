package Server.Handler;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import Models.AuthToken;
import Services.Result.PersonResult;
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

public class GetPersonHandler implements HttpHandler {

    //for /person/[personID]
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                PersonService personService = new PersonService();

                String personID = exchange.getRequestURI().toString().substring(8);

                String token = "";
                Headers reqHeaders = exchange.getRequestHeaders();
                Database database;
                Connection conn;
                AuthTokenDAO authTokenDAO;
                AuthToken authToken;

                try {
                    database = new Database();
                    conn = database.openConnection();
                } catch (Exception e) {
                    throw new Exception("cannot get a connection to the database.");
                }

                if (reqHeaders.containsKey("Authorization")) {
                    token = reqHeaders.getFirst("Authorization");
                    try {
                        authTokenDAO = new AuthTokenDAO(conn);
                        authToken = authTokenDAO.find(token);
                    } catch (Exception e) {
                        throw new Exception("Invalid Authorization token (you may not be logged in)");
                    }
                } else {
                    throw new Exception("Authorization token missing.");
                }

                Gson gson = new Gson();
                PersonResult personResult = personService.findPersonWithMatchingPersonID(personID);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String response = gson.toJson(personResult);
                OutputStream outputStream = exchange.getResponseBody();
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_FORBIDDEN, 0);
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Gson gson = new Gson();
            PersonResult personResult = new PersonResult(e.getMessage(), false);
            String response = gson.toJson(personResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        finally {
            exchange.getResponseBody().close();
        }
    }
}