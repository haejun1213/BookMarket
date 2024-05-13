package bookmarket.view;

import java.util.Scanner;

import bookmarket.model.BookStorage;
import bookmarket.model.Cart;

public class ConsoleView {

	public void displayWelcome() {

		String welcome = "*****************************************\n" + "*    Welcome to Haejun's Book Market    *\n"
				+ "*****************************************";
		System.out.println(welcome);

	}

	public int selectMenuNo(String[] menuList) {

		displayMenu(menuList);

		Scanner sc = new Scanner(System.in);
		int menu;
		do {
			System.out.print(">> 메뉴 선택 : ");
			menu = sc.nextInt();
			if (menu < 0 || menu >= menuList.length)
				System.out.println("0부터 " + (menuList.length - 1) + "번 사이의 숫자를 입력하세요.");

		} while (menu < 0 || menu >= menuList.length);
		return menu;
	}

	private void displayMenu(String[] menuList) {

		System.out.println("=========================================");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(menuList[i]);
		}
		System.out.println(menuList[0]);
		System.out.println("=========================================");

	}

	public void displayBookInfo(BookStorage mBookStorage) {
		System.out.println("------------------------------------------------------------------------");
		for (int i = 0; i < mBookStorage.getNumBooks(); i++) {
			String bookInfo = mBookStorage.getBookInfo(i);
			System.out.println(bookInfo);
		}
		System.out.println("------------------------------------------------------------------------");
	}

	public void displayCart(Cart mCart) {
		if (mCart.isEmpty()) {
			System.out.println(">> 장바구니가 비어 있습니다.");
			return;
		}
		System.out.println("------------------------------------------------------------------------");
		for (int i = 0; i < mCart.getNumItem(); i++) {
			System.out.println(mCart.getItemInfo(i));
		}
		System.out.println("------------------------------------------------------------------------");
	}

	public void showMessage(String message) {

		System.out.println(message);
	}

	public boolean askConfirm(String message, String string) {
		System.out.print(message);

		Scanner sc = new Scanner(System.in);

		if (sc.nextLine().equals(string))
			return true;

		return false;
	}

	public int selectBookId(BookStorage BookStorage) {
		Scanner sc = new Scanner(System.in);

		int bookId;
		boolean result;
		do {
			System.out.print(">> 추가할 도서의 ID를 입력하세요 : ");
			bookId = sc.nextInt();
			result = BookStorage.isValidBook(bookId);
			if (!result)
				System.out.println(">> 잘못된 도서의 ID입니다.");
		} while (!result);
		return bookId;

	}

	public int selectBookId(Cart cart) {
		Scanner sc = new Scanner(System.in);

		int bookId;
		boolean result;
		do {
			System.out.print(">> 도서의 ID를 입력하세요 : ");
			bookId = sc.nextInt();
			result = cart.isValidItem(bookId);
			if (!result)
				System.out.println(">> 잘못된 도서의 ID입니다.");
		} while (!result);
		return bookId;
	}

	public int inputNumber(int min, int max) {
		Scanner input = new Scanner(System.in);
		int number;
		do {
			System.out.print(">> 수량 입력 (" + min + " ~ " + max + "): ");
			number = input.nextInt();
			if (number < min || number > max)
				System.out.println(">> 잘못된 수량입니다.");
		} while (number < min || number > max);
		return number;
	}

}
