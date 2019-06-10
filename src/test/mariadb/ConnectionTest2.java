package test.mariadb;

import java.sql.Connection;
import java.sql.SQLException;

import util.DBUtil;

public class ConnectionTest2 {
	public static void main(String[] args) {
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
		}  catch (Exception e) {
			System.out.println("error: " + e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
