package com.mtw.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtw.author.Author;
import com.mtw.author.AuthorService;
import com.mtw.publisher.Publisher;
import com.mtw.publisher.PublisherService;

/**
 * Servlet implementation class ForwardAddBookController
 */
public class ForwardAddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardAddBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PublisherService ps = new PublisherService();
		List<Publisher> list = ps.queryAll();
		AuthorService as=new AuthorService();
		List<Author> authorList=as.queryAll();
		request.setAttribute("publist", list);
		request.setAttribute("authorlist", authorList);
		request.getRequestDispatcher("/WEB-INF/jsp/book/addbook.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
