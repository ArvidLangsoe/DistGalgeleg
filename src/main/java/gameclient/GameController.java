package gameclient;

import gameserver.server.IGalgeController;
import gameserver.transport.PlayerHistory;

import java.util.Scanner;

public class GameController {

    IGalgeController con;

    Scanner sc;

    String player = null;

    public GameController(IGalgeController controller){
        this.con=controller;

        sc= new Scanner(System.in);
    }


    public void runGame() throws Exception {
        String name=null;
        String password=null;

        do {
            if(name!=null){
                System.out.println("Wrong login information please try again!");
            }
            System.out.println("Please enter username: ");
            name = sc.nextLine();
            System.out.println("Please enter password: ");
            password = sc.nextLine();
        }while(!con.login(name,password));

        player=name;
        password=null;

        System.out.println("You are now loggedIn!");


        System.out.println("Welcome to hangman! Guess the word, by typing a letter and pressing enter.");
        con.newGame(player);

        while(true){
            System.out.println("Here is your word:");
            System.out.println(con.getVisibleWord(player));


            System.out.println("Whats your guess?");

            String guess= sc.nextLine();
            if(guess.length()>1||guess.length()==0){
                System.out.println("Your guess had no letters or more than one letter.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if(con.getUsedLetters(player).contains(guess)){
                System.out.println("You already guessed that letter!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            con.guessLetter(player,guess);


            if(con.isLastLetterCorrect(player)){
                System.out.println("You guessed correctly");
            }
            else{
                System.out.println("You made the wrong guess");
                System.out.println("You now have " + con.getNumWrongLetters(player)+" wrong guesses");


            }

            if(con.isTheGameOver(player)){
                System.out.println("\n");

                if(con.isTheGameWon(player)){
                    System.out.println("You won the game!! :D ");
                }
                else if(con.isTheGameLost(player)){
                    System.out.println("You lost the game :(");
                }
                System.out.println("The word was: "+ con.getVisibleWord(player));

                PlayerHistory pH =con.getPlayerHistoryData(player);

                System.out.println("You have played: "+pH.gamesPlayed + " game and won: "+ pH.gamesWon);
                System.out.println("Here are all the scores: \n"+con.getPlayHistoryData());
                break;
            }
        }
    }


}
