package Server.Handler;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import Models.AuthToken;
import Services.Result.EventListResult;
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
import java.sql.Connection;

public class GetEventHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                EventService eventService = new EventService();

                String eventID = exchange.getRequestURI().toString().substring("/event/".length());

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
                    throw new Exception("cannot get a connection to the database.");
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
                EventResult eventResult = eventService.getEvent(eventID);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String response = gson.toJson(eventResult);
                OutputStream outputStream = exchange.getResponseBody();
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_FORBIDDEN,0);
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Gson gson = new Gson();
            EventResult eventResult = new EventResult(e.getMessage());
            String response = gson.toJson(eventResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        finally {
            exchange.getResponseBody().close();
        }
    }
}
