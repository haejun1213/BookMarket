package NoOOP;

import java.util.Scanner;

public class Bookmarket2 {

	static String[][] bookList = { { "ID2401", "쉽게 배우는 자바 프로그래밍 2판", "우종정", "한빛아카데미", "20,000원" },
			{ "ID2402", "코딩 자율학습 HTML+CSS+자바스크립트", "김기수", "길벗", "30,000원" },
			{ "ID2403", "Do It! 자료구조와 함께 배우는 알고리즘 입문 - 자바편", "보요시바타", "이지스퍼블리싱", "25,000원" } };
	
	static int books = 3;
	static int list = 5;

	static int[] cartList = { 0, 0, 0 };
	static int numCartItem = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		displayWelcome();
		boolean quit = false;
		while (!quit) {
			getMenuNum();
			int menuNum = sc.nextInt();
			switch (menuNum) {
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
				marketOut();
				quit = true;
				break;
			default:
				wrongNum();
				

			}
		}

	}
	

	static void getMenuNum() {
		System.out.print("=========================================\r\n"
				+ "1. 도서 목록 보기\r\n"
				+ "2. 장바구니 보기\r\n"
				+ "3. 장바구니에 도서 추가\r\n"
				+ "4. 장바구니 비우기\r\n"
				+ "0. 종료\r\n"
				+ "=========================================\r\n"
				+ ">> 메뉴 선택 : ");
	}


	static void displayWelcome() {
		System.out.println("*****************************************\n" 
						 + "*    Welcome to Haejun's Book Market    *\n"
						 + "*****************************************");
	}
	static void displayBookList() {
		System.out.println(">> 도서 목록\n"
				+ "------------------------------------------------------------------------");
		for(int i = 0; i < books; i++) {
			for(int j = 0; j < list; j++) {
				System.out.print(bookList[i][j] + ", ");
			}System.out.println();
		}
		System.out.println("------------------------------------------------------------------------");
		
	}
	static void displayCartList() {
		int cart = books;
		for (int i = 0; i < books; i++) {
			if(cartList[i] == 0) {
				cart--;
			}
		}
		if(cart == 0) {
			System.out.println(">> 장바구니가 비어 있습니다.");
		} else {
			System.out.println(">> 장바구니 보기\n"
					+ "------------------------------------------------------------------------");
			for(int i = 0; i < books; i++) {
				if(cartList[i] != 0) {
					System.out.print(cartList[i] + "권, ");
				for(int j = 0; j < list; j++) {
					System.out.print(bookList[i][j] + ", ");
				}System.out.println();
				}
			}
			System.out.println("------------------------------------------------------------------------");
		}
		
		
	}
	static void addBookCart() {
		x : while (true) {
		System.out.println(">> 도서 목록\n"
				+ "------------------------------------------------------------------------");
		for(int i = 0; i < books; i++) {
			for(int j = 0; j < list; j++) {
				System.out.print(bookList[i][j] + ", ");
			}System.out.println();
		}
		System.out.print("------------------------------------------------------------------------\r\n"
				+ ">> 추가할 도서 ID 입력 : ");
		Scanner sc = new Scanner(System.in);
		String bookID = sc.nextLine();
		int id = books;
		String bookName = null;
		for(int i = 0; i < books; i++) {
		if (bookID.equals(bookList[i][0])) {
			bookName = bookList[i][1];
			cartList[i]++;
			System.out.println(">> 장바구니 추가 : " + bookName);
			break x;
		} else {
			id--;
		}
		}
		if(id == 0) {
			System.out.println(">> 없는 ID입니다. 도서 목록에 있는 ID를 입력하세요.");
		} 
			
		
		}
		
	}
	static void clearCart() {
		int cart = books;
		for (int i = 0; i < books; i++) {
			if(cartList[i] == 0) {
				cart--;
			}
		}
		if(cart == 0) {
			System.out.println(">> 장바구니가 비어 있습니다.");
		} else {
			for (int i = 0; i < books; i++) {
					cartList[i] = 0;
				}
			
			System.out.println(">> 장바구니에 있는 아이템을 모두 삭제하였습니다.");
		}
		
	}
	static void marketOut() {
		System.out.println(">>Haejun's Book Market을 종료하였습니다.");
	}
	static void wrongNum() {
		System.out.println("없는 메뉴입니다. 0번부터 4번까지의 메뉴 중에서 선택하세요.");
	}

}