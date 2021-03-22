package Server.Handler;

import Services.Result.ErrorResult;
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

                String response = gson.toJson(loginResult);
                OutputStream outputStream = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();

                complete = true;
            }
            if (!complete) {
                throw new Exception("Error: Unable to login");
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
