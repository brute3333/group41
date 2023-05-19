package za.co.wethinkcode.robotworlds.server.commands;

import za.co.wethinkcode.robotworlds.server.world_bots.Robot;

public class ForwardCommand extends Command{

    public ForwardCommand(String argument) {
        super("forward ",argument);

    }

    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(nrSteps)) {
            target.setStatus("Moved forward  by " + nrSteps + " steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }
    
    
}
