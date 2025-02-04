package edu.ics111.h10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * JPanel that shows a pair of dice.
 * 
 * @author Cam Moore
 */
@SuppressWarnings("serial")
public class DicePanel extends JPanel {
  private GraphicalPairOfDice dice;

  /**
   * Creates a new DicePanel.
   */
  public DicePanel() {
    this.dice = new GraphicalPairOfDice();
    setPreferredSize(new Dimension(150, 150));
  }


  /**
   * Rolls the dice and repaints.
   */
  public void roll() {
    dice.roll();
    repaint();
  }


  /**
   * Returns the value of die 1.
   * 
   * @return The value of die 1.
   */
  public int getDie1() {
    return dice.getDie1();
  }


  /**
   * Returns the value of die 2.
   * 
   * @return The value of die 2.
   */
  public int getDie2() {
    return dice.getDie2();
  }


  /**
   * The paintComponent method just draws the two dice and draws a one-pixel 
   * wide blue border around the panel.
   * Antialiasing is turned on to make the ovals look nicer.
   * 
   * @param g The Graphics context.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g); // fill with background color.
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int width = getSize().width;
    int widthPad = (width - 70) / 3;
    int height = getSize().height;
    int heightPad = (height - 70) / 3;
    g.setColor(Color.BLUE);
    g.drawRect(0, 0, width - 1, height - 1);
    // g.drawRect(1,1,width - 3,height - 3);

    dice.drawDie(g, 1, widthPad, heightPad);
    dice.drawDie(g, 2, 2 * widthPad + 35, heightPad * 2 + 35);
  }

}
