package securityserver;

public interface ISecurityManager extends java.rmi.Remote{

    public boolean login(String name, String password) throws java.rmi.RemoteException;

    public boolean isLoggedIn(String name)throws java.rmi.RemoteException;

    public boolean isLoggedInAdmin(String adminName) throws  java.rmi.RemoteException;

}
