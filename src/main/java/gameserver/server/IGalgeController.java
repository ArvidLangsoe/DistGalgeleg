package gameserver.server;

import gameserver.transport.GameHistory;
import gameserver.transport.HangmanData;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;


@WebService
public interface IGalgeController {

    @WebMethod
    public void newGame(String playerName);

    @WebMethod
    public boolean isTheGameOver(String playerName);

    @WebMethod
    public boolean isTheGameWon(String playerName);

    @WebMethod
    public boolean isTheGameLost(String playerName);

    @WebMethod
    public String getVisibleWord(String playerName);

    @WebMethod
    public String getFullWord(String playerName);

    @WebMethod
    public int getNumWrongLetters(String playerName);

    @WebMethod
    public List<String> getUsedLetters(String playerName);

    @WebMethod
    public boolean isLastLetterCorrect(String playerName);

    @WebMethod
    public void guessLetter(String playerName, String letter);

    @WebMethod
    public HangmanData getGameData(String playerName);

    @WebMethod
    public GameHistory getData();

    @WebMethod
    public String getAllGames(String adminName);

    @WebMethod
    public void endGame(String adminName, String playerName);

    @WebMethod
    public void deleteGameData(String adminName, String playerName);



}
