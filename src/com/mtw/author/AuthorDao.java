package com.mtw.author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mtw.common.DBInfo;
import com.mtw.publisher.Publisher;

public class AuthorDao {
	public List<Author> selectAll() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			String sql = "select * from tbl_author ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<Author> list = new ArrayList<Author>();
			while (rs.next()) {
				list.add(new Author(rs.getString("authorid"),
						rs.getString("name")));
			}
			return list;
		} finally {
			if (conn != null) {
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
