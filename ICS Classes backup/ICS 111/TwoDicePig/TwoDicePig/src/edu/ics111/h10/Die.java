package edu.ics111.h10;

/**
 * Represents a single die.
 * 
 * @author Cam Moore
 */
public class Die {
  private int value;
  private int sides;

  /**
   * Creates a default 6 sided die.
   */
  public Die() {
    this(6);
    roll();
  }


  /**
   * Creates a die with the given number of sides.
   * 
   * @param sides The number of sides for the die.
   */
  public Die(int sides) {
    this.sides = sides;
    roll();
  }


  /**
   * Rolls the die.
   * 
   * @return the new value of the die.
   */
  public int roll() {
    value = (int) (Math.random() * sides + 1);
    return value;
  }


  /**
   * Returns the value.
   * 
   * @return the value.
   */
  public int getValue() {
    return value;
  }


  /**
   * Returns the sides.
   * 
   * @return the sides.
   */
  public int getSides() {
    return sides;
  }
}
