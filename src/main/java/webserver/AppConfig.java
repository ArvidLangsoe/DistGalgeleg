package webserver;

import gameserver.server.IGalgeController;
import securityserver.ISecurityManager;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@ApplicationPath("/rest")
public class AppConfig extends Application {

    public static IGalgeController controller;
    public static ISecurityManager security;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:44656/hangman?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://server.gameserver/", "GalgeControllerService");
        Service service = Service.create(url, qname);
        controller = service.getPort(IGalgeController.class);

        try {
            security = (ISecurityManager) Naming.lookup("rmi://localhost:44657/security");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}