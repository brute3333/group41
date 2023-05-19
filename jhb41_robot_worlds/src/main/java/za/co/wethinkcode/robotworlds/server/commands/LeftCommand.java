package za.co.wethinkcode.robotworlds.server.commands;



import za.co.wethinkcode.robotworlds.server.track_robot_movements.Robot_Direction;
import za.co.wethinkcode.robotworlds.server.world_bots.*;

public class LeftCommand extends Command{
    public LeftCommand() {
        super("left");
    }

    @Override
    public boolean execute(Robot target) {
        Robot_Direction direction = target.getCurrentDirection();

        switch (direction) {
            case NORTH:
                target.setCurrentDirection(Robot_Direction.WEST);
                target.setStatus("Turned left.");
                break;
            case WEST:
                target.setCurrentDirection(Robot_Direction.SOUTH);
                target.setStatus("Turned left.");
                break;
            case SOUTH:
                target.setCurrentDirection(Robot_Direction.EAST);
                target.setStatus("Turned left.");
                break;
            case EAST:
                target.setCurrentDirection(Robot_Direction.NORTH);
                target.setStatus("Turned left.");
                break;
        }
        return true;
    }
}

