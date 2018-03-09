package gameserver.server;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminTest {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:44656/hangman?wsdl");
        QName qname = new QName("http://server.gameserver/", "GalgeControllerService");
        Service service = Service.create(url, qname);
        IGalgeController gc = service.getPort(IGalgeController.class);

        gc.login("admin","qwerty");

        gc.newGame("admin");
        System.out.println(gc.getAllGames("admin"));
        gc.endGame("admin","admin");
        System.out.println(gc.getAllGames("admin"));


    }
}
