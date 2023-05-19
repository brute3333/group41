package za.co.wethinkcode.robotworlds.client;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleClientTest {

    @Test
    void testSimpleClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();

        Thread serverThread = new Thread(() -> {
            try {
                Socket socket = serverSocket.accept();
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Waiting for client...");

                String messageFromClient = in.readLine();
                System.out.println("Message \"" + messageFromClient + "\" from client");
                out.println("Thanks for this message: " + messageFromClient);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        Socket clientSocket = new Socket("localhost", port);
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message = "Hello from client";
        out.println(message);
        out.flush();

        String response = in.readLine();
        assertEquals("Thanks for this message: " + message, response);

        serverThread.interrupt();
        serverSocket.close();
    }
}
