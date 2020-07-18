package com.mtw.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtw.common.DBInfo;

public class UserDao {
public int selectCountByUsernameAndPassword(User user) throws ClassNotFoundException, SQLException
{	
	Connection conn=null;
	try {
		conn=DBInfo.getConnection();
		String sql="select count(*) from tbl_userinfo "
				+"where username = ? and password = ?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}finally {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

	public void updatePassword(User user) throws ClassNotFoundException, SQLException {
		Connection conn=null;
		try {
			conn=DBInfo.getConnection();
			String sql="update tbl_userinfo set password=?where username=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
			pstmt.executeUpdate();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
