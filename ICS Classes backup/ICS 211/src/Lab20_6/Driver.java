package Lab20_6;

public class Driver {
    public static void main(String[] args) {
        ShoppingCart test = new ShoppingCart("John", "Jan 1, 2020");

        ItemToPurchase testItem = new ItemToPurchase("Test Item", "description", 999, 1);
        System.out.println("Before modification");

        test.addItem(testItem);

        test.printTotal();

        test.modifyItem(test.findItemByName("Test Item"), 100);

        System.out.println("After modification");

        test.printTotal();

    }
}
