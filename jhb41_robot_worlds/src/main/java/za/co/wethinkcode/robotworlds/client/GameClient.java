package za.co.wethinkcode.robotworlds.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Scanner;

public class GameClient {

    public String getInputMessage() {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        return message;
    }

    public void printMessage(String message) {

        if (message.contains(",")) {
            try {
                JSONParser parser = new JSONParser();
                JSONObject fromResponse = (JSONObject) parser.parse(message);
                System.out.println("State           : " + fromResponse.get("state"));
                System.out.println("Status          : " + fromResponse.get("status"));
                System.out.println("Robot_Position  : " + fromResponse.get("position"));
                System.out.println(fromResponse.get("prompt"));
            } catch (Exception e) {

            }
        } else {
            System.out.println(message);
        }

    }
}
