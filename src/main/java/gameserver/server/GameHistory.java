package gameserver.server;

import gameserver.transport.PlayerHistory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GameHistory implements Serializable {

    private List<PlayerHistory> gameHistory = new ArrayList<PlayerHistory>();

    public void addGame(String playerName,boolean gameWon){
        PlayerHistory pHis=null;
        for(PlayerHistory pH: gameHistory){
            if(playerName.equals(pH.playerName))
                pHis=pH;
        }
        if(pHis==null){
            pHis=new PlayerHistory(playerName);
            gameHistory.add(pHis);
        }

        pHis.gamesPlayed++;
        if(gameWon){
            pHis.gamesWon++;
        }
    }
    public PlayerHistory getPlayHistory(String playerName){
        for(PlayerHistory pH: gameHistory){
            if(playerName.equals(pH.playerName))
                return pH;
        }
        return null;
    }

    public void deleteHistory(String playerName){
        for(PlayerHistory pH: gameHistory){
            if(playerName.equals(pH.playerName))
                gameHistory.remove(pH);
        }
    }

    public List<PlayerHistory> getAllPlayHistory(){
        return gameHistory;
    }
}

