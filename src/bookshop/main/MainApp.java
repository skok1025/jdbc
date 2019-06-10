package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDAO;
import bookshop.vo.BookVO;

public class MainApp {

	public static void main(String[] args) {
		
		displayBookInfo();
	
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		Long no = scanner.nextLong();
		
		
		
		scanner.close();
		rent(no);
		displayBookInfo();
	}
	
	private static void rent(Long bookNo) {

		new BookDAO().update(bookNo,"대여중");
	}

	private static void displayBookInfo() {
		List<BookVO> list = new BookDAO().getList();
		
		for(BookVO vo : list) {
			System.out.printf("책 제목:%s, 작가%s, 대여 유무:%s\n",vo.getTitle(),vo.getAuthorName(),vo.getStatus());
		}
	}	
}