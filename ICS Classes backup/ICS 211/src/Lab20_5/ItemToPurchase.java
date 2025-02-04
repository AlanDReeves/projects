package Lab20_5;

public class ItemToPurchase {

    private String itemName;
    private int itemPrice;
    private int itemQuantity;

    public ItemToPurchase() {
        itemName = "none";
        itemPrice = 0;
        itemQuantity = 0;
    }

    public void setName(String newName) {
        itemName = newName;
    }

    public void setItemPrice(int newPrice) {
        itemPrice = newPrice;
    }

    public void setItemQuantity(int newQuantity) {
        itemQuantity = newQuantity;
    }

    public String getName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public int getTotalCost() {
        return itemPrice * itemQuantity;
    }

}
