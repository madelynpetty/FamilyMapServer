package Server.Handler;

import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.AuthToken;
import Services.Request.FillRequest;
import Services.Result.FillResult;
import Services.Result.LoginResult;
import Services.Service.FillService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.sql.Connection;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            FillService fillService;
            FillRequest fillRequest;

            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                String[] uriArgs = exchange.getRequestURI().toString().substring("/fill/".length()).split("/");

                String username = uriArgs[0];
                int generationInt = 4;

                if (uriArgs.length > 1) {
                    String generations = uriArgs[1];
                    try {
                        generationInt = Integer.parseInt(generations);
                        if (generationInt < 0) {
                            throw new Exception("Error: generations must be a positive number.");
                        }
                    }
                    catch (Exception e) {
                        throw new Exception("Error: Generations must be a positive integer.");
                    }
                }

                Database database;
                Connection conn;

                UserDAO userDAO;
                try {
                    database = new Database();
                    conn = database.getConnection();
                    userDAO = new UserDAO(conn);
                    if (userDAO.find(username) == null) {
                        throw new Exception("Error: Username does not exist in the database.");
                    }
                }
                catch (Exception e) {
                    throw new Exception("Error: Username does not exist in the database.");
                }

                try {
                    database.closeConnection(false);
                }
                catch (Exception e) {
                    throw new Exception("Error: could not close the database.");
                }

                fillRequest = new FillRequest(username, generationInt);

                Gson gson = new Gson();
                fillService = new FillService();
                FillResult fillResult = fillService.callFillService(fillRequest);
                String response = gson.toJson(fillResult);
                OutputStream outputStream = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            }
            else {
                throw new Exception("Error: Unable to fill");
            }
        }
        catch(Exception e) {
            Gson gson = new Gson();
            FillResult fillResult = new FillResult(e.getMessage(), false);
            String response = gson.toJson(fillResult);
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
