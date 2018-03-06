package gameserver.client;

import hangman.server.GalgeController;

public class GameTest {

    public static void main(String[] args){

        GameController game = new GameController(new GalgeController());

        game.runGame();
    }
}
