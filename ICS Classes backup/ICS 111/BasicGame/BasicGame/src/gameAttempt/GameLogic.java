package gameAttempt;

import java.util.Random;
import java.util.Scanner;


/**
 * This class is the game logic for Player and Enemy.
 * @author Alan Reeves
 */
public class GameLogic {

  /**
   * This is the main method. It runs the game.
   * @param args not used.
   */
  public static void main(String[] args) {
    
    Random rand = new Random();
    
    //get character's name
    Scanner keyboard = new Scanner(System.in);
    // TODO Auto-generated method stub
    System.out.println("Please input a name for your character");
    String name = keyboard.nextLine();
    
    //make character
    Player player1 = new Player(name, 100, 100);
    
    //make enemy
    Enemy slime = new Enemy(rand.nextInt(99) + 1);
    
    //inform player of enemy and ask for input
    System.out.println("You see an enemy sime. What will you do?");
    
    while (slime.health > 0) { //start of loop
      System.out.println("1 - magic attack");
      System.out.println("2 - quit");
    
      //get input
      int input = keyboard.nextInt();
      while (input < 0 || input > 2) {
        System.out.println("You need to input either 1 or 2");
        System.out.println("1 - magic attack");
        System.out.println("2 - quit");
        input = keyboard.nextInt();
      } //end of while
    
      //act on input
      switch (input) {
        case 1:
          System.out.println(name + " attacked with magic! ");
          slime.takeDamage(player1.magicAttack());
          System.out.println("Enemy health is now " + slime.health);
          break;
        case 2:
          System.out.println("You quit");
          break;
        default:
          System.out.println("Something unexpected happened in the switch block.");
          break;     
      } //end of switch
    } //end of while
    keyboard.close();
    System.out.println(name + " wins! ");
  } //end of main

} //end of class
