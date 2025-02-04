package Assignment2;

import java.util.Random;
import java.util.Scanner;

/*
 * This class simulates a rock paper scissors game with the extra options.
 * @author Alan Reeves.
 */
public class RockPaperScissorsGame {
/*
 * This is the main method.
 * @param args not used.
 */
public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      final int ROCK = 0;
      final int PAPER = 1;
      final int SCISSORS = 2;
      final int VULCAN = 3;
      final int LIZARD = 4;
      Random rand = new Random();

      System.out.println("Input integer for seed");

      int seed = scnr.nextInt();
      int p1Wins = 0;
      int p2Wins = 0;

      rand.setSeed(seed);

      System.out.println("Enter player1 name");
      String name1 = scnr.next();
      System.out.println("Enter player2 name"); 
      String name2 = scnr.next();
      
      int roundCount = -1;

      while (roundCount <= 0) {
        System.out.println("Enter integer number of rounds");
        roundCount = scnr.nextInt();
        if (roundCount > 0) {
            break;
        }
        System.out.println("Rounds must be > 0");

        }
        System.out.printf("%s vs %s for %d rounds\n", name1, name2, roundCount);

    //play game
    for (int i = 0; i < roundCount; i++) {
        int p1Move = rand.nextInt(5);
        int p2Move = rand.nextInt(5);
        if (p1Move == p2Move) {
            i--;
            System.out.println("Tie");
        } else { //check who wins
            //in case p1 wins
            int scoreDiff = p1Move - p2Move;
            if (scoreDiff == 1 || scoreDiff == 3 || scoreDiff == -4 || scoreDiff == -2) {
                System.out.printf("%s ", name1);
                System.out.println(determineMove(p1Move, scoreDiff));
                p1Wins++;
            } else { //p2 must have won then
                System.out.printf("%s ", name2);
                System.out.println(determineMove(p2Move, -scoreDiff));
                p2Wins++;
            }

        } 
    }
    System.out.printf("%s wins %d and %s wins %d\n", name1, p1Wins, name2, p2Wins);
    scnr.close();
   }//end of main


   /*
    * This method determines the string to print after a turn, when there is not a tie.
    @param moveInt the integer number of the player's move.
    @param scoreDiff player 1's score minus player 2's score.
    */
   public static String determineMove(int moveInt, int scoreDiff) {
    switch (moveInt) {
        case 0:
            if (scoreDiff == -4) {
                return "crushes lizard with rock";
            } else {
                return "crushes scissors with rock";
            }
        case 1:
            if (scoreDiff == 1) {
                return "covers rock with paper";
            } else {
                return "disproves vulcan with paper";
            }
        case 2:
            if (scoreDiff == 1) {
                return "cuts paper with scissors";
            } else {
                return "decapitates lizard with scissors";
            }
        case 3:
            if (scoreDiff == 1) {
                return "smashes scissors with vulcan";
            } else {
                return "vaporizes rock with vulcan";
            }
        default:
            if (scoreDiff == 1) {
                return "poisons vulcan with lizard";
            } else {
                return "eats paper with lizard";
            }
    }
    } //end of determineMove

}

