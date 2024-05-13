package bookmarket.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    ArrayList<CartItem> itemList = new ArrayList<>();
    
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public int getNumItem() {
        return itemList.size();
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    public String getItemInfo(int index) {
        return itemList.get(index).toString();
    }

    public void resetCart() {
        itemList.clear();
    }

    public void addItem(Book book) {
        CartItem item = getCartItem(book);
        if (item == null) {
            itemList.add(new CartItem(book));
        } else {
            item.addQuantity(1);
        }
    }
    
    public void deleteItem(Book book) {
    	
    	
    	
    }
    
    
    private CartItem getCartItem(Book book) {
        for (CartItem item : itemList) {
            if (item.getBook() == book) {
                return item;
            }
        }
        return null;
    }
}
