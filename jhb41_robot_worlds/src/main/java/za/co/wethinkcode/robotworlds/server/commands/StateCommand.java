package za.co.wethinkcode.robotworlds.server.commands;
import kong.unirest.json.JSONObject;
import za.co.wethinkcode.robotworlds.server.world_bots.Robot;

public class StateCommand extends Command{
    public StateCommand() {
        super("state");
    }

    @Override
    public boolean execute(Robot target) {

        String name = target.getName();
        String status = target.getState();

        JSONObject state = new JSONObject();
        state.put("name",name);
        state.put("state", status);
        target.setStateToBeReturnedToTheUser(status);
        target.getStateToBeReturnedToTheUser();

        target.getState();

        return true;
    }
}
