package aop;

import org.springframework.stereotype.Component;

@Component
public class Shop implements IShop{
    @Override
    public void addItem(String itemName, double price) {
        System.out.printf("Adding %s costing %.2f.\n", itemName, price);
    }

    @Override
    public void removeItem(String itemName) {
        System.out.printf("Removing %s.\n", itemName);
    }

    @Override
    public void getPrice(String itemName) {
        if(itemName.equals("Banana")){
             System.out.println("Price: 1.2");
         }else{
             throw new IllegalArgumentException("Item not found");
         }
    }
}
