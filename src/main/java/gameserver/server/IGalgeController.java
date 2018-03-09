package gameserver.server;

import gameserver.transport.HangmanData;
import gameserver.transport.PlayerHistory;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.Set;


@WebService
public interface IGalgeController {

    @WebMethod
    public HangmanData newGame(String playerName) throws Exception;

    @WebMethod
    public boolean isTheGameOver(String playerName);

    @WebMethod
    public boolean isTheGameWon(String playerName);

    @WebMethod
    public boolean isTheGameLost(String playerName);

    @WebMethod
    public String getVisibleWord(String playerName);

    @WebMethod
    public int getNumWrongLetters(String playerName);

    @WebMethod
    public List<String> getUsedLetters(String playerName);

    @WebMethod
    public boolean isLastLetterCorrect(String playerName);

    @WebMethod
    public HangmanData guessLetter(String playerName, String letter) throws Exception;

    @WebMethod
    public HangmanData getGameData(String playerName);

    @WebMethod
    public List<PlayerHistory> getPlayHistoryData();
    @WebMethod
    public PlayerHistory getPlayerHistoryData(String playerName);

    @WebMethod
    public boolean login(String playerName,String password);

    @WebMethod
    public Set<String> getAllGames(String adminName)throws Exception;

    @WebMethod
    public void endGame(String adminName, String playerName)throws Exception;

    @WebMethod
    public void deleteGameData(String adminName, String playerName)throws Exception;



}
