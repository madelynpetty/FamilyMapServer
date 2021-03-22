package Server.Handler;

import Services.Result.ErrorResult;
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
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {

                RegisterService registerService = new RegisterService();
                String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

                Gson gson = new Gson();
                RegisterRequest registerRequest = gson.fromJson(json, RegisterRequest.class); //get json from request
                RegisterResult registerResult = registerService.callRegisterService(registerRequest);

                String response = gson.toJson(registerResult);
                OutputStream outputStream = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            }
            else {
                throw new Exception("Error: Unable to register user");
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
