package com.mtw.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtw.common.Result;
import com.mtw.common.ResultCode;
import com.mtw.publisher.Publisher;
import com.mtw.publisher.PublisherService;

/**
 * Servlet implementation class QueryOneBookController
 */
public class QueryOneBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOneBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn=request.getParameter("isbn");
		Result<Book> result=new BookService().queryOneAndPublisherAuthor(isbn);
		System.out.println(result);
		if(result.getCode()==ResultCode.ERROR_DATA_NOTEEXIST)
		{
			request.setAttribute("info", "该书不存在可能被删除");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}else if(result.getCode()==ResultCode.ERROR_SYSTEM)
		{
			request.setAttribute("info", "操作失败，系统异常");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		else
		{
			Book book=result.getObj();
			request.setAttribute("book", book);
			request.getRequestDispatcher("/WEB-INF/jsp/book/bookdetail.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
