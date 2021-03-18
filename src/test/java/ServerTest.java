import static org.junit.jupiter.api.Assertions.*;

import Server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerTest {
    private Server server;

    @Test
    private void run (String portNum) {
        server = new Server();
//        server.run("8080");
    }

}