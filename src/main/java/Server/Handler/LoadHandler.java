package Server.Handler;

import Services.Request.LoadRequest;
import Services.Result.LoadResult;
import Services.Service.LoadService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {

                LoadService loadService = new LoadService();
                String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

                Gson gson = new Gson();
                LoadRequest loadRequest = gson.fromJson(json, LoadRequest.class); //get json from request
                LoadResult loadResult = loadService.callLoadService(loadRequest);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String response = gson.toJson(loadResult);
                OutputStream outputStream = exchange.getResponseBody();
                StringUtil.writeStringToStream(response, outputStream);
                outputStream.close();
            }
            else {
                throw new Exception("Error: Unable to load");
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Gson gson = new Gson();
            LoadResult loadResult = new LoadResult(e.getMessage(), false);
            String response = gson.toJson(loadResult);
            OutputStream outputStream = exchange.getResponseBody();
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
