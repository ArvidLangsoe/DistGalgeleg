package securityserver;

import brugerautorisation.transport.rmi.Brugeradmin;
import brugerautorisation.data.Bruger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class SecurityManager extends UnicastRemoteObject implements ISecurityManager{

    HashMap<String,String> testUsersWithPasswords=new HashMap<String, String>();
    HashSet<String> admins = new HashSet<String>();

    HashMap<String, Calendar> loggedInUntill = new HashMap<String, Calendar>();

    Brugeradmin ba;

    SecurityManager() throws RemoteException {
        testUsersWithPasswords.put("admin","qwerty");
        testUsersWithPasswords.put("testuser","12345");

        admins.add("admin");
        admins.add("s144265");
        admins.add("093905");
        try{
        ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (Exception e){
            System.err.println("Could not connect to login server, only test users are allowed");
        }
    }

    public boolean login(String name, String password){
        if(testUsersWithPasswords.containsKey(name)){
            if(testUsersWithPasswords.get(name).equals(password)){
                updateLogin(name);
                return true;
            }
            else{
                return false;
            }
        }

        Bruger loggedInUser=null;
        try {
            loggedInUser =ba.hentBruger(name,password);
            updateLogin(name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void logout(String name) {
        loggedInUntill.remove(name);
    }

    public boolean isLoggedIn(String name) {
        if(loggedInUntill.containsKey(name)){
            if(loggedInUntill.get(name).after(Calendar.getInstance())){
                updateLogin(name);
                return true;
            }
            else{
                loggedInUntill.remove(name);
            }
        }

        return false;
    }

    public boolean isLoggedInAdmin(String adminName) throws RemoteException {
        if(isLoggedIn(adminName)&&admins.contains(adminName)){
            return true;
        }
        return false;
    }

    private void updateLogin(String name){
        Calendar newTime = Calendar.getInstance();
        newTime.add(Calendar.HOUR,2);
        loggedInUntill.put(name,newTime);
    }
}
