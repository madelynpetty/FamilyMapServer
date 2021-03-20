package Server.Handler;

import Models.Event;
import Services.Request.EventRequest;
import Services.Result.EventResult;
import Services.Service.EventService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            EventService eventService = new EventService();
            String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

            Gson gson = new Gson();
            EventRequest eventRequest = gson.fromJson(json, EventRequest.class); //get json from request
            EventResult eventResult = eventService.callEventService(eventRequest);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String response = gson.toJson(eventResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        catch (Exception e) {
            Gson gson = new Gson();
            EventResult eventResult = new EventResult(e.getMessage(), false);
            String response = gson.toJson(eventResult);
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
