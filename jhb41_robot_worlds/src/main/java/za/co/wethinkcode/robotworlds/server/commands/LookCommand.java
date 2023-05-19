package za.co.wethinkcode.robotworlds.server.commands;
import za.co.wethinkcode.robotworlds.server.world_bots.Robot;

public class LookCommand extends Command{
    public LookCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(Robot target) {
        return false;
    }
}
