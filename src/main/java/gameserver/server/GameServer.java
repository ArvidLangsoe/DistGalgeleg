package gameserver.server;

import javax.xml.ws.Endpoint;

public class GameServer {

    public static void main(String[] args) {
        System.out.println("Starting game server.");
        GalgeController controller = new GalgeController();
        Endpoint.publish("http://[::]:9901/hangman", controller);

        System.out.println("Game server up.");
    }
}
