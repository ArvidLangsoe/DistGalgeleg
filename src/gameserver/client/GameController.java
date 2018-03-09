package hangman.client;

import hangman.server.IGalgeController;

import java.util.Scanner;

public class GameController {

    IGalgeController con;

    Scanner sc;

    String player = "placeholder";

    public GameController(IGalgeController controller){
        this.con=controller;

        sc= new Scanner(System.in);
    }


    public void runGame(){
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
                System.out.println("The word was: "+ con.getFullWord(player));
                break;
            }


        }
    }


}
