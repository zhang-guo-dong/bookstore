package com.mtw.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mtw.author.Author;
import com.mtw.common.DBInfo;
import com.mtw.common.PageInfo;
import com.mtw.publisher.Publisher;
import com.mtw.users.User;

public class BookDao {
	/**
	 * 条件组合式查询
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public PageInfo <Book> selectByCondition(Book condition,String authorname,PageInfo<Book> page) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			String sqlTotalRow="select count(distinct b.isbn) from tbl_books b join TBL_BOOKAUTHOR ba on b.isbn=ba.isbn " 
			+ " join tbl_author au on ba.AUTHORID = au.AUTHORID join TBL_PUBLISHER p on b.PUBID=p.PUBID where 1=1";
			String sql = "select b.*,p.name from tbl_books b left "
					+ "join tbl_publisher p on b.pubid = p.pubid where 1=1";
			List<Object> conditionList = new ArrayList<>();
			List<Object> conditionListTotalRow = new ArrayList<>();
			if (condition.getIsbn() != null && !"".equals(condition.getIsbn())) {
				sql += " and b.isbn=?";
				sqlTotalRow += " and b.isbn=?";
				conditionList.add(condition.getIsbn());
				 conditionListTotalRow.add(condition.getIsbn());
			}
			if (condition.getTitle() != null && !"".equals(condition.getTitle())) {
				sql += " and b.title like '%" + condition.getTitle() + "%'";
				sqlTotalRow += " and b.title like '%" + condition.getTitle() + "%'";
			}
			if (condition.getPublisher().getPubid() != null && -1 != condition.getPublisher().getPubid().longValue()) {
				sql += " and b.pubid=?";
				sqlTotalRow += " and b.isbn=?";
				conditionList.add(condition.getPublisher().getPubid());
				 conditionListTotalRow.add(condition.getPublisher().getPubid());
			}
			if (condition.getCategory() != null && !"".equals(condition.getCategory())) {
				sql += " and b.category=?";
				sqlTotalRow += " and b.isbn=?";
				conditionList.add(condition.getCategory());
				 conditionListTotalRow.add(condition.getCategory());
			}
			if(null!=authorname&&!"".equals(authorname.trim())) {
				sqlTotalRow +=" and au.name like'%"+authorname+"%'";
				}
			
			if(null!=page.getOrderBy() && !"".equals(page.getOrderBy())) {
				sql += " order by " + page.getOrderBy() + " " + page.getOrder();
			}
			
			int start=page.getStart();
			int end=page.getEnd();
			sql="select *from (select a.*,rownum rn from("+sql+
					")a where rownum<="+end+") where rn>="+start;
			//) where rn>=?
			
			System.out.println("sql="+sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmtTotalRow = conn.prepareStatement(sqlTotalRow);
			System.out.println("sqlTotalRow="+sqlTotalRow);
			int i=0;
			for (; i < conditionList.size(); i++) {
				pstmt.setObject(i + 1, conditionList.get(i));
			}
			for (int j = 0; j < conditionListTotalRow.size(); j++) {
				pstmtTotalRow.setObject(j + 1, conditionListTotalRow.get(j));

			}
			
			ResultSet rs = pstmt.executeQuery();
			ResultSet rsTotalRow = pstmtTotalRow.executeQuery();
			rsTotalRow.next();
			int totalRow=rsTotalRow.getInt(1);
			System.out.println(totalRow);
			page.setTotalRow(totalRow);
			
			List<Book> list = new ArrayList<>();
			String sql2 = "select count(*) from tbl_author a join tbl_bookauthor ba "
					+ "on a.authorid=ba.authorid where ba.isbn=? ";
					if(null!=authorname&&!"".equals(authorname.trim())) {
					sql2 +="and a.name like'%"+authorname+"%'";
					}
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			String sql3 = "select a.* from tbl_author a join tbl_bookauthor ba "
					+ "on a.authorid=ba.authorid where ba.isbn=? ";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			while (rs.next()) {
				pstmt2.setString(1, rs.getString("isbn"));
				ResultSet rs2 = pstmt2.executeQuery();
				List<Author> authors = new ArrayList<>();
				rs2.next();
				if(rs2.getInt(1)>0)
				{
					pstmt3.setString(1, rs.getString("isbn"));
					ResultSet rs3=pstmt3.executeQuery();
					while(rs3.next())
					{
						authors.add(new Author(rs3.getString("authorid"),rs3.getString("name")));
					}
					Book book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getDate("pubdate"),
							rs.getLong("pubid"), rs.getFloat("cost"), rs.getFloat("retail"), rs.getString("category"),
							new Publisher(rs.getLong("pubid"), rs.getString("name"), null, null));
					book.setAuthors(authors);
					list.add(book);
				}
			}
			page.setResults(list);
			return page;
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

	public int selectCountByISBN(String isbn) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			String sql = "select count(*) from tbl_books " + "where isbn = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
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

	public void insertOne(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into tbl_books values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setDate(3, new java.sql.Date(book.getPubdate().getTime()));
			pstmt.setLong(4, book.getPubid());
			pstmt.setFloat(5, book.getCost());
			pstmt.setFloat(6, book.getRetail());
			pstmt.setString(7, book.getCategory());
			pstmt.executeUpdate();

			String sql2 = "insert into tbl_bookauthor values(?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			List<Author> authors = book.getAuthors();
			for (Author au : authors) {
				pstmt2.setString(1, book.getIsbn());
				pstmt2.setString(2, au.getAuthorid());
				pstmt2.executeUpdate();
			}
			conn.commit();
		} finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public Book selectOneByISBN(String isbn) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			String sql = "select * from tbl_books where isbn=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String sql2 = "select b.*,ba.authorid from tbl_books b join tbl_bookauthor ba "
						+ "on b.ISBN = ba.ISBN where b.isbn = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, rs.getString("isbn"));
				ResultSet rs2 = pstmt2.executeQuery();
				List<Author> authorList = new ArrayList<>();
				while(rs2.next()) {
					authorList.add(new Author(rs2.getString("authorid"),null));
				}
				Book book =new Book(rs.getString("isbn"), rs.getString("title"), rs.getDate("pubdate"), rs.getLong("pubid"),
						rs.getFloat("cost"), rs.getFloat("retail"), rs.getString("category"), null);
				book.setAuthors(authorList);
				return book;
			}
			return null;
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

	public Book selectOneAndPublisherAuthorByISBN(String isbn) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			String sql = "select * from tbl_books b join tbl_publisher p " 
					+ " on b.pubid=p.pubid where isbn=? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				String sql2 = "select b.*,au.* from tbl_books b join tbl_bookauthor ba "
						+ "on b.ISBN = ba.ISBN join tbl_author au on ba.authorid = au.authorid where b.isbn = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, rs.getString("isbn"));
				ResultSet rs2 = pstmt2.executeQuery();
				List<Author> authorList = new ArrayList<>();
				while(rs2.next()) {
					authorList.add(new Author(rs2.getString("authorid"),rs2.getString("name")));
				}
				Book book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getDate("pubdate"), rs.getLong("pubid"),
						rs.getFloat("cost"), rs.getFloat("retail"), rs.getString("category"),
						new Publisher(rs.getLong("pubid"), rs.getString("name"), null, null));
				book.setAuthors(authorList);
				return book;
			}
			return null;
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

	public void updateOne(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			conn.setAutoCommit(false);
			String sql = "update tbl_books set title=?," +
			"pubdate=?,pubid=?,cost=?,retail=?,category=? where isbn=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setDate(2, new java.sql.Date(book.getPubdate().getTime()));
			pstmt.setLong(3, book.getPubid());
			pstmt.setFloat(4, book.getCost());
			pstmt.setFloat(5, book.getRetail());
			pstmt.setString(6, book.getCategory());
			pstmt.setString(7, book.getIsbn());
			pstmt.executeUpdate();
			
			String sql2 = "delete tbl_bookauthor where isbn=?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, book.getIsbn());
			pstmt2.executeUpdate();
			
			String sql3 = "insert into tbl_bookauthor values(?,?)";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			List<Author> authors = book.getAuthors();
			for (Author au : authors) {
				pstmt3.setString(1, book.getIsbn());
				pstmt3.setString(2, au.getAuthorid());
				pstmt3.executeUpdate();
			}
			
			conn.commit();
		} finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void delOne(String isbn) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			String sql2 = "delete tbl_bookauthor where isbn=?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, isbn);
			pstmt2.executeUpdate();
			String sql = "delete tbl_books where isbn=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.executeUpdate();
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

	public void delMany(String[] isbns) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBInfo.getConnection();
			conn.setAutoCommit(false);
			String sql2 = "delete tbl_bookauthor where isbn=?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);	
			String sql = "delete tbl_books where isbn=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (String isbn : isbns) {
				pstmt2.setString(1, isbn);
				pstmt2.executeUpdate();
				pstmt.setString(1, isbn);
				pstmt.executeUpdate();
			}
			conn.commit();
		} finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}