package gameclient;


import gameserver.server.IGalgeController;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class GameClient {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:44656/hangman?wsdl");
        QName qname = new QName("http://server.gameserver/", "GalgeControllerService");
        Service service = Service.create(url, qname);
        IGalgeController gc = service.getPort(IGalgeController.class);

        GameController game = new GameController(gc);

        game.runGame();
    }
}
