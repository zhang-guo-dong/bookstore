package com.mtw.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtw.author.Author;
import com.mtw.common.PageInfo;
import com.mtw.publisher.Publisher;
import com.mtw.publisher.PublisherDao;
import com.mtw.publisher.PublisherService;

/**
 * Servlet implementation class QueryBooksController
 */
public class QueryBooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryBooksController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Publisher> publist=new PublisherService().queryAll();
		if(null==publist) {
			request.setAttribute("info", "系统异常");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		request.setAttribute("publist", publist);
		List<Book> bookList=new ArrayList<>();
		String isbn=request.getParameter("isbn");
		//点击查询按钮进入
		if(null!=isbn){
			String title=request.getParameter("title");
			Long pubid=Long.parseLong(request.getParameter("publisher"));
			String category=request.getParameter("category");
			String authorname=request.getParameter("authorname");
			String curPageStr=request.getParameter("curPage");
			int rowPerPage=Integer.parseInt(request.getParameter("rowPerPage"));
			int curPage=curPageStr==null||"".equals(curPageStr)?1:
				Integer.parseInt(curPageStr);
			String orderBy=request.getParameter("orderBy");
			String order=request.getParameter("order");
			
			Book condition=new Book();
			condition.setIsbn(isbn);
			condition.setTitle(title);
			condition.setCategory(category);

			Publisher pCondition=new Publisher();
			pCondition.setPubid(pubid);
			condition.setPublisher(pCondition);
			PageInfo<Book> page=new PageInfo<>();
			page.setRowPerPage(rowPerPage);
			page.setCurPage(curPage);
			page.setOrderBy(orderBy);
			page.setOrder(order);
			PageInfo<Book> pageResult=new BookService().selectByCondition(condition,authorname,page);
			bookList=pageResult.getResults();
			request.setAttribute("bookList", bookList);
			request.setAttribute("totalRow", pageResult.getTotalRow());
			request.setAttribute("totalPages", pageResult.getTotalPages());
			request.setAttribute("curPage", pageResult.getCurPage());
			request.setAttribute("con", condition);
			request.setAttribute("an", authorname);
			request.setAttribute("rowPerPage", rowPerPage);
			request.setAttribute("orderBy",orderBy);
			request.setAttribute("order",order);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/book/booklist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
