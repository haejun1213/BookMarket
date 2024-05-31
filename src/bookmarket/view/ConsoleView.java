package bookmarket.view;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import bookmarket.model.BookStorage;
import bookmarket.model.Cart;
import bookmarket.model.Customer;

public class ConsoleView {

	public void displayWelcome() {

//		String welcome2 = "   Welcome to Haejun's Book Market   ";
//
//		for (int i = 0; i < 39; i++) {
//			flowTime(80);
//			System.out.print("*");
//		}
//		System.out.println();
//		System.out.print("*");
//		char[] charArr = welcome2.toCharArray();
//		for (int i = 0; i < charArr.length; i++) {
//			flowTime(50);
//			System.out.print(charArr[i]);
//		}
//		System.out.print("*");
//		System.out.println();
//		for (int i = 0; i < 39; i++) {
//			flowTime(50);
//			System.out.print("*");
//		}
//		System.out.println();
//		flowTime(200);
		String welcome = "*****************************************\n" + "*    Welcome to Haejun's Book Market    *\n"
				+ "*****************************************";
		System.out.println(welcome);

	}

	void flowTime(int time) {
		sleep(time);
	}

	private void sleep(int i) {
		try {
			TimeUnit.MILLISECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int selectMenuNo(String[] menuList) {

		displayMenu(menuList);

		int menu;
		do {
			System.out.print(">> 메뉴 선택 : ");

			menu = inputNumberWithValidation(">> 숫자를 입력하세요: ");

			if (menu < 0 || menu >= menuList.length)
				System.out.println(">> 0부터 " + (menuList.length - 1) + "번 사이의 숫자를 입력하세요.");

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
		int bookId;
		boolean result;
		do {
			System.out.print(">> 도서의 ID를 입력하세요 : ");
			bookId = inputNumberWithValidation(">> 숫자를 입력하세요: ");
			result = BookStorage.isValidBook(bookId);
			if (!result)
				System.out.println(">> 잘못된 도서의 ID입니다.");
		} while (!result);
		return bookId;

	}

	public int selectBookId(Cart cart) {
		int bookId;
		boolean result;
		do {
			System.out.print(">> 도서의 ID를 입력하세요 : ");
			bookId = inputNumberWithValidation(">> 숫자를 입력하세요: ");
			result = cart.isValidItem(bookId);
			if (!result)
				System.out.println(">> 잘못된 도서의 ID입니다.");
		} while (!result);
		return bookId;
	}

	public int inputNumber(int min, int max) {
		int number;
		do {
			System.out.print(">> 수량 입력 (" + min + " ~ " + max + "): ");
			number = inputNumberWithValidation(">> 숫자를 입력하세요: ");
			if (number < min || number > max)
				System.out.println(">> 잘못된 수량입니다.");
		} while (number < min || number > max);
		return number;
	}

	public int inputNumberWithValidation(String message) {
		Scanner sc = new Scanner(System.in);
		try {
			int number = sc.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print(message);
			return inputNumberWithValidation(message);
		}
	}

	public void inputCustomerInfo(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름과 전화번호를 입력하세요.");
		System.out.print(">> 이름 : ");
		customer.setName(sc.nextLine());
		System.out.print(">> 전화번호 : ");
		customer.setPhone(sc.nextLine());
	}

	public void displayDeliveryInfo(Cart cart, Customer customer) {

		System.out.println();
		// 장바구니 보여주기
		System.out.println("***** 주문할 도서 ******");
		displayCart(cart);

		// 배송 정보 보여주기 - 고객 이름, 전화번호, 주소, 이메일주소
		System.out.println("***** 배송 정보 ******");
		System.out.println(">> 이름 : " + customer.getName());
		System.out.println(">> 전화번호 : " + customer.getPhone());
		System.out.println(">> 이메일 : " + customer.getEmail());
		System.out.println(">> 주소 : " + customer.getAddress());

	}

	public void inputDeliveryInfo(Customer customer) {
		if (customer.getAddress() == null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("주소와 메일을 입력하세요.");
			System.out.print(">> 주소 : ");
			customer.setAddress(sc.nextLine());
			System.out.print(">> 메일 : ");
			customer.setEmail(sc.nextLine());
		}

	}

	public String inputString(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message);
		return sc.nextLine();
	}

	public int inputPrice(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message);
		try {
			int number = sc.nextInt();
			return number;
		} catch (Exception e) {
			return inputPrice(message);
		}
	}


}
