package test.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InserTest {

	public static void main(String[] args) {
		boolean result = insert("맹자");

		if (result) {
			System.out.println("입력 성공");
		}
	}

	public static boolean insert(String name) {
		boolean result = false;

		Connection conn = null;
		Statement stat = null;
		try {

			// 1. JDBC Driver (Maria DB) 로딩
			Class.forName("org.postgresql.Driver");

			// 2. 연결하기
			String url = "jdbc:postgresql://192.168.1.46:5432/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("연결성공");

			stat = conn.createStatement();

			String sql = "insert into author values(nextval('seq_author'),'" + name + "')";

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
