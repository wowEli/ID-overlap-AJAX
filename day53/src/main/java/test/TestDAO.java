package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import test.JDBCUtil;

public class TestDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final private String sql="SELECT * FROM TEST WHERE MID = ?";
	
	public int check(TestVO vo) { // 상황에 따라 String , boolean 을 쓰기도 함
		// 1 , 0 , -1
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				return 0; // 아이디가 중복되면 0 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return 1; // 아이디가 중복이 아니면 1
	}
	
}
