package gameAttempt;

/**This class holds enemy data for GameLogic.
 * @author Alan Reeves
 */
public class Enemy {
  int health;
  
  /**
   * This is the constructor for the class.
   * @param health is health.
   */
  public Enemy(int health) {
    this.health = health;
  } //end of constructor
  
  /**
   * This method allows enemies to be damaged.
   * @param damage is damage taken.
   * @return health.
   */
  public int takeDamage(int damage) {
    this.health -= damage;
    if (this.health <= 0) {
      System.out.println("Enemy is dead!");
    } //end of if
    return this.health;
  } //end of takeDamage

} //end of class
