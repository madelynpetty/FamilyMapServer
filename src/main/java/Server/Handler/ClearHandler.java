package Server.Handler;

import Services.Result.ClearResult;
import Services.Service.ClearService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                ClearService clearService = new ClearService();

                ClearResult clearResult = clearService.callClearService();

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Gson gson = new Gson();
                String response = gson.toJson(clearResult);
                OutputStream outputStream = exchange.getResponseBody();
                StringUtil.writeStringToStream(response, outputStream);

                outputStream.close();
            } else {
                throw new Exception("Error: Unable to clear");
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Gson gson = new Gson();
            ClearResult clearResult = new ClearResult(e.getMessage(), false);
            String response = gson.toJson(clearResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        } finally {
            try {
                exchange.getResponseBody().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
