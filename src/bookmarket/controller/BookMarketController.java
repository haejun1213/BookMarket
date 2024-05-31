package bookmarket.controller;

import bookmarket.model.Admin;
import bookmarket.model.Book;
import bookmarket.model.BookStorage;
import bookmarket.model.Cart;
import bookmarket.model.Customer;
import bookmarket.view.ConsoleView;

public class BookMarketController {

	ConsoleView view;
	BookStorage mBookStorage;
	Cart mCart;
	Customer mCustomer;
	Admin mAdmin;

	String[] menuList = { "0. 종료", "1. 도서 정보 보기", "2. 장바구니 보기", "3. 장바구니에 도서 담기", "4. 장바구니 도서 삭제", "5. 장바구니 도서 수량 변경",
			"6. 장바구니 비우기", "7. 주문", "8. 관리자 모드" };

	String[] adminMenuList = { "0. 종료", "1. 도서 정보 추가", "2. 도서 정보 삭제", "3. 도서 정보 파일 저장" };

	public BookMarketController(BookStorage bookStorage, Cart cart, ConsoleView view) {

		this.view = view;
		this.mBookStorage = bookStorage;
		this.mCart = cart;
		mCustomer = new Customer();
		mAdmin = new Admin();
	}

	public void start() {

		view.displayWelcome();
		view.inputCustomerInfo(mCustomer);

		int menu;

		do {
			menu = view.selectMenuNo(menuList);

			switch (menu) {

			case 1 -> viewBookInfo();

			case 2 -> viewCart();

			case 3 -> addBook2Cart();

			case 4 -> deleteBookInCart();

			case 5 -> updateBookInCart();

			case 6 -> resetCart();

			case 7 -> order();

			case 8 -> adminMode();

			case 0 -> end();

			default -> view.showMessage(">> 잘못된 메뉴 번호입니다.");

			}
		} while (menu != 0);

	}

	private void adminMode() {

		if (!authenticateAdmin()) {
			view.showMessage("인증 실패");
			return;
		} else {
			int menu;
			do {
				menu = view.selectMenuNo(adminMenuList);

				switch (menu) {

				case 1 -> addBook2Storage();

				case 2 -> deleteBookInStorage();

				case 3 -> saveBookList2File();

				case 0 -> adminEnd();

				default -> view.showMessage(">> 잘못된 메뉴 번호입니다.");

				}
			} while (menu != 0);
			// 관리자 모드 진입 -> 도서 추가, 도서 삭제, 도서 정보 파일 저장
			// 관리자 모드일 때의 메뉴 출력
			// 메뉴 선택하면 해당 기능 실행

		}

	}

	private void addBook2Storage() {
		view.showMessage("새로운 책을 추가합니다");

		mBookStorage.addBook(view.inputString("책 제목 : "), view.inputString("저자 : "), view.inputString("출판사 : "), view.inputPrice("가격 : "));
		
	
	}

	private void deleteBookInStorage() {
		
		
		if (mBookStorage.isEmpty()) {
			view.showMessage("책 창고에 책이 없습니다.");
			return;
		}
		viewBookInfo();
			// 도서 ID 입력받기
		int bookId = view.selectBookId(mBookStorage);
			if (view.askConfirm(">> 해당 도서를 삭제하려면 \"yes\"를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem삭제
				mBookStorage.deleteItem(bookId);
				view.showMessage(">> 해당 도서를 삭제했습니다.");
			} else {
				view.showMessage(">> 해당 도서를 삭제하지 않았습니다.");
			}
		
	}

	private void saveBookList2File() {
		
		if(mBookStorage.isSaved()) {
			view.showMessage("저장할 변경사항이 없습니다.");
		} else {
			mBookStorage.saveBookList2File();
			view.showMessage("책을 저장했습니다.");
		}
	}

	private void adminEnd() {
		view.showMessage("관리자 모드가 종료되었습니다.");
	}

	private boolean authenticateAdmin() {
		// 관리자 인증 (id, password)
		view.showMessage("관리자 모드 진입을 위한 인증");

		String id = view.inputString("관리자 ID : ");
		String password = view.inputString("관리자 PW : ");

		return mAdmin.login(id, password);
	}

	private void end() {
		view.showMessage("Haejun's BookMarket을 종료합니다.");
	}

	private void order() {
		if (!mCart.isEmpty()) {

			addDeliveryInfo();

			displayOrderInfo();

			if (view.askConfirm(">> 주문하려면 \"yes\"를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 주문을 완료했습니다.");
			} else {
				view.showMessage(">> 주문을 취소했습니다.");
			}
		} else {
			view.displayCart(mCart);
		}
	}

	private void displayOrderInfo() {

		view.displayDeliveryInfo(mCart, mCustomer);
	}

	private void addDeliveryInfo() {
		view.inputDeliveryInfo(mCustomer);

	}

	private void updateBookInCart() {

		view.displayCart(mCart);

		if (!mCart.isEmpty()) {
			// 도서 ID 입력받기
			int bookId = view.selectBookId(mCart);
			// 수량 입력 받기
			int quantity = view.inputNumber(0, mBookStorage.getMaxQuantitiy());
			// 도서 ID에 해당하는 cartItem 가져옴
			mCart.updateQuantity(bookId, quantity);
			view.showMessage(">> 해당 도서의 수량을 변경하였습니다.");
		}
	}

	private void deleteBookInCart() {

		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력받기
			int bookId = view.selectBookId(mCart);
			if (view.askConfirm(">> 해당 도서를 삭제하려면 \"yes\"를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem삭제
				mCart.deleteItem(bookId);
				view.showMessage(">> 해당 도서를 삭제했습니다.");
			} else {
				view.showMessage(">> 해당 도서를 삭제하지 않았습니다.");
			}
		}

	}

	private void resetCart() {
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우시려면 \"yes\"를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			} else {
				view.showMessage(">> 장바구니를 비우지 않았습니다.");
			}
		}
	}

	private void addBook2Cart() {
		view.displayBookInfo(mBookStorage);

		mCart.addItem(mBookStorage.getBookId(view.selectBookId(mBookStorage)));
		view.showMessage(">> 장바구니에 도서를 추가하였습니다.");
	}

	private void viewCart() {
		view.displayCart(mCart);

	}

	private void viewBookInfo() {

		view.displayBookInfo(mBookStorage);

	}

}
