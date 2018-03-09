package gameserver.transport;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class GameHistory implements Serializable {
    private HashMap<String,PlayerHistory> playerHistory= new HashMap<String,PlayerHistory>();

    public void addGame(String playerName,boolean gameWon){
        if(!playerHistory.containsKey(playerName)){
            playerHistory.put(playerName,new PlayerHistory());
        }
        PlayerHistory pHis = playerHistory.get(playerName);
        pHis.gamesPlayed++;
        if(gameWon){
            pHis.gamesWon++;
        }
    }
    public PlayerHistory getPlayHistory(String playerName){
        return playerHistory.get(playerName).clone();
    }
    public Set<String> getPlayerNamesInHistory(){
        return playerHistory.keySet();
    }


    public class PlayerHistory implements Cloneable{
        public String playerName;
        public int gamesPlayed;
        public int gamesWon;

        @Override
        public PlayerHistory clone(){
           PlayerHistory pHis= new PlayerHistory();

           pHis.playerName=this.playerName;
           pHis.gamesPlayed=this.gamesPlayed;
           pHis.gamesWon=this.gamesWon;
           return pHis;
        }
    }
}

