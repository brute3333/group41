package za.co.wethinkcode.robotworlds.client;

import java.net.*;
import java.io.*;



public class SimpleClient {
    public static void main(String args[]) {
        try (
                Socket socket = new Socket("localhost", 5000);
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
        )
        {
            String clientMessageToServer= "";
            String serverMessageFromClient = "";
            System.out.println("Hey Champ! to play the game enter the following command:\nlaunch <robotMake> <robotName>");
            System.out.println("Tip:After launching enter the command help to get a list of all  valid Commands of the game.");
            GameClient  gameclient = new GameClient();

            while (true) {
                clientMessageToServer =gameclient.getInputMessage();
                HandlingRequests incoming_requests = new HandlingRequests();
                incoming_requests.setValues(clientMessageToServer);
                out.println(incoming_requests.request(incoming_requests.request(serverMessageFromClient)));
                out.println(clientMessageToServer);
                out.flush();

                serverMessageFromClient = in.readLine();
                gameclient.printMessage(serverMessageFromClient);

                if (clientMessageToServer.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
