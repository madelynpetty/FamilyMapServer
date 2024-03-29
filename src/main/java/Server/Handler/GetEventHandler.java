package Server.Handler;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import Models.AuthToken;
import Services.Result.ErrorResult;
import Services.Result.EventResult;
import Services.Service.EventService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.rmi.server.ExportException;
import java.sql.Connection;

public class GetEventHandler implements HttpHandler {
    private Database database;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                EventService eventService = new EventService();

                String eventID = exchange.getRequestURI().toString().substring("/event/".length());

                String token = "";
                Headers reqHeaders = exchange.getRequestHeaders();

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
                        if (authToken == null) {
                            throw new Exception("Error: cannot find authtoken in the database");
                        }
                    } catch (Exception e) {
                        throw new Exception("Error: Invalid Authorization token (you may not be logged in)");
                    }
                } else {
                    throw new Exception("Error: Authorization token missing.");
                }

                Gson gson = new Gson();
                EventResult eventResult = eventService.getEvent(eventID);
                if(!eventResult.getAssociatedUsername().equalsIgnoreCase(authToken.getUsername())) {
                    throw new Exception("Error: You cannot access events from people that you are not related to");
                }

                String response = gson.toJson(eventResult);
                OutputStream outputStream = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            } else {
                throw new Exception("Error: Unable to get family events");
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
