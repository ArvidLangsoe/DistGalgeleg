package gameserver.transport;

import java.io.Serializable;

    public class PlayerHistory implements Serializable {
        public String playerName;

        @Override
        public String toString() {
            return "PlayerHistory{" +
                    "playerName='" + playerName + '\'' +
                    ", gamesPlayed=" + gamesPlayed +
                    ", gamesWon=" + gamesWon +
                    '}'+"\n";
        }

        public int gamesPlayed;
        public int gamesWon;


        public PlayerHistory(){

        }

        public PlayerHistory(String name) {
            this.playerName = name;
        }
    }

