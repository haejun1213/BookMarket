package bookmarket.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private ArrayList<CartItem> itemList = new ArrayList<>();

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

	private CartItem getCartItem(int bookId) {
		for (CartItem item : itemList) {
			if (item.bookId == bookId) {
				return item;
			}
		}

		return null;
	}

	public boolean isValidItem(int bookId) {

		for (CartItem item : itemList) {
			if (item.bookId == bookId)
				return true;
		}
		return false;

	}

	public void deleteItem(int bookId) {
		CartItem item = getCartItem(bookId);
		itemList.remove(item);

	}

	public void updateQuantity(int bookId, int quantity) {

		if (quantity == 0) {
			deleteItem(bookId);
		} else {
			CartItem item = getCartItem(bookId);
			item.setQuantity(quantity);
		}

	}
}
