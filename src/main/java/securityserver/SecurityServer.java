package securityserver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SecurityServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {

        System.out.println("Starting securityServer");
        ISecurityManager sM = new SecurityManager();
        java.rmi.registry.LocateRegistry.createRegistry(44657);

        Naming.rebind("rmi://localhost:44657/security",sM);
        System.out.println("Server published");
    }
}
