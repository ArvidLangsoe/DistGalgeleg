package gameserver.server;

import gameserver.transport.PlayerHistory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameHistory implements Serializable {

    private List<PlayerHistory> gameHistory = new CopyOnWriteArrayList<PlayerHistory>();

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

        for(PlayerHistory pH: gameHistory){
            if(playerName.equals(pH.playerName))
                System.out.println(pH.playerName+" was not deleted.");
        }

    }

    public List<PlayerHistory> getAllPlayHistory(){
        return gameHistory;
    }
}

