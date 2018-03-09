package hangman.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
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
    public HangmanData getData(String playerName);

    @WebMethod
    public GameData getData();

    public getAllGames(String adminName);

    public endGame(String adminName,String playerName);

    public deleteGameData(String adminName, String playerName);



}
