package Server.Handler;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import Models.AuthToken;
import Services.Request.EventRequest;
import Services.Result.ErrorResult;
import Services.Result.EventListResult;
import Services.Service.EventService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;

public class EventHandler implements HttpHandler {
    private Database database;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                EventService eventService = new EventService();
                String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

                String token = "";
                Headers reqHeaders = exchange.getRequestHeaders();
                Connection conn;
                AuthTokenDAO authTokenDAO;
                AuthToken authToken;

                try {
                    database = new Database();
                    conn = database.openConnection();
                } catch (Exception e) {
                    throw new Exception("Error: cannot get a connection to the database.");
                }

                if (reqHeaders.containsKey("Authorization")) {
                    token = reqHeaders.getFirst("Authorization");
                    try {
                        authTokenDAO = new AuthTokenDAO(conn);
                        authToken = authTokenDAO.find(token);
                        if (authToken == null) {
                            throw new Exception("Error: No matching authtoken in the database");
                        }
                    } catch (Exception e) {
                        throw new Exception("Error: Invalid Authorization token (you may not be logged in)");
                    }
                } else {
                    throw new Exception("Error: Authorization token missing.");
                }

                Gson gson = new Gson();
                EventRequest eventRequest = gson.fromJson(json, EventRequest.class); //get json from request
                EventListResult eventListResult = eventService.callEventService(authToken.getAuthToken());
                String response = gson.toJson(eventListResult);
                OutputStream outputStream = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            }
            else {
                throw new Exception("Error: Unable to return event with given AuthToken");
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
                database.closeConnection(false);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
