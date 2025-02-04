package Lab20_6;

/*
 * @author Alan Reeves
 * to be used with ShoppingCart and ShoppingCartManager
 */
public class ItemToPurchase {

    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private String itemDescription;

    public ItemToPurchase() {
        itemName = "none";
        itemPrice = 0;
        itemQuantity = 0;
        itemDescription = "none";
    }

    public ItemToPurchase(String itemName, String itemDescription, int itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public void setName(String newName) {
        itemName = newName;
    }

    public void setPrice(int newPrice) {
        itemPrice = newPrice;
    }

    public void setQuantity(int newQuantity) {
        itemQuantity = newQuantity;
    }

    public void setDescription(String newDescription) {
        itemDescription = newDescription;
    }

    public String getName() {
        return itemName;
    }

    public int getPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public int getTotalCost() {
        return itemPrice * itemQuantity;
    }

    public String getDescription() {
        return itemDescription;
    }

    public void printItemCost() {
        System.out.printf("%s %d @ $%d = $%d\n", 
        itemName, itemQuantity, itemPrice, this.getTotalCost());
    }

    public void printItemDescription() {
        System.out.printf("%s: %s\n", itemName, itemDescription);
    }

}
