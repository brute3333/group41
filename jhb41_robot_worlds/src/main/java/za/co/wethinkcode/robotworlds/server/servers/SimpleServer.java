package za.co.wethinkcode.robotworlds.server.servers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.wethinkcode.robotworlds.client.GameClient;
import za.co.wethinkcode.robotworlds.server.store_server_response.HandlingResponses;
import za.co.wethinkcode.robotworlds.server.world_bots.Play;


import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleServer implements Runnable {

    public static final int PORT = 5000;
    private final BufferedReader inputFromClient;
    private final PrintStream outputToClient;
    private final String clientMachine;
    public static List<String> robotsList = new ArrayList<>();

    public SimpleServer(Socket socket) throws IOException {
        clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);

        outputToClient = new PrintStream(socket.getOutputStream());
        inputFromClient = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        System.out.println("Currently Waiting for client to send requests...");
    }

    public void run() {
        try {
            Play play = null;

            String messageFromClient;
            while((messageFromClient = inputFromClient.readLine()) != null) {

                try {
                    JSONParser parser = new JSONParser();
                    JSONObject fromRequest = (JSONObject) parser.parse(messageFromClient);

                    if (fromRequest.get("command").equals("launch")) {
                        String name = fromRequest.get("robot").toString();
                        robotsList.add(name);
                        List<String> make = (List<String>) fromRequest.get("arguments");
                        play = new Play(make.get(0), name);
                        play.move();
                        outputToClient.println(play.getMyPrompt());
                        fromRequest.clear();
                    }


                    String myCommand = fromRequest.get("command").toString();
                    List<String> userArgs = (List<String>) fromRequest.get("arguments");

                    if (userArgs.isEmpty()) {
                        String jointStringToGoToTheRobot = myCommand;
                        play.setInstruction(jointStringToGoToTheRobot);
                    } else {
                        String jointStringToGoToTheRobot = myCommand + " " + userArgs.get(0);
                        play.setInstruction(jointStringToGoToTheRobot);
                    }

                    play.move();

                    HandlingResponses responding = new HandlingResponses();
                    responding.setValues(play.getRobot(), play);
                    String replyToClient = responding.response();
                    outputToClient.println(replyToClient);
                    outputToClient.flush();


                } catch (Exception e) {

                }

                
            }
            
        }
        catch(IOException ex) {
            System.out.println("Shutting down single client server");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly();
        }
    }

    private void closeQuietly() {
        try { inputFromClient.close(); outputToClient.close();
        } catch(IOException ex) {}
    }
}

