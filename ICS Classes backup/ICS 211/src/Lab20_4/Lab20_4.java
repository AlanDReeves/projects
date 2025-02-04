package Lab20_4;

import java.util.Scanner;

/*
 * @author Alan Reeves
 * This class has edits a string in several ways depending on user input.
 */
public class Lab20_4 {

    /*
     * This is the main method. 
     * @param args not used.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a sample text:");
        System.out.println();

        String userString = scan.nextLine();

        System.out.printf("You entered: %s\n", userString);
        System.out.println();

        char userInput = ' ';


        while(userInput != 'q') {
            
            userInput = printMenu(scan);
            executeMenu(userInput, userString, scan);

        }

    }//end of main

    /*
     * prints menu.
     * This version of the method was a dummy method to complete online grading and is not used.
     */
    public static void printMenu() {
        System.out.println("MENU");
        System.out.println("c - Number of non-whitespace characters");
        System.out.println("w - Number of words");
        System.out.println("f - Find text");
        System.out.println("r - Replace all !'s");
        System.out.println("s - Shorten spaces");
        System.out.println("q - Quit");
    }//end of printMenu()

    /*
     * prints a menu for the user and takes a character as input.
     * accepts a string but takes just the first character.
     * @param scan is a scanner object.
     */
    public static char printMenu(Scanner scan) {
        System.out.println("MENU");
        System.out.println("c - Number of non-whitespace characters");
        System.out.println("w - Number of words");
        System.out.println("f - Find text");
        System.out.println("r - Replace all !'s");
        System.out.println("s - Shorten spaces");
        System.out.println("u - First letter of first word of sentence upper case");
        System.out.println("q - Quit");
        System.out.println();
        System.out.println("Choose an option:");
        char returnChar = scan.next().charAt(0);
        return returnChar;
    }//end of printMenu(Scanner)

    /*
     * takes the user's input and calls the corresponding method.
     * @param userInput is the char from printMenu.
     * @param userString is the user's input string to be edited.
     * @param scan is a Scanner object from main.
     */
    public static void executeMenu(char userInput, String userString, Scanner scan) {

        switch(userInput) {
            case 'q':{
                return;
            }
            case 'c': {
                System.out.printf(
                    "Number of non-whitespace characters: %d\n", getNumOfNonWSCharacters(userString));
                    System.out.println();
                    return;
                    
            }
            case 'w': {
                System.out.printf("Number of words: %d\n", getNumOfWords(userString));
                System.out.println();
                return;
            }
            case 'f': {
                scan.nextLine();
                System.out.println("Enter a word or phrase to be found:");
                //Scanner checkScan = new Scanner(System.in);
                String checkString = scan.nextLine();
                findText(checkString, userString);
                System.out.println();
                return;

            }
            case 'r': {
                System.out.printf("Edited text: %s\n", replaceExclamation(userString));
                System.out.println();
                return;

            }
            case 's': {
                System.out.printf("Edited text:  %s \n", shortenSpace(userString));
                System.out.println();
                return;

            }
            case 'u': {
                System.out.printf("Edited text: %s\n", capitalizeString(userString));

                return;
            }
            default: {
                System.out.println("Invalid input. Try again.");
                System.out.println();

            }
        }

    }//end of executeMenu

    /*
     * takes the user string and returns the number of characters except spaces.
     */
    public static int getNumOfNonWSCharacters(String userString) {
        int count = userString.length();
        for (int i = 0; i < userString.length(); i++) {
            if (userString.charAt(i) == ' ') {
                count -= 1;
            }
        }

        return count;
    }//end of getNumOfWSCharacters

    /*
     * takes the user string and returns the number of blanks between characters.
     */
    public static int getNumOfWords(String userString) {
        int count = 1;
        for (int i = 1; i < userString.length(); i++) {
            if (userString.charAt(i) == ' ' && userString.charAt(i-1) != ' ') {
                count += 1;
            }
        }
        return count;
    }//end of getNumOfWords


    /*
     * Counts the number of instances of a given check string.
     * @param checkString the string to search for.
     * @param userString the user's input.
     */
    public static int findText(String checkString, String userString) {
        int count = 0;
        int lastIndex = 0;

        if (userString.equalsIgnoreCase(checkString)) {
            System.out.printf("\"%s\" instances: %d\n", checkString, 1);
            return 1;
        }

        while (lastIndex != -1) {
            lastIndex = userString.indexOf(checkString, lastIndex);
            if (lastIndex != -1) {
                count += 1;
                lastIndex += checkString.length();
            }
        }
        System.out.printf("\"%s\" instances: %d\n", checkString, count);
        return count;
    }//end of findText

    /*
     * replaces exclamation points in a string with periods.
     */
    public static String replaceExclamation(String userString) {
        String returnString = "";
        for (int i = 0; i < userString.length(); i++) {
            if (userString.charAt(i) != '!') {
                returnString += userString.charAt(i);
            } else {
                returnString += '.';
            }
        }
        return returnString;
    }//end replaceExclamation

    /*
     * removes extra spaces from strings so that there are no double spaces. 
     */
    public static String shortenSpace(String userString) {
        String returnString = "";
        userString = userString.trim();
        for (int i = 0; i < userString.length(); i++) {
            if (i + 1 == userString.length() && userString.charAt(i) != ' ') {
                returnString += userString.charAt(i);
                break;
            } else if (userString.charAt(i) == ' ' && userString.charAt(i+1) == ' ') {
                continue;
            } else {
                returnString += userString.charAt(i);
            }
        }

        return returnString;
    }

    /*
     * capitalizes the first word of every sentence.
     */
    public static String capitalizeString(String userString) {
        String returnString = "";
        char[] arry = userString.toCharArray();
        boolean periodSeen = true;
        for (int i = 0; i < arry.length; i++) {
            if (periodSeen && arry[i] != ' ') {
                arry[i] = Character.toUpperCase(arry[i]);
                periodSeen = false;
            } else if (arry[i] == '.') {
                periodSeen = true;
            }
        }

        for (char letter : arry) {
            returnString += letter;
        }

        return returnString;
    }

}
