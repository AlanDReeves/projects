package edu.ics111.h10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel that shows the Player's name and score.
 * 
 * @author Cam Moore
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
  private Player player;
  private JLabel playerName;
  private JLabel playerScore;
  private boolean highlight;

  /**
   * Creates a new PlayerPanel with the given name and score of 0.
   * 
   * @param name The player's name.
   */
  public PlayerPanel(String name) {
    this.player = new Player(name);
    setLayout(new BorderLayout());
    this.playerName = new JLabel(name, JLabel.CENTER);
    playerName.setFont(new Font("SansSerif", Font.BOLD, 16));
    add(playerName, BorderLayout.NORTH);
    this.playerScore = new JLabel("" + player.getScore(), JLabel.CENTER);
    playerScore.setFont(new Font("SansSerif", Font.BOLD, 20));
    add(playerScore, BorderLayout.CENTER);
    this.highlight = false;
    this.setPreferredSize(new Dimension(75, 150));
  }


  /**
   * Returns the player's score.
   * 
   * @return The player's score.
   * @see two.Player#getScore()
   */
  public int getScore() {
    return player.getScore();
  }


  /**
   * Returns the player's name.
   * 
   * @return The player's name.
   * @see two.Player#getName()
   */
  public String getName() {
    return player.getName();
  }


  /**
   * Returns the highlight value.
   * 
   * @return the highlight.
   */
  public boolean isHighlight() {
    return highlight;
  }


  /**
   * Sets the highlight value.
   * 
   * @param highlight the highlight to set.
   */
  public void setHighlight(boolean highlight) {
    this.highlight = highlight;
    if (highlight) {
      this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    } else {
      this.setBorder(BorderFactory.createEmptyBorder());
    }
    repaint();
  }


  /**
   * Sets the player's score.
   * 
   * @param score The player's new score.
   */
  public void setScore(int score) {
    this.player.setScore(score);
    playerScore.setText("" + player.getScore());
    repaint();
  }


  /**
   * Testing.
   * 
   * @param args not used.
   */
  public static void main(String[] args) {
    JFrame window = new JFrame("Player Panel");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    PlayerPanel cam = new PlayerPanel("Cam");
    window.setContentPane(cam);
    window.pack();
    window.setVisible(true);
    cam.setHighlight(true);
    cam.setHighlight(false);

  }
}
