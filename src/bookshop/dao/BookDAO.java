package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVO;
import util.DBUtil;

public class BookDAO {

	public List<BookVO> get(BookVO vo) {
		return null;
	}
	
	public List<BookVO> get(Long no) {
		return null;
	}
	
	
	public Boolean update(BookVO vo) {
		// 책 정보 변경
		return false;
	}

	public Boolean update(Long no, String status) {
		// 책 정보 변경
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "update book set status = ? where no = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setLong(2, no);
			pstat.setString(1, status);

			int count = pstat.executeUpdate();

			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean insert(BookVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into book(no,title,author_no) values(null,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getTitle());
			pstat.setLong(2, vo.getAuthorNo());

			int count = pstat.executeUpdate();

			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BookVO> getList() {

		List<BookVO> result = new ArrayList<BookVO>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select * from book";
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setStatus(rs.getString(3));
				vo.setAuthorNo(rs.getLong(4));
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

}
