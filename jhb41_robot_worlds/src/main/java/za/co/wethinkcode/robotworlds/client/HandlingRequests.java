package za.co.wethinkcode.robotworlds.client;

import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HandlingRequests {
    private String robot;
    private String command;
    private List<String> arguments = new ArrayList<>();

    public void setValues(String message) {
        String[] args = message.split(" ");

        if (args.length == 1) {
            setCommand(args[0]);
        }
        else if (args.length == 2) {
            setCommand(args[0]);
            setArguments(args[1]);
        }
        else if (args.length == 3) {
            setRobot(args[2]);
            setCommand(args[0]);
            setArguments(args[1]);
            setArguments(args[2]);
        }
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }


    public void setCommand(String command) {
        this.command = command;
    }


    public void setArguments(String arguments) {
        this.arguments.add(arguments);
    }

    public String request(String clientRequest) {
        JSONObject requesting = new JSONObject();

        requesting.put("robot", robot);
        requesting.put("command", command);
        requesting.put("arguments", arguments);

        return requesting.toString();
    }
}
