package Server.Handler;

import Services.Result.LoginResult;
import Utils.StringUtil;
import Services.Request.LoginRequest;
import Services.Service.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            boolean complete = false;
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                LoginService loginService = new LoginService();
                String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

                Gson gson = new Gson();
                LoginRequest loginRequest = gson.fromJson(json, LoginRequest.class); //get json from request
                LoginResult loginResult = loginService.callLoginService(loginRequest);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String response = gson.toJson(loginResult);
                OutputStream outputStream = exchange.getResponseBody();
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();

                complete = true;
            }
            if (!complete) {
                throw new Exception("Error: Unable to login");
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
            Gson gson = new Gson();
            LoginResult loginResult = new LoginResult(e.getMessage());
            String response = gson.toJson(loginResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        finally {
            exchange.getResponseBody().close();
        }

    }
}
