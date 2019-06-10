package test.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {

	public static void main(String[] args) {
		boolean result = delete("6");

		if (result) {
			System.out.println("삭제 성공");
		}
	}

	public static boolean delete(String no) {
		boolean result = false;

		Connection conn = null;
		Statement stat = null;
		try {

			// 1. JDBC Driver (Maria DB) 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.42:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공");

			stat = conn.createStatement();

			String sql = "delete from department where no = " + no;

			int count = stat.executeUpdate(sql);

			result = (count == 1);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (stat != null) {
					stat.close();
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
