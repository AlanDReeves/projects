package Lab20_8;

import java.util.Scanner;

/*
 * @author Alan Reeves
 */
public class RunMe {
    // Prints the SortedNumberList's contents, in order from head to tail
    // Taken directly from the lab's LabProgram class
	public static void printList(SortedNumberList list) {
		Node node = list.head;
		while (null != node) {
			System.out.print(node.getData() + " ");
			node = node.getNext();
		}
		System.out.println();
	}

    public static void printMenu() {
        System.out.println("Sorted Number List Menu: ");
        System.out.println();
        System.out.println("a - Add node");
        System.out.println("d - Remove node");
        System.out.println("o = Output list");
        System.out.println("q - Quit");
        System.out.println();
    }

    public static void executeMenu(char input, SortedNumberList numList, Scanner scan) {
        switch (input) {
            case 'a': {
                System.out.println("Please type a floating point number to enter.");
                double insertDouble = scan.nextDouble();
                numList.insert(insertDouble);
                scan.nextLine();
                System.out.println(insertDouble + " added to list");
                System.out.println();
                return;
            }
            case 'd': {
                System.out.println("Please type a floating point number to remove.");
                double removeDouble = scan.nextDouble();
                boolean success = numList.remove(removeDouble);
                if (success) {
                    System.out.println(removeDouble + " removed.");
                } else {
                    System.out.println(removeDouble + "not found");
                }
                scan.nextLine();
                System.out.println();
                return;
            }
            case 'o': {
                System.out.println("Current list:");
                printList(numList);
                System.out.println();
            }
            default:
            return;
            }

        }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SortedNumberList numList = new SortedNumberList();
        String inputString;
        char input;

        do {
            printMenu();
            inputString = scan.nextLine();
            input = inputString.charAt(0);
            executeMenu(input, numList, scan);

        } while (input != 'q');


    }
} //end of RunMe