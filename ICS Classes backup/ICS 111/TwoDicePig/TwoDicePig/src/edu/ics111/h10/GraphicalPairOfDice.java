package edu.ics111.h10;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A subclass of PairOfDice that can draw the die.
 * 
 * @author Cam Moore
 */
public class GraphicalPairOfDice extends PairOfDice {

  /**
   * Creates a new GraphicalPairOfDice.
   *
   */
  public GraphicalPairOfDice() {
    super();
  }


  /**
   * Draw a die with upper left corner at (x,y). The die is 35 by 35 pixels 
   * in size. The val parameter gives the value.
   * showing on the die (that is, the number of dots).
   * 
   * @param g The Graphics Context.
   * @param dieNum which die to draw.
   * @param x the x value.
   * @param y the y value.
   */
  public void drawDie(Graphics g, int dieNum, int x, int y) {
    int val = 1;
    if (dieNum == 1) {
      val = getDie1();
    } else {
      val = getDie2();
    }
    g.setColor(Color.white);
    g.fillRect(x, y, 35, 35);
    g.setColor(Color.black);
    g.drawRect(x, y, 34, 34);
    if (val > 1) { // upper left dot
      g.fillOval(x + 3, y + 3, 9, 9);
    }
    if (val > 3) { // upper right dot
      g.fillOval(x + 23, y + 3, 9, 9);
    }
    if (val == 6) { // middle left dot
      g.fillOval(x + 3, y + 13, 9, 9);
    }
    if (val % 2 == 1) { // middle dot (for odd-numbered val's)
      g.fillOval(x + 13, y + 13, 9, 9);
    }
    if (val == 6) { // middle right dot
      g.fillOval(x + 23, y + 13, 9, 9);
    }
    if (val > 3) { // bottom left dot
      g.fillOval(x + 3, y + 23, 9, 9);
    }
    if (val > 1) { // bottom right dot
      g.fillOval(x + 23, y + 23, 9, 9);
    }
  }

}
