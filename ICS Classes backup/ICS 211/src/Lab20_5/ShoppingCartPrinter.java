package Lab20_5;

import java.util.Scanner;

/*
 * @author Alan Reeves
 * This method uses the ItemToPurchase class to calculate total cost of buying two items.
 */
public class ShoppingCartPrinter {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        
        
        System.out.println("Item 1");
        System.out.println("Enter the item name:");
        String item1Name = scan.nextLine();
        System.out.println("Enter the item price:");
        int item1Price = scan.nextInt();
        System.out.println("Enter the item quantity:");
        int item1Quantity = scan.nextInt();
        System.out.println();

        ItemToPurchase item1 = new ItemToPurchase();
        item1.setName(item1Name);
        item1.setItemPrice(item1Price);
        item1.setItemQuantity(item1Quantity);

        scan.nextLine();


        System.out.println("Item 2");
        System.out.println("Enter the item name:");
        String item2Name = scan.nextLine();
        System.out.println("Enter the item price:");
        int item2Price = scan.nextInt();
        System.out.println("Enter the item quantity:");
        int item2Quantity = scan.nextInt();

        ItemToPurchase item2 = new ItemToPurchase();
        item2.setName(item2Name);
        item2.setItemPrice(item2Price);
        item2.setItemQuantity(item2Quantity);

        System.out.println();
        System.out.println("TOTAL COST");
        System.out.printf("%s %d @ $%d = $%d\n", item1.getName(), item1.getItemQuantity(), 
        item1.getItemPrice(), item1.getTotalCost());

        System.out.printf("%s %d @ $%d = $%d\n", item2.getName(), item2.getItemQuantity(), 
        item2.getItemPrice(), item2.getTotalCost());
        System.out.println();

        
        System.out.printf("Total: $%d\n", item1.getTotalCost() + item2.getTotalCost());

        scan.close();
    }

}
