package gameserver.transport;

import java.io.Serializable;
import java.util.List;

public class HangmanData implements Serializable {

    public boolean gameHasEnded;
    public boolean gameWon;
    public boolean isLastLetterCorrect;
    public String word;
    public int numWrongLetters;

    public List<String> usedLetters;

}
