package Server.Handler;

import Utils.StringUtil;
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

public class DefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String uri = exchange.getRequestURI().toString();
            String path;
            if (uri.equals(null) || uri.equals("/")) {
                path = "web/index.html";

                String respData = StringUtil.getStringFromInputStream(exchange.getRequestBody());
                OutputStream respBody = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                StringUtil.writeStringToStream(respData, respBody);
                Path filePath = FileSystems.getDefault().getPath(path);
                Files.copy(filePath, respBody);
                respBody.close();
            }
            else {
                path = "web/HTML/404.html";

                String respData = StringUtil.getStringFromInputStream(exchange.getRequestBody());
                OutputStream respBody = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                StringUtil.writeStringToStream(respData, respBody);
                Path filePath = FileSystems.getDefault().getPath(path);
                Files.copy(filePath, respBody);
                respBody.close();
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
            exchange.getResponseBody().close(); //this is the respBody OutputStream from earlier
            e.printStackTrace();
            try {
                throw new Exception("Error: default handle did not work");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
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

//        try {
//
//            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
//                Headers reqHeaders = exchange.getRequestHeaders();
//
//                if (reqHeaders.containsKey("Authorization")) {
//                    String authToken = reqHeaders.getFirst("Authorization");
//                    if (authToken.equals("/")/*the one we are expecting goes here*/) {
//                        //Extracting JSON string from HTTP request body
//                        StringUtil stringUtil = new StringUtil();
//                        String respData = stringUtil.getStringFromInputStream(exchange.getRequestBody());
//
//                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//                        OutputStream respBody = exchange.getResponseBody();
//                        writeString(respData, respBody);
//                        respBody.close();
//                    }
//                    else {
//                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
//                    }
//                }
//                else {
//                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
//                }
//            }
//            else {
//                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//            }
//        }
//        catch(IOException e) {
//            throw new IOException("Error: fill handle did not work");
//        }
