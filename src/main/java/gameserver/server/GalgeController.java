package gameserver.server;

import gameserver.transport.HangmanData;
import gameserver.transport.PlayerHistory;

import javax.jws.WebService;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;


@WebService(endpointInterface = "gameserver.server.IGalgeController")
public class GalgeController implements IGalgeController {

    private Hashtable<String, GalgeLogik> gameMap;

    private GameHistory history;

    public GalgeController(){
        gameMap=new Hashtable<String,GalgeLogik>();
        history=new GameHistory();
        new GalgeLogik();
    }


    public void newGame(String playerName){
        //TODO Check playerName is logged in.
        gameMap.put(playerName,new GalgeLogik());
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

    public HangmanData guessLetter(String playerName, String letter) {
        //TODO Check playerName is logged in.
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

    public Set<String> getAllGames(String adminName) {
        //TODO Check adminName is logged in.
        return gameMap.keySet();
    }

    public void endGame(String adminName, String playerName) {
//TODO Check adminName is logged in.
        if(gameMap.containsKey(playerName)){
            gameMap.remove(playerName);
        }
    }

    public void deleteGameData(String adminName, String playerName) {
        //TODO Check adminName is logged in.
    }
}
