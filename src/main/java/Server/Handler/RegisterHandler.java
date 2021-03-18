package Server.Handler;

import Services.Request.RegisterRequest;
import Services.Service.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            RegisterService registerService = new RegisterService();
            String json = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
//            System.out.println("Json:" + json);

            Gson gson = new Gson();
            RegisterRequest registerRequest = gson.fromJson(json, RegisterRequest.class); //get json from request
            registerService.callRegisterService(registerRequest);
        }
        catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
