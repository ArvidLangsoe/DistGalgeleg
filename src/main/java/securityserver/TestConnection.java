package securityserver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestConnection {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        ISecurityManager sM = (ISecurityManager) Naming.lookup("rmi://localhost:44657/security");
        System.out.println(sM.isLoggedIn("haha"));
    }
}
