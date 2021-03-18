package Server.Handler;

import Services.Request.LoginRequest;
import Services.Service.LoginService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.net.URI;

public class LoginHandler implements HttpHandler {
    public LoginHandler() {

    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            LoginService loginService = new LoginService();
            String json = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
//            System.out.println("Json:" + json);

            Gson gson = new Gson();
            LoginRequest loginRequest = gson.fromJson(json, LoginRequest.class); //get json from request
            loginService.callLoginService(loginRequest);
        }
        catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
