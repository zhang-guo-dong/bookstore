package com.mtw.author;

import java.sql.SQLException;
import java.util.List;

import com.mtw.publisher.Publisher;

public class AuthorService {
	private AuthorDao authorDao=new AuthorDao();
	public List<Author> queryAll(){
		try {
			return authorDao.selectAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
