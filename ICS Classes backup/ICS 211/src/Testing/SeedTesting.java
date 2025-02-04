package Testing;

import java.util.Random;
public class SeedTesting {
    public static void main(String[] args) {
        Random rand = new Random();

        rand.setSeed(5);

        for (int i = 0; i < 6; i++) {
            System.out.println(rand.nextInt(3));
        }

         //play game

    int roundCount = 5;
    for (int i = 0; i < roundCount; i++) {
        int p1Move = rand.nextInt();
        int p2Move = rand.nextInt();
        System.out.printf("p1Move = %d")
            }

    }

}
