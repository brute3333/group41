package za.co.wethinkcode.robotworlds.server.servers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleServerTest {
    private final InputStream sysIn = System.in;
    private final PrintStream sysOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private SimpleServer server;
    private Socket clientSocket;

    @BeforeEach
    void setUp() throws IOException {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        ServerSocket serverSocket = new ServerSocket(0); // find available port
        clientSocket = new Socket("localhost", serverSocket.getLocalPort());

        server = new SimpleServer(serverSocket.accept());
    }

    @AfterEach
    void tearDown() throws IOException {
        System.setIn(sysIn);
        System.setOut(sysOut);
        System.setErr(sysOut);

        clientSocket.close();
    }

    @Test
    void testServerReceivesAndSendsMessages() throws IOException {
        String message = "Hello, world!";
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println(message);
        assertEquals("Message \"" + message + "\" from " + clientSocket.getInetAddress().getHostName() + "\n" +
                "Thanks for this message: " + message + "\n", outContent.toString());
    }
}
