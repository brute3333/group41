package za.co.wethinkcode.robotworlds.server.world;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ConnectedClients {
    private List<Socket> connectedClientList = new LinkedList<>();

    public List<Socket> getConnectedClientList() {
        return connectedClientList;
    }

    public void setConnectedClientList(Socket connectedClient) {
        this.connectedClientList.add(connectedClient);
    }
}

