package bookmarket.model;

public class CartItem {

	Book book;
	int bookId;
	int quantity;
	
	public CartItem(Book book) {
		this.book = book;
		this.bookId = book.bookId;
		this.quantity = 1;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public int getPrice() {
		return book.getPrice() * quantity;
	}
	
	public String toString() {
		return book.getBookId() + ", " + book.getTitle() + ", " + quantity + "권, " + getPrice() + "원";
}
}
