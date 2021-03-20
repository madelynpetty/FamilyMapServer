package Server;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Models.User;
import Server.Handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 10;
    public static void main(String args[]) {
        String portNum = "8080";
        new Server().run(portNum);
    }

    private void run (String portNum) {
        HttpServer server;
        System.out.println("Initializing HTTP Server");

        try {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNum)), MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);
        System.out.println("Creating contexts");
        server.createContext("/",  new DefaultHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());

        server.start();
        System.out.println("FamilyMapServer listening on port " + portNum);

    }
}
