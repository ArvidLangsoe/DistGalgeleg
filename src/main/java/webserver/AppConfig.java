package webserver;

import gameserver.server.IGalgeController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationPath("/rest")
public class AppConfig extends Application {

    public static IGalgeController controller;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9901/hangman?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://server.gameserver/", "GalgeControllerService");
        Service service = Service.create(url, qname);
        controller = service.getPort(IGalgeController.class);
        controller.newGame("x");
    }

}