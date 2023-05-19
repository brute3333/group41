package za.co.wethinkcode.robotworlds.server.commands;

import za.co.wethinkcode.robotworlds.server.track_robot_movements.Robot_Direction;
import za.co.wethinkcode.robotworlds.server.world_bots.Robot;
import za.co.wethinkcode.robotworlds.server.track_robot_movements.Robot_Direction;

public class RightCommand extends Command {

    public RightCommand() {
        super("right");
    }

    @Override
    public boolean execute(Robot target) {
        Robot_Direction direction = target.getCurrentDirection();

        switch (direction) {
            case NORTH:
                target.setCurrentDirection(Robot_Direction.EAST);
                target.setStatus("Turned right.");
                break;
            case WEST:
                target.setCurrentDirection(Robot_Direction.NORTH);
                target.setStatus("Turned right.");
                break;
            case SOUTH:
                target.setCurrentDirection(Robot_Direction.WEST);
                target.setStatus("Turned right.");
                break;
            case EAST:
                target.setCurrentDirection(Robot_Direction.SOUTH);
                target.setStatus("Turned right.");
                break;
        }
        return true;
    }
}
