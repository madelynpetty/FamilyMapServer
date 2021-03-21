package Server.Handler;

import Services.Result.ClearResult;
import Services.Service.ClearService;
import Utils.StringUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                ClearService clearService = new ClearService();

                clearService.callClearService();
                ClearResult clearResult = new ClearResult(clearService.getMessage(), clearService.getSuccess());
                //            String response = "{\n\"message\": " + clearService.getMessage() + "\n\"success\":" + clearService.getSuccess() + "\n}";

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Gson gson = new Gson();
                String response = gson.toJson(clearResult);
                OutputStream outputStream = exchange.getResponseBody();
                StringUtil.writeStringToStream(response, outputStream);

                outputStream.close();
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_FORBIDDEN, 0);
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Gson gson = new Gson();
            ClearResult clearResult = new ClearResult(e.getMessage(), false);
            String response = gson.toJson(clearResult);
            OutputStream outputStream = exchange.getResponseBody();
            StringUtil.writeStringToStream(response, outputStream);
            outputStream.close();
        }
    }

//    private void writeString(String str, OutputStream os) throws IOException {
//        OutputStreamWriter sw = new OutputStreamWriter(os);
//        BufferedWriter bw = new BufferedWriter(sw);
//        bw.write(str);
//        bw.flush();
//    }
}
