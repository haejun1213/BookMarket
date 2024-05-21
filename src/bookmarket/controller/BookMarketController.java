package bookmarket.controller;

import bookmarket.model.BookStorage;
import bookmarket.model.Cart;
import bookmarket.model.Customer;
import bookmarket.view.ConsoleView;

public class BookMarketController {

	ConsoleView view;
	BookStorage mBookStorage;
	Cart mCart;
	Customer mCustomer;

	String[] menuList = { "0. 종료", "1. 도서 정보 보기", "2. 장바구니 보기", "3. 장바구니에 도서 담기", "4. 장바구니 도서 삭제", "5. 장바구니 도서 수량 변경",
			"6. 장바구니 비우기", "7. 주문" };

	public BookMarketController(BookStorage bookStorage, Cart cart, ConsoleView view) {

		this.view = view;
		this.mBookStorage = bookStorage;
		this.mCart = cart;
		mCustomer = new Customer();
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

			case 0 -> end();

			default -> view.showMessage(">> 잘못된 메뉴 번호입니다.");

			}
		} while (menu != 0);

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
		view.displayCart(mCart);
		view.displayDeliveryInfo(mCustomer);
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
