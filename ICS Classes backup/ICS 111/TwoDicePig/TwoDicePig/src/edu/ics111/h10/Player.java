package edu.ics111.h10;

/**
 * Represents a Player with a name and score.
 * 
 * @author Cam Moore
 */
public class Player {
  private String name;
  private int score;

  /**
   * Creates a new Player with the given name and a score of 0.
   * 
   * @param name The player's name.
   */
  public Player(String name) {
    this(name, 0);
  }


  /**
   * Creates a new Player with the given name and score.
   * 
   * @param name The player's name.
   * @param score The player's score.
   */
  public Player(String name, int score) {
    this.name = name;
    this.score = score;
  }


  /**
   * Returns the score.
   * 
   * @return the score.
   */
  public int getScore() {
    return score;
  }


  /**
   * Sets the score to the new score.
   * 
   * @param score the score to set.
   */
  public void setScore(int score) {
    this.score = score;
  }


  /**
   * Return the name.
   * 
   * @return the name.
   */
  public String getName() {
    return name;
  }

}
