package za.co.wethinkcode.robotworlds.server.world_bots;
import za.co.wethinkcode.robotworlds.server.commands.Command;



import za.co.wethinkcode.robotworlds.server.commands.*;
import za.co.wethinkcode.robotworlds.server.track_robot_movements.*;
import za.co.wethinkcode.robotworlds.server.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private final Position TOP_LEFT = new Position(-200,100);
    private final Position BOTTOM_RIGHT = new Position(100,-200);
    public static final Position CENTRE = new Position(0,0);
    private Position position;
    private Robot_Direction currentDirection;
    private String status;
    private String name;
    private List<Command> commandHistory = new ArrayList<>();
    private String state;
    private String make;
    private Obstacle obstacle;

    public Obstacle getObstacle() {
        return obstacle;
    }

    public String getMake() {
        return make;
    }

    public String getStateToBeReturnedToTheUser() {
        return stateToBeReturnedToTheUser;
    }

    public void setStateToBeReturnedToTheUser(String stateToBeReturnedToTheUser) {
        this.stateToBeReturnedToTheUser = stateToBeReturnedToTheUser;
    }

    private String stateToBeReturnedToTheUser;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public Robot(String make, String name) {
        this.make = make;
        this.name = name;
        this.state="normal";
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Robot_Direction.NORTH;
        this.obstacle = obstacle;
    }


    public boolean handleCommand(Command command) {
        return command.execute(this);
    }


    public boolean updatePosition(int nrSteps){
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Robot_Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Robot_Direction.EAST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        } else if (Robot_Direction.WEST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        } else if (Robot_Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            this.position = newPosition;
            return true;
        }
        return false;
    }


    public List<Command> getCommandHistory() {
        return commandHistory;
    }


    public void setCommandHistory(Command command) {
        this.commandHistory.add(command);
    }


    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Robot_Direction getCurrentDirection() {
        return this.currentDirection;
    }


    public void setCurrentDirection(Robot_Direction direction) {
        this.currentDirection = direction;
    }


    public Position getPosition() {
        return this.position;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "]";
    }




}

