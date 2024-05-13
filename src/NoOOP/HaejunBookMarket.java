package NoOOP;

import java.util.Scanner;

public class HaejunBookMarket {

	static final int NUM_MENU = 4;
	static final int books = 3;
	static final int list = 5;
	
	static String[][] bookList = { { "ID2401", "쉽게 배우는 자바 프로그래밍 2판", "우종정", "한빛아카데미", "20,000원" },
			{ "ID2402", "코딩 자율학습 HTML+CSS+자바스크립트", "김기수", "길벗", "30,000원" },
			{ "ID2403", "Do It! 자료구조와 함께 배우는 알고리즘 입문 - 자바편", "보요시바타", "이지스퍼블리싱", "25,000원" } };
	
	static int[] cartList = {0, 0, 0};
//	static int numCartItem = 0;

	public static void main(String[] args) {
		displayWelcom();

		boolean quit = false;
		while (!quit) {
			int menu = displayGetMenu();
			switch (menu) {
			case 1:
				menuBookList();
				break;
			case 2:
				menuCartList();
				break;
			case 3:
				menuAddCartItem();
				break;
			case 4:
				menuClearCart();
				break;
			case 0:
				menuExit();
				quit = true;
				break;
			default:
				menuWrongNumber();

			}
		}
	}

	static void displayWelcom() {
		String wlcome = "*****************************************\n" 
					   +"*    Welcome to Haejun's Book Market    *\n"
					   +"*****************************************";
		System.out.println(wlcome);
	}

	static int displayGetMenu() {
		String menuStr = "=========================================\n"
						+"1. 도서 목록 보기\n" 
						+"2. 장바구니 보기\n"
						+"3. 장바구니에 도서 추가\n"
						+"4. 장바구니 비우기\n"
						+"0. 종료\n"
						+"=========================================\n";
		System.out.println(menuStr);
		System.out.print(">> 메뉴 선택 : ");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();

	}

	static void menuBookList() {
		System.out.println("------------------------------------------------------------------------");
		for (int i = 0; i < books; i++) {
			for (int j = 0; j < list; j++) {
				System.out.print(bookList[i][j] + ", ");
				
			}System.out.println();
		}
		System.out.println("------------------------------------------------------------------------");
	}

	static void menuCartList() {
		boolean cart = true;
		for(int i = 0; i < books; i++) {
			if(cartList[i] == 0) {
				cart = false;
			} else { 
				cart = true;
				break;
			}
		}
			if(cart) {
				System.out.println(">> 장바구니 보기\n"
						+ "------------------------------------------------------------------------");
				
				for (int i = 0; i < books; i++) {
					if (cartList[i] != 0) {
						for (int j = 0; j < list; j++) {
							System.out.print(cartList[i] + "권, ");
							bookInf(i,j);
					} System.out.println();
						
					}
				}
				System.out.println("------------------------------------------------------------------------");
				
				
			} else {
				System.out.println(">> 장바구니가 비어 있습니다.");
			}
		
		
				
	}

	

	static void menuAddCartItem() {
	
		Scanner sc = new Scanner(System.in);

		String bookName = null;
		
			menuBookList();
			
			System.out.print(">> 추가할 도서 ID 입력 : ");
			String bookID = sc.nextLine();
			int k = books;
			for(int i = 0; i < books; i++) {
				if(bookID.equals(bookList[i][0])) {
					cartList[i]++;
					bookName = bookList[i][1];
					break;
				
				} else
					k--;
			}
				if(k == 0) {
					System.out.println(">> 없는 ID입니다. 도서 목록에 있는 ID를 입력하세요.");
				} else {
			System.out.println(">> 장바구니 추가 : " + bookName);
			}
			
			
	}

	static void menuClearCart() {
		boolean cart = true;
		for(int i = 0; i < books; i++) {
			if(cartList[i] == 0) {
				cart = false;
			} else { 
				cart = true;
				break;
			}
		}
			if (cart) {
				System.out.println(">> 장바구니에 있는 아이템을 모두 삭제하였습니다.");
			for (int i = 0; i < books; i++) {
			cartList[i] = 0;
				}
			} else {
				System.out.println(">> 장바구니가 비어 있습니다.");
			}
	}

	static void menuExit() {
		System.out.println(">> Haejun's Book Market을 종료합니다. ");
	}
	
	static void menuWrongNumber() {
		System.out.println("없는 메뉴입니다. 0번부터 " + NUM_MENU + "번사이의 숫자를 입력해주세요.");
	}

	static void bookInf(int i, int j) {
		System.out.print(bookList[i][j] + ", ");
	}
}
