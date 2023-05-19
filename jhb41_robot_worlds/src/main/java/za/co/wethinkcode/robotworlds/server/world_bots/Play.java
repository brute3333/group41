package za.co.wethinkcode.robotworlds.server.world_bots;
import za.co.wethinkcode.robotworlds.server.commands.Command;



public class Play {
    private String messageToTheServer;
    private String myPrompt;
    private String instruction = "";
    private Robot robot;

    public Play(String make, String name) {
        this.robot = new Robot(make, name);
    }

    public Robot getRobot() {
        return robot;
    }

    public void setMessageToTheServer(String messageToTheServer) {
        this.messageToTheServer = messageToTheServer;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getMyPrompt() {
        return myPrompt;
    }

    public void move() {

        Command command;
        boolean shouldContinue = true;
        myPrompt = robot.getName() + "> What must I do next?".strip().toLowerCase();
        try {
            command = Command.create(instruction);
            shouldContinue = robot.handleCommand(command);
        } catch (IllegalArgumentException e) {
            robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
        }
        messageToTheServer = robot.toString();
    }

    public String getMessageToTheServer() {
        return messageToTheServer;
    }
}
