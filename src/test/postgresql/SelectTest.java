package test.postgresql;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {

			// 1. JDBC Driver (Maria DB) 로딩
			Class.forName("org.postgresql.Driver");

			// 2. 연결하기
			String url = "jdbc:postgresql://192.168.1.46:5432/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공");

			// 3. statement 객체 생성
			stat = conn.createStatement();

			// sql 문 실행
			String sql = "select no, name from author order by no desc";
			rs = stat.executeQuery(sql);

			// 결과가져오기

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				System.out.println(no + ":" + name);
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
	}

}
