package za.co.wethinkcode.robotworlds.server.world;

import za.co.wethinkcode.robotworlds.server.track_robot_movements.*;

public class SquareObstacle implements Obstacle{
    private int x;
    private int y;
    private int size;
    public SquareObstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 4;
    }
    @Override
    public int getBottomLeftX() {
        return this.x;
    }
    @Override
    public int getBottomLeftY() {
        return this.y;
    }
    @Override
    public int getSize() {
        return this.size+1;
    }

    @Override
    public boolean blocksPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        Position topLeft = new Position(this.x, this.y+this.size);
        Position bottomLeft = new Position(this.x, this.y);
        Position bottomRight = new Position(this.x+this.size, this.y);
        boolean withinXAxis = bottomLeft.getX() <= x && bottomRight.getX() >= x;
        boolean withinYAxis = bottomLeft.getY() <= y && topLeft.getY() >= y;
        return withinXAxis && withinYAxis;
    }



    @Override
    public boolean blocksPath(Position a, Position b) {
        boolean blocked = false;
        boolean yAxis = false;
        boolean xAxis = false;
        int xDiff = Math.abs(a.getX() - b.getX());
        int yDiff = Math.abs(a.getY() - b.getY());
        if (xDiff == 0) {
            yAxis = movingOnYAxis(blocked, a, b);
        } else if (yDiff == 0) {
            xAxis = movingOnXAxis(blocked, a, b);
        }
        return yAxis || xAxis;
    }
    private boolean movingOnYAxis(boolean blocked, Position a, Position b) {
        if (a.getY() < b.getY()) {
            for (int i = a.getY(); i <= b.getY(); i++) {
                if (blocksPosition(new Position(a.getX(), i))) {
                    blocked = true;
                }
                if (blocked) {
                    return blocked;
                }
            }
        } else {
            for (int i = b.getY(); i <= a.getY(); i++) {
                if (blocksPosition(new Position(a.getX(), i))) {
                    blocked = true;
                }
                if (blocked) {
                    return blocked;
                }
            }
        }
        return blocked;
    }
    private boolean movingOnXAxis(boolean blocked, Position a, Position b) {
        if (a.getX() < b.getX()) {
            for (int i = a.getX(); i <= b.getX(); i++) {
                if (blocksPosition(new Position(i, a.getY()))) {
                    blocked = true;
                }
                if (blocked) {
                    return blocked;
                }
            }
        } else {
            for (int i = a.getX(); i <= b.getX(); i++) {
                if (blocksPosition(new Position(i, a.getY()))) {
                    blocked = true;
                }
                if (blocked) {
                    return blocked;
                }
            }
        }
        return blocked;
    }

}
