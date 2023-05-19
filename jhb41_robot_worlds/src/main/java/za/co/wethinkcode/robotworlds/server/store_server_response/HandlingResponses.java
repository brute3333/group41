package za.co.wethinkcode.robotworlds.server.store_server_response;


import kong.unirest.json.JSONObject;
import za.co.wethinkcode.robotworlds.server.track_robot_movements.*;
import za.co.wethinkcode.robotworlds.server.world_bots.*;


public class HandlingResponses {

    private String state;

    private String name;
    private String prompt;
    private String position;
    private Robot_Direction direction;
    private String status;

    private String shields = "5";
    private String shots = "4";




    public void setValues(Robot robot, Play play) {
        this.state = robot.getState();
        this.name = robot.getName();
        this.position = robot.toString();
        this.direction = robot.getCurrentDirection();
        this.status = robot.getStatus();
        this.prompt = play.getMyPrompt();
    }

    public String response () {

        JSONObject requesting = new JSONObject();

        requesting.put("state", state);
        requesting.put("name", name);
        requesting.put("position", position);
        requesting.put("direction", direction);
        requesting.put("status", status);
        requesting.put("shields", shields);
        requesting.put("shots", shots);
        requesting.put("prompt", prompt);


        return requesting.toString();
    }
}
