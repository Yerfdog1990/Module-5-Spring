package aop;

public interface IShop {
    void addItem(String itemName, double price);
    void removeItem(String itemName);
    void getPrice(String itemName);
}
