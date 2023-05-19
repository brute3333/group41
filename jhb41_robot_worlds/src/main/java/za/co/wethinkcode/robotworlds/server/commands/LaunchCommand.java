package za.co.wethinkcode.robotworlds.server.commands;

import za.co.wethinkcode.robotworlds.server.world_bots.Robot;

public class LaunchCommand extends Command{
    public LaunchCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(Robot target) {
        return false;
    }

}
