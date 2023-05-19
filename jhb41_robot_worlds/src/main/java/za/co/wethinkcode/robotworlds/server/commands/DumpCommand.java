package za.co.wethinkcode.robotworlds.server.commands;
import za.co.wethinkcode.robotworlds.server.servers.SimpleServer;
import za.co.wethinkcode.robotworlds.server.world_bots.Robot;
public class DumpCommand extends  Command{

    public DumpCommand() {
        super("dump");
    }
    @Override
    public  boolean execute(Robot target) {

        target.setStatus("this are  all the bots connected to the world" +SimpleServer.robotsList);
        return  true;

    }
}
