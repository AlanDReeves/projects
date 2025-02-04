package Lab20_6;

import java.util.ArrayList;

/*
 * @author Alan Reeves
 * To be used with ShoppingCartManager.java
 */
public class ShoppingCart {
    private String customerName;
    private String currentDate;
    private ArrayList<ItemToPurchase> cartItems;

    public ShoppingCart() {
        customerName = "none";
        currentDate = "January 1, 2016";
        cartItems = new ArrayList<ItemToPurchase>();
    }

    public ShoppingCart(String newName, String newDate) {
        customerName = newName;
        currentDate = newDate;
        cartItems = new ArrayList<ItemToPurchase>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return currentDate;
    }

    public void addItem(ItemToPurchase newItem) {
        cartItems.add(newItem);
    }

    public void removeItem(String itemName) {
        int removeIndex = -1;
        boolean present = false;

        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getName().equals(itemName)) {
                removeIndex = i;
                present = true;
            }
        }
        if (present) {
            cartItems.remove(removeIndex);
        }
        if (!present) {
            System.out.println("Item not found in cart. Nothing removed.");
        }
    }

    public void modifyItem(ItemToPurchase item, int itemQuantity) {
        item.setQuantity(itemQuantity);
        
    }

    public int getNumItemsInCart() {
        int count = 0;
        for (ItemToPurchase item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }

    public int getCostOfCart() {
        int totalCost = 0;
        for (ItemToPurchase item : cartItems) {
            totalCost += item.getTotalCost();
        }
        return totalCost;
    }

    public void printTotal() {
        if (cartItems.isEmpty()) {
            System.out.printf("%s's Shopping Cart - %s\n", 
            customerName, currentDate);
            System.out.println("Number of Items: " + cartItems.size());
            System.out.println();
            System.out.println("SHOPPING CART IS EMPTY");
            System.out.println();
            System.out.println("Total: $0");
        } else {
            System.out.printf("%s's Shopping Cart - %s\n", 
            customerName, currentDate);

            int totalItems = 0;
            for (ItemToPurchase item : cartItems) {
                totalItems += item.getQuantity();
            }

            System.out.println("Number of Items: " + totalItems);
            System.out.println();

            for (ItemToPurchase item : cartItems) {
                item.printItemCost();
            }
            System.out.println();
            System.out.printf("Total: $%d\n", this.getCostOfCart());
        }
    }

    public void printDescriptions() {
        if (cartItems.isEmpty()) {
            System.out.println("SHOPPING CART IS EMPTY");
        } else {
            System.out.printf("%s's Shopping Cart - %s\n", 
            customerName, currentDate);
            System.out.println();

            System.out.println("Item Descriptions");
            for (ItemToPurchase item : cartItems) {
                item.printItemDescription();
            }
        }
    }

    public ItemToPurchase findItemByName(String name) {
        ItemToPurchase returnItem = null;
        for (ItemToPurchase item : cartItems) {
            if (item.getName().equals(name)) {
                returnItem = item;
            }
        } return returnItem;
    }
}
