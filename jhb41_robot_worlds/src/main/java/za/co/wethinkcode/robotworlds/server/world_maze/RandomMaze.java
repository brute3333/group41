package za.co.wethinkcode.robotworlds.server.world_maze;

import za.co.wethinkcode.robotworlds.server.track_robot_movements.*;
import za.co.wethinkcode.robotworlds.server.world.Obstacle;
import za.co.wethinkcode.robotworlds.server.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze implements Maze{
    ArrayList<Obstacle> Obstaclelist ;
    Random rand= new Random();

    public List<Obstacle> getObstacles() {
        return this.Obstaclelist;
    }

    public RandomMaze(){
        this.Obstaclelist=new ArrayList<>();
        RandomObstacle();
    }

    private void RandomObstacle() {
        int min =0;
        int max =10;
        int random_int =rand.nextInt(max)+1;
        while(min<=random_int){
            min++;
            int x=rand.nextInt(100);
            int y=rand.nextInt(200);
            Obstaclelist.add(new SquareObstacle(x,y));
        }
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        for(Obstacle obs: getObstacles()){
            if (obs.blocksPath(a,b)){
                return true;
            }
        }
        return false;
    }
}
