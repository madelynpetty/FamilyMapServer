package Server.Handler;

import Services.Request.PersonRequest;
import Services.Result.PersonResult;
import Services.Service.PersonService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            PersonService personService = new PersonService();
            String json = StringUtil.getStringFromInputStream(exchange.getRequestBody());

            Gson gson = new Gson();
            PersonRequest personRequest = gson.fromJson(json, PersonRequest.class); //get json from request

            PersonResult personResult;
            if (exchange.getRequestMethod().equals("/person")) {
                personResult = personService.findPersonWithMatchingPersonID(personRequest);
            }
            else {
                personResult = personService.returnFamilyMembers(personRequest);
            }

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String response = gson.toJson(personResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
        catch (Exception e) {
            Gson gson = new Gson();
            PersonResult personResult = new PersonResult(e.getMessage(), false);
            String response = gson.toJson(personResult);
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
