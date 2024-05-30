package bookmarket.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BookStorage {

	ArrayList<Book> bookList = new ArrayList<>();

	final int MAX_QUANTITIY = 10;

	private String bookFilename = "booklist.txt";

	public BookStorage() throws IOException {
		loadBookListFromFile();
	}

	private void loadBookListFromFile() throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(bookFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null) {
				int id = Integer.parseInt(idStr);
				String title = br.readLine();
				String author = br.readLine();
				String publisher = br.readLine();
				int price = Integer.parseInt(br.readLine());
				bookList.add(new Book(id, title, author, publisher, price));
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getNumBooks() {
		return bookList.size();
	}

	public String getBookInfo(int i) {
		return bookList.get(i).toString();
	}

	public boolean isValidBook(int bookId) {

		for (Book book : bookList) {
			if (book.getBookId() == bookId)
				return true;
		}

		return false;
	}

	public Book getBookId(int bookId) {

		for (Book book : bookList) {
			if (book.getBookId() == bookId)
				return book;
		}
		return null;
	}

	public int getMaxQuantitiy() {
		// TODO Auto-generated method stub
		return MAX_QUANTITIY;
	}

}
