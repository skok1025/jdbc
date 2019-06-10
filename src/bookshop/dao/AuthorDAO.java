package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVO;

public class AuthorDAO {

	public Boolean insert(AuthorVO vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstat= null;
		try {

			conn = getConnection();
			
			String sql = "insert into author values(null,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getName());
			
			int count = pstat.executeUpdate();
			
			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("error: "+e);
		} finally {
			try {
			
				if(pstat != null) {
					pstat.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	public List<AuthorVO> getList() {

		List<AuthorVO> result = new ArrayList<AuthorVO>();

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.42:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공");

			String sql = "select no, name from department";
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();


			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				AuthorVO vo = new AuthorVO();
				vo.setNo(no);
				vo.setName(name);
				
				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
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
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.42:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : "+e);
		}	
		return conn;

			
	}
	

}
