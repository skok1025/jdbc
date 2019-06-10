package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

	public List<EmployeeVO> getList(String keyword) {

		List<EmployeeVO> result = new ArrayList<EmployeeVO>();
		
		Connection conn = null;
		//Statement stat = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			// 1. JDBC Driver (Maria DB) 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.42:3307/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");

			System.out.println("연결성공");

			// 3. statement 객체 생성
			//stat = conn.createStatement();
			// sql 문 실행
			//String sql = "select emp_no,first_name,last_name,hire_date from employees where first_name like '%"+keyword+"%' or last_name like '%"+keyword+"%'";
			//rs = stat.executeQuery(sql);
			String sql = "select emp_no,first_name,last_name,hire_date from employees where first_name like ? or last_name like ?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, "%"+keyword+"%");
			pstat.setString(2, "%"+keyword+"%");
			
			rs = pstat.executeQuery();

			// 결과가져오기

			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVO vo = new EmployeeVO();
				vo.setNo(no);
				vo.setFirst_name(firstName);
				vo.setLast_name(lastName);
				vo.setHire_date(hireDate);
				
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
//				if (stat != null) {
//					stat.close();
//				}
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
