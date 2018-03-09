package gameserver.transport;

import java.io.Serializable;

    public class PlayerHistory implements Serializable {
        public String playerName;
        public int gamesPlayed;
        public int gamesWon;


        public PlayerHistory(){

        }

        public PlayerHistory(String name) {
            this.playerName = name;
        }
    }

