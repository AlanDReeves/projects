package Lab20_2;

import java.util.Random;
import java.util.Scanner;

public class LabProgram2 {
public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      final int ROCK = 0;
      final int PAPER = 1;
      final int SCISSORS = 2;
      Random rand = new Random();
      int seed = scnr.nextInt();
      int p1Wins = 0;
      int p2Wins = 0;

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

    //play game
    for (int i = 0; i < roundCount; i++) {
        int p1Move = rand.nextInt(3);
        int p2Move = rand.nextInt(3);
        if (p1Move == p2Move) {
            i--;
            System.out.println("Tie");
        } else { //check who wins
            //in case p1 wins
            if (p1Move - p2Move == 1 || p1Move - p2Move == -2) {
                System.out.printf("%s wins with ", name1);
                System.out.println(determineMove(p1Move));
                p1Wins++;
            } else { //p2 must have won then
                System.out.printf("%s wins with ", name2);
                System.out.println(determineMove(p2Move));
                p2Wins++;
            }

        } 
    }
    System.out.printf("%s wins %d and %s wins %d\n", name1, p1Wins, name2, p2Wins);
    scnr.close();
   }//end of main

   public static String determineMove(int moveInt) {
        switch (moveInt) {
            case 0:
            return "rock";
            case 1:
            return "paper";
            default:
            return "scissors";
        }
    } //end of determineMove

}
