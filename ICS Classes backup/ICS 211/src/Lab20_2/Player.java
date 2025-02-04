package Lab20_2;
import java.util.Random;

public class Player {
    private int winCount;
    private int moveChoice;
    private String name;
    private Random rand;

    /*Constructor.player
     * @param newName is the name of the player.
     * @param rand is a random object.
     */
    public Player (String newName, Random rand) {
        winCount = 0;
        moveChoice = -1;
        name = newName;
        this.rand = rand;
    }

    public void increaseWinCount() {
        winCount++;
    }

    public int getMove() {
        moveChoice = rand.nextInt(3);
        return moveChoice;
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

}
