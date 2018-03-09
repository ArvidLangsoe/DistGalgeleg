package gameserver.server;

import gameserver.transport.HangmanData;
import gameserver.transport.PlayerHistory;
import securityserver.ISecurityManager;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;


@WebService(endpointInterface = "gameserver.server.IGalgeController")
public class GalgeController implements IGalgeController {

    private Hashtable<String, GalgeLogik> gameMap;

    private GameHistory history;
    ISecurityManager security;

    public GalgeController(){
        try {
            security=(ISecurityManager) Naming.lookup("rmi://localhost:44657/security");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public GalgeController(String rmiLookup) throws RemoteException, NotBoundException, MalformedURLException {
        gameMap=new Hashtable<String,GalgeLogik>();
        history=new GameHistory();
        new GalgeLogik();

        security=(ISecurityManager) Naming.lookup(rmiLookup);
    }


    public HangmanData newGame(String playerName) throws Exception{
        if(!security.isLoggedIn(playerName)){
            throw new Exception("You are not logged in.");
        }
        gameMap.put(playerName,new GalgeLogik());
        return getGameData(playerName);
    }


    public boolean isTheGameOver(String playerName){
        GalgeLogik game= gameMap.get(playerName);
        boolean status = game.erSpilletSlut();
        if(status&&!game.isGameAddedToHistory()){
            history.addGame(playerName,game.erSpilletVundet());
            game.setGameAddedToHistory(true);
        }
        return status;
    }


    public boolean isTheGameWon(String playerName){
        return gameMap.get(playerName).erSpilletVundet();
    }


    public boolean isTheGameLost(String playerName) {
        return gameMap.get(playerName).erSpilletTabt();
    }


    public boolean isLastLetterCorrect(String playerName) {
        return gameMap.get(playerName).erSidsteBogstavKorrekt();
    }


    public String getVisibleWord(String playerName){

        if(isTheGameOver(playerName)){
            return gameMap.get(playerName).getOrdet();
        }
        return gameMap.get(playerName).getSynligtOrd();
    }

    public int getNumWrongLetters(String playerName) {
        return gameMap.get(playerName).getAntalForkerteBogstaver();
    }


    public List<String> getUsedLetters(String playerName) {
        return gameMap.get(playerName).getBrugteBogstaver();
    }

    public HangmanData guessLetter(String playerName, String letter) throws Exception{
        if(!security.isLoggedIn(playerName)){
            throw new Exception("You are not logged in.");
        }
        gameMap.get(playerName).gætBogstav(letter);
        return getGameData(playerName);
    }



    public HangmanData getGameData(String playerName) {
        HangmanData data = new HangmanData();

        data.gameHasEnded=isTheGameOver(playerName);
        data.gameWon=isTheGameWon(playerName);
        data.isLastLetterCorrect=isLastLetterCorrect(playerName);
        data.numWrongLetters=getNumWrongLetters(playerName);
        data.word=getVisibleWord(playerName);
        data.usedLetters=getUsedLetters(playerName);
        return data;
    }

    public List<PlayerHistory> getPlayHistoryData() {
        return history.getAllPlayHistory();
    }

    public PlayerHistory getPlayerHistoryData(String playerName) {
        return history.getPlayHistory(playerName);
    }

    public boolean login(String playerName, String password) {
        try {
            return security.login(playerName,password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Set<String> getAllGames(String adminName) throws Exception{
        if(!security.isLoggedInAdmin(adminName)){
            throw new Exception("You dont have permission");
        }
        return gameMap.keySet();
    }

    public void endGame(String adminName, String playerName) throws Exception{
        if(!security.isLoggedInAdmin(adminName)){
            throw new Exception("You dont have permission");
        }
        if(gameMap.containsKey(playerName)){
            gameMap.remove(playerName);
        }
    }

    public void deleteGameData(String adminName, String playerName) throws Exception{
        if(!security.isLoggedInAdmin(adminName)){
            throw new Exception("You dont have permission");
        }

        gameMap.remove(playerName);
    }
}
