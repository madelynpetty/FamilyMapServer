package Server.Handler;

import Services.Result.RegisterResult;
import Utils.StringUtil;
import Services.Request.RegisterRequest;
import Services.Service.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            RegisterService registerService = new RegisterService();
            String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

            Gson gson = new Gson();
            RegisterRequest registerRequest = gson.fromJson(json, RegisterRequest.class); //get json from request
            RegisterResult registerResult = registerService.callRegisterService(registerRequest);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String response = gson.toJson(registerResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        catch (Exception e) {
            Gson gson = new Gson();
            RegisterResult registerResult = new RegisterResult(e.getMessage());
            String response = gson.toJson(registerResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
        }
        finally {
            exchange.getResponseBody().close();
        }
    }
}
