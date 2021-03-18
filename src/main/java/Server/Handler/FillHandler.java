package Server.Handler;

import Server.StringUtil;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                Headers reqHeaders = exchange.getRequestHeaders();

                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");
                    if (authToken.equals("")/*the one we are expecting goes here*/) {
                        //Extracting JSON string from HTTP request body
                        StringUtil stringUtil = new StringUtil();
                        String respData = stringUtil.getStringFromInputStream(exchange.getRequestBody());

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        OutputStream respBody = exchange.getResponseBody();
                        writeString(respData, respBody);
                        respBody.close();
                    }
                    else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
                    }
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
                }
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        }
        catch(IOException e) {
            throw new IOException("Error: fill handle did not work");
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(sw);
        bw.write(str);
        bw.flush();
    }
}
