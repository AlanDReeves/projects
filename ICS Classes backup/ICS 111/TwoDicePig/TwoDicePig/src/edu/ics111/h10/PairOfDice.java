package edu.ics111.h10;

/**
 * A pair of dice.
 * 
 * @author Cam Moore
 */
public class PairOfDice {
  private Die die1;
  private Die die2;

  /**
   * Creates a pair of 6 sided dice.
   */
  public PairOfDice() {
    this.die1 = new Die();
    this.die2 = new Die();
    this.roll();
  }


  /**
   * Rolls the dice.
   */
  public void roll() {
    die1.roll();
    die2.roll();
  }


  /**
   * Returns the value of die 1.
   * 
   * @return the value of die 1.
   */
  public int getDie1() {
    return die1.getValue();
  }


  /**
   * Returns the value of die 2.
   * 
   * @return the value of die 2.
   */
  public int getDie2() {
    return die2.getValue();
  }
}
