package Lab20_6;

import java.util.Scanner;

/*
 * @author Alan Reeves
 */
public class ShoppingCartManager {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        String customerName;
        String currentDate;
        
        
        System.out.println("Enter customer's name:");
        customerName = scan.nextLine();
        System.out.println("Enter today's date:");
        currentDate = scan.nextLine();
        System.out.println();

        System.out.printf("Customer name: %s\nToday's date: %s\n\n", 
        customerName, currentDate);

        ShoppingCart cart = new ShoppingCart(customerName, currentDate);

        char userInput = '1';

       

        while (userInput != 'q') {

            printMenu();
            System.out.println();
            System.out.println("Choose an option:");
            userInput = scan.nextLine().charAt(0);
            //check if input is acceptable
            
            while(!inputChecker(userInput)) {
                System.out.println("Choose an option:");
                userInput = scan.nextLine().charAt(0);
            }
            
            
            //input known to be acceptable, continue
            executeMenu(userInput, cart, scan);
            
        }


    }//end of main

    public static boolean inputChecker(char c) {
        switch (c) {
            case 'a':
            return true;
            case 'd':
            return true;
            case 'c':
            return true;
            case 'i':
            return true;
            case'o':
            return true;
            case'q':
            return true;
            default:
            return false;
        }

    }

    public static void printMenu() {
        System.out.println("MENU");
        System.out.println("a - Add item to cart");
        System.out.println("d - Remove item from cart");
        System.out.println("c - Change item quantity");
        System.out.println("i - Output items' descriptions");
        System.out.println("o - Output shopping cart");
        System.out.println("q - Quit");
    }

    public static void executeMenu(char userChoice, ShoppingCart cart, Scanner scan) {
        switch (userChoice) {
            case 'q': {
                return;
            }
            case 'o': {
                System.out.println("OUTPUT SHOPPING CART");
                cart.printTotal();
                System.out.println();
                return;
            }
            case 'i': {
                System.out.println();
                System.out.println("OUTPUT ITEMS' DESCRIPTIONS");
                cart.printDescriptions();
                System.out.println();
                return;
            }
            case 'c': {
                System.out.println();
                System.out.println("CHANGE ITEM QUANTITY");
                System.out.println("Enter the item name:");
                String itemName = scan.nextLine();
                System.out.println("Enter the new quantity:");
                int newQuantity = scan.nextInt();

                if (cart.findItemByName(itemName) == null) {
                    System.out.println("Item not found in cart. Nothing modified.");
                } else {

                cart.modifyItem(cart.findItemByName(itemName), newQuantity);
                }
                scan.nextLine();
                System.out.println();
                return;
            }
            case 'd': {
                System.out.println();
                System.out.println("REMOVE ITEM FROM CART");
                System.out.println("Enter name of item to remove:");
                String removeItem = scan.nextLine();

                cart.removeItem(removeItem);
                System.out.println();
                return;
            }
            case 'a': {
                System.out.println();
                System.out.println("ADD ITEM TO CART");
                System.out.println("Enter the item name:");
                String newName = scan.nextLine();
                
                System.out.println("Enter the item description:");
                String newDescription = scan.nextLine();

                System.out.println("Enter the item price:");
                int newPrice = scan.nextInt();

                System.out.println("Enter the item quantity:");
                int newQuantity = scan.nextInt();

                ItemToPurchase addItem = new ItemToPurchase(newName, newDescription, newPrice, newQuantity);
                cart.addItem(addItem);
                scan.nextLine();
                System.out.println();
                }
        }
    }


}
