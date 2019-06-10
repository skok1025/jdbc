package bookshop.test;

import bookshop.dao.BookDAO;
import bookshop.vo.BookVO;

public class BookDAOTest {
	
	public static void main(String[] args) {
		insert("아리랑",2l);
		
		
	}

	public static void insert(String title,Long authorNo) {
		BookVO vo = new BookVO();
		vo.setTitle(title);
		vo.setAuthorNo(authorNo);
		
		new BookDAO().insert(vo);
	}
	
}
