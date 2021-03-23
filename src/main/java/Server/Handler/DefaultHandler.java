package Server.Handler;

import Utils.StringUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class DefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String uri = exchange.getRequestURI().toString();
            String path;
            if (uri.equals("/")) {
                path = "web/index.html";
            }
            else {
                path = "web" + uri;
            }

            Path filePath = FileSystems.getDefault().getPath(path);
            if (!Files.exists(filePath)) {
                throw new IOException("Error: Your search does not exist");
            }

            OutputStream respBody = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            Files.copy(filePath, respBody);
            respBody.close();
        }
        catch (IOException e) {
            OutputStream respBody = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            Files.copy(Path.of("web/HTML/404.html"), respBody);
            respBody.close();
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            exchange.getResponseBody().close(); //this is the respBody OutputStream from earlier
            e.printStackTrace();
            try {
                throw new Exception("Error: default handle did not work");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}