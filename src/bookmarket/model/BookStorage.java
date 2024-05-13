package bookmarket.model;

import java.util.ArrayList;

public class BookStorage {
	
	ArrayList<Book> bookList = new ArrayList<>();

	final int MAX_QUANTITIY = 10;

	public BookStorage() {
		bookList.add(new Book(2401, "쉽게 배우는 자바 프로그래밍 2판", "우종정", "한빛아카데미", 20000));
		bookList.add(new Book(2402, "코딩 자율학습 HTML+CSS+자바스크립트", "김기수", "길벗", 30000));
		bookList.add(new Book(2403, "Do It! 자료구조와 함께 배우는 알고리즘 입문 - 자바편", "보요시바타", "이지스퍼블리싱", 25000));
				}


	public int getNumBooks() {
		return bookList.size();
	}

	public String getBookInfo(int i) {
		return bookList.get(i).toString();
	}

	public boolean isValidBook(int bookId) {

		for(Book book : bookList) {
			if (book.getBookId() == bookId) return true;
		}
		
		return false;
	}

	public Book getBookId(int bookId) {

		for(Book book : bookList) {
			if (book.getBookId() == bookId) return book;
		}
		return null;
	}


	public int getMaxQuantitiy() {
		// TODO Auto-generated method stub
		return MAX_QUANTITIY;
	}

}

