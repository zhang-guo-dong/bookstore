package com.mtw.book;

import java.sql.SQLException;
import java.util.List;

import com.mtw.common.PageInfo;
import com.mtw.common.Result;
import com.mtw.common.ResultCode;

public class BookService {
	private BookDao bookDao=new BookDao();
	public PageInfo<Book> selectByCondition(Book condition,String authorname,PageInfo<Book> page){
		try {
			return bookDao.selectByCondition(condition,authorname,page);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultCode checkISBN(String isbn) {
		try {
			int count = bookDao.selectCountByISBN(isbn);
			return count == 1 ? ResultCode.ERROR_ISBN_EXIST : ResultCode.SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultCode.ERROR_SYSTEM;
		}
	}
	
	public ResultCode addOne(Book book) {
		try {
			bookDao.insertOne(book);
			return ResultCode.SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultCode.ERROR_SYSTEM;
		}
	}
	public Result<Book> queryOne(String isbn)
	{
		Result<Book>result=new Result<>();
		try {
			Book book = bookDao.selectOneByISBN(isbn);
			if(book==null)
			{
				result.setCode(ResultCode.ERROR_DATA_NOTEEXIST); 
				return result;
			}
			result.setObj(book);
			result.setCode(ResultCode.SUCCESS);
			return result;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(ResultCode.ERROR_SYSTEM);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(ResultCode.ERROR_SYSTEM);
			return result;
		}
	}
	public ResultCode modOne(Book book) {
		try {
			bookDao.updateOne(book);
			return ResultCode.SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultCode.ERROR_SYSTEM;
		}
	}
	public ResultCode delOne(String isbn) {
		try {
			bookDao.delOne(isbn);
			return ResultCode.SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultCode.ERROR_SYSTEM;
		}
	}
	public ResultCode delMany(String[] isbns) {
		try {
			bookDao.delMany(isbns);
			return ResultCode.SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultCode.ERROR_SYSTEM;
		}
	}
	public Result<Book> queryOneAndPublisherAuthor(String isbn)
	{
		Result<Book>result=new Result<>();
		try {
			Book book = bookDao.selectOneAndPublisherAuthorByISBN(isbn);
			System.out.println("1"+book);
			if(book==null)
			{
				result.setCode(ResultCode.ERROR_DATA_NOTEEXIST); 
				return result;
			}
			result.setObj(book);
			result.setCode(ResultCode.SUCCESS);
			return result;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("2");
			e.printStackTrace();
			result.setCode(ResultCode.ERROR_SYSTEM);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("3");
			result.setCode(ResultCode.ERROR_SYSTEM);
			return result;
		}
	}
} 
