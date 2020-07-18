package com.mtw.publisher;

import java.sql.SQLException;
import java.util.List;

public class PublisherService {
	private PublisherDao pubDao=new PublisherDao();
	public List<Publisher> queryAll(){
		try {
			return pubDao.selectAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
