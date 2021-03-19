package Server.Handler;

import Utils.StringUtil;
import Services.Request.RegisterRequest;
import Services.Service.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            RegisterService registerService = new RegisterService();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

            Gson gson = new Gson();
            RegisterRequest registerRequest = gson.fromJson(json, RegisterRequest.class); //get json from request
            registerService.callRegisterService(registerRequest);
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
            exchange.getResponseBody().close(); //this is the respBody OutputStream from earlier
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }
}
