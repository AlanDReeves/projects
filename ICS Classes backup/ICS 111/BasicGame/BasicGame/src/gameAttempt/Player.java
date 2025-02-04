package gameAttempt;

import java.util.Random;

/**
 * This class holds player information for GameLogic.
 * @author Alan Reeves
 */
public class Player {
  private String name;
  private int health;
  private int mana;
  
  Random rand = new Random();
  
  /**
  * This method builds new Player objects.
  */
  public Player(String name, int health, int mana) { //constructor
    this.name = name;
    this.health = health;
    this.mana = mana;
  }
  /**
   * This method calculates and saves damage.
   * @param damage is damage taken.
   * @return health
   */
  
  public int takeDamage(int damage) {
    try {
      this.health -= damage;
    } catch (Exception e) {
      System.out.println("Something went wrong with damage calculation");
    } //end of catch
    return this.health;
  } //end of takeDamage
  
  /**
   * This method takes 5 mana and makes a random number for damage between 1 and 10.
   * Deals no damage if mana <= 0.
   * @return damageDealt
   */
  public int magicAttack() {
    int damageDealt;
    if (this.mana > 0) {
      damageDealt = rand.nextInt(9) + 1;
      this.mana -= 5;
      return damageDealt;
    } else {
      damageDealt = 0;
      System.out.println("No mana left");
      return damageDealt;
    } //end of else
  } //end of magicAttack
} //end of class
