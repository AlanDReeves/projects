package Junk;

import java.util.Scanner;

public class garbage {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt();

        for (int i = 0; i < 5; i++) {
            System.out.printf("i = %d\nindex = %d\n", i, ((input % 11) + (i * (7 - input % 7))) % 11);
        }
    }
}
