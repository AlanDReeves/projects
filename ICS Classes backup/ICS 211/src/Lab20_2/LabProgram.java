package Lab20_2;

import java.util.Random;
import java.util.Scanner;

public class LabProgram {
    public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      final int ROCK = 0;
      final int PAPER = 1;
      final int SCISSORS = 2;
      Random rand = new Random();
      int seed = scnr.nextInt();

      rand.setSeed(seed);

      String name1 = scnr.next();

      String name2 = scnr.next();
      
      int roundCount = -1;

      while (roundCount <= 0) {
        roundCount = scnr.nextInt();
        if (roundCount > 0) {
            break;
        }
        System.out.println("Rounds must be > 0");

        }
        System.out.printf("%s vs %s for %d rounds\n", name1, name2, roundCount);

        Player player1 = new Player(name1, rand);
        Player player2 = new Player(name2, rand);

        playGame(roundCount, player1, player2);
 
   } //end of main

   public static void playGame(int roundCount, Player player1, Player player2) {
    for (int i = 0; i < roundCount; i ++) {
        if (player1.getMove() == player2.getMove()) {
            i--;
            System.out.println("Tie");
        } else { //check who wins
            int p1Move = player1.getMove();
            int p2Move = player2.getMove();
            //in case p1 wins
            if (p1Move - p2Move == 1 || p1Move - p2Move == -2) {
                System.out.printf("%s wins with ", player1.getName());
                System.out.println(determineMove(p1Move));
            } else {
                System.out.printf("%s wins with ", player2.getName());
                System.out.println(determineMove(p2Move));
            }

        } 

    }
   }//end of playGame

   public static String determineMove(int moveInt) {
        switch (moveInt) {
            case 0:
            return "rock";
            case 1:
            return "scissors";
            default:
            return "paper";
        }
    } //end of determineMove

}
