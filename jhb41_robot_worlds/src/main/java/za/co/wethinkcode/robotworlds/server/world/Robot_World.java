package za.co.wethinkcode.robotworlds.server.world;

import za.co.wethinkcode.robotworlds.server.world_maze.Maze;
import za.co.wethinkcode.robotworlds.server.track_robot_movements.*;
import za.co.wethinkcode.robotworlds.server.world_bots.*;
import za.co.wethinkcode.robotworlds.server.world.Config_World.*;

import java.util.Arrays;
import java.util.List;

public class Robot_World implements IWorld{
    private final Position TOP_LEFT = new Position(-100,200);
    private final Position BOTTOM_RIGHT = new Position(100,-200);

    public static final Position CENTRE = new Position(0,0);

    private Position position;
    private Direction currentDirection;
    private Maze maze;
    private Robot robot;

    public Robot_World(Maze maze){
        this.maze = maze;
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
    }
    @Override
    public UpdateResponse updatePosition(int nrSteps) {

        int newX = this.position.getX();
        int newY = this.position.getY();
        if (Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }else if(Direction.LEFT.equals(this.currentDirection)){
            newX = newX - nrSteps;
        }else if(Direction.RIGHT.equals(this.currentDirection)){
            newX = newX + nrSteps;
        }else if(Direction.DOWN.equals(this.currentDirection)){
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (maze.blocksPath(this.position,newPosition)){
            return UpdateResponse.FAILED_OBSTRUCTED;
        }
        else if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            this.position = newPosition;
            return UpdateResponse.SUCCESS;
        }
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        int direction = Arrays.asList(Direction.values()).indexOf(this.currentDirection);
        if (turnRight) {
            direction ++;
            if(direction == 4){
                direction = 0;
            }
        } else {
            direction--;
            if(direction == -1){
                direction = 3;
            }
        }
        this.currentDirection = Direction.values()[direction];

    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    @Override
    public boolean isAtEdge() {
        return false;
    }

    @Override
    public void reset() {
        this.currentDirection= Direction.UP;
        this.position = CENTRE;

    }

    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }

    public Maze getMaze() {
        return this.maze;
    }
    public void getRobot(){
        Play play= new Play("iRobot", "Killer Kau");
        Robot robot = play.getRobot();
        play.setMessageToTheServer(robot.getObstacle()+ " from the world.");
    }

    @Override
    public void showObstacles() {
        System.out.println("Here are some  Robot Worlds obstacles to be aware of after launching your Robots:");
        for (Obstacle obstacle:getObstacles()){
            int x= obstacle.getBottomLeftX();
            int y=obstacle.getBottomLeftY();
            System.out.println("At position "+x+","+y+" (to "+(x+4)+","+(y+4)+")");
        }

    }
}

