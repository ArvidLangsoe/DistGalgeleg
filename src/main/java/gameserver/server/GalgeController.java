package gameserver.server;

import gameserver.transport.GameHistory;
import gameserver.transport.HangmanData;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;


@WebService(endpointInterface = "gameserver.server.IGalgeController")
public class GalgeController implements IGalgeController {

    private Hashtable<String, GalgeLogik> gameMap;

    public GalgeController(){
        gameMap=new Hashtable<String,GalgeLogik>();
    }


    public void newGame(String playerName){
        gameMap.put(playerName,new GalgeLogik());
    }


    public boolean isTheGameOver(String playerName){

        boolean status = gameMap.get(playerName).erSpilletSlut();
        if(status){
            System.out.println(playerName + " has just finished a game.");
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
        return gameMap.get(playerName).getSynligtOrd();
    }


    public String getFullWord(String playerName){
        return gameMap.get(playerName).getOrdet();
    }


    public int getNumWrongLetters(String playerName) {
        return gameMap.get(playerName).getAntalForkerteBogstaver();
    }


    public List<String> getUsedLetters(String playerName) {
        return gameMap.get(playerName).getBrugteBogstaver();
    }

    public void guessLetter(String playerName, String letter) {
        gameMap.get(playerName).gætBogstav(letter);
    }



    public HangmanData getGameData(String playerName) {
        return null;
    }

    public GameHistory getData() {
        return null;
    }

    public String getAllGames(String adminName) {
        return null;
    }

    public void endGame(String adminName, String playerName) {

    }

    public void deleteGameData(String adminName, String playerName) {

    }
}
