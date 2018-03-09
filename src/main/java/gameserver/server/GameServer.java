package gameserver.server;

import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GameServer {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.out.println("Starting game server.");
        GalgeController controller = new GalgeController("rmi://localhost:44657/security");
        Endpoint.publish("http://[::]:44656/hangman", controller);

        System.out.println("Game server up.");
    }
}
