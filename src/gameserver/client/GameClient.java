package hangman.client;

import hangman.server.IGalgeController;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class GameClient {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:9901/hangman?wsdl");
        QName qname = new QName("http://server.hangman/", "GalgeControllerService");
        Service service = Service.create(url, qname);
        IGalgeController gc = service.getPort(IGalgeController.class);

        GameController game = new GameController(gc);

        game.runGame();
    }
}
