package NoOOP;

import java.util.Scanner;

public class Bookmarket1 {

	static int menuNum = 4;
	static int bookNum = 3;
	static int bookEm = 4;

	static String[][] bookList = { { "ID2401", "쉽게 배우는 자바 프로그래밍 2판", "우종정", "한빛아" },
			{ "ID2402", "코딩 자율학습 HTML+CSS+자바스크립트", "김기수", "길벗", "30,000원" },
			{ "ID2403", "Do It! 자료구조와 함께 배우는 알고리즘 입문 - 자바편", "보요시바타", "이지스퍼" } };
	static int[] cartList = { 0, 0, 0 };
	static int numCartItem = 0;

	public static void main(String[] args) {
		displayWelcome();
		boolean quit = false;
		while (!quit) {
			int menu = displayGetMenu();
			switch (menu) {
			case 1:
				displayBookList();
				break;

			case 2:
				displayCartList();
				break;

			case 3:
				addBookCart();
				break;

			case 4:
				clearCart();
				break;
			case 0:
				end();
				quit = true;
				break;
			default:
				wrongMenuNum();

			}
		}

	}

	private static void wrongMenuNum() {
System.out.println("없는 메뉴입니다. 0번부터 " + menuNum +"번까지의 메뉴 중에서 선택하세요");
		
	}

	private static void displayCartList() {
		int cart = bookNum;
		for (int i = 0; i < bookNum; i++) {
			if (cartList[i] == 0) {
				cart--;
			}
		}
		if (cart == 0) {
			System.out.println(">> 장바구니가 비어 있습니다.");
		} else {
			for (int i = 0; i < bookNum; i++) {
				if (cartList[i] != 0) {
					System.out.print(cartList[i] + "권, ");
					for (int j = 0; j < bookEm; j++) {

						System.out.print(bookList[i][j] + ", ");
					}
					System.out.println();

				}
			}
		}

	}

	private static void end() {
		System.out.println(">> Haejun's Book Market을 종료합니다. ");
	}

	private static void clearCart() {
		int cart = bookNum;
		for (int i = 0; i < bookNum; i++) {
			if (cartList[i] == 0) {
				cart--;
			}

		}
		if (cart == 0) {
			System.out.println(">> 장바구니가 비어 있습니다.");
		} else {

			for (int i = 0; i < bookNum; i++) {
				cartList[i] = 0;
			}
			System.out.println(">> 장바구니에 있는 아이템을 모두 삭제하였습니다.");
		}
	}

	private static void addBookCart() {
		x: while (true) {
			int list = bookNum;
			System.out.println(">> 도서 목록");
			System.out.println("------------------------------------------------------------------------");
			for (int i = 0; i < bookNum; i++) {
				for (int j = 0; j < bookEm; j++) {
					System.out.print(bookList[i][j] + ", ");
				}
				System.out.println();
			}
			System.out.print("------------------------------------------------------------------------\n"
					+ ">> 추가할 도서 ID 입력 : ");
			Scanner sc = new Scanner(System.in);
			String bookID = sc.nextLine();
			for (int i = 0; i < bookNum; i++) {
				if (bookID.equals(bookList[i][0])) {
					System.out.println(">> 장바구니 추가 : " + bookList[i][1]);
					cartList[i]++;
					break x;
				} else {
					list--;
				}
			}
			if (list == 0) {
				System.out.println(">> 없는 ID입니다. 도서 목록에 있는 ID를 입력하세요.");
			}

		}
	}

	static void displayBookList() {

		System.out.println("------------------------------------------------------------------------");
		for (int i = 0; i < bookNum; i++) {
			for (int j = 0; j < bookEm; j++) {
				System.out.print(bookList[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------");

	}

	static int displayGetMenu() {
		Scanner sc = new Scanner(System.in);
		String menu = "=========================================\n" + "1. 도서 목록 보기\r\n" + "2. 장바구니 보기\r\n"
				+ "3. 장바구니에 도서 추가\r\n" + "4. 장바구니 비우기\r\n" + "0. 종료\r\n" + "=========================================\n"
				+ ">> 메뉴 선택 : ";
		System.out.print(menu);

		return sc.nextInt();
	}

	static void displayWelcome() {

		String welcome = "*****************************************\n" + "*    Welcome to Haejun's Book Market    *\n"
				+ "*****************************************";
		System.out.println(welcome);

	}

}
