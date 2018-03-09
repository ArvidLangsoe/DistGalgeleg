package securityserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SecurityManager extends UnicastRemoteObject implements ISecurityManager{


    protected SecurityManager() throws RemoteException {
    }

    public boolean login(String name, String password){
        return false;
    }

    public boolean isLoggedIn(String name) {
        return false;
    }
}
