package com.mtw.book;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtw.author.Author;
import com.mtw.common.ResultCode;

/**
 * Servlet implementation class ModBookController
 */
public class ModBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String dateStr = request.getParameter("pubdate");
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			Date pubdate = f.parse(dateStr);
			Long pubid = Long.valueOf(request.getParameter("pubid"));
			Float cost = "".equals(request.getParameter("cost").trim())?null:
				Float.valueOf(request.getParameter("cost"));
			Float retail = "".equals(request.getParameter("retail").trim())?null:
				Float.valueOf(request.getParameter("retail"));
			String category = request.getParameter("category");
			
			String[] authorids=request.getParameterValues("authorinfo");
			List<Author> authors =new ArrayList<>();
			for (String authorid : authorids) {
				authors.add(new Author(authorid,null));
			}
			Book book = new Book(isbn, title, pubdate, pubid, cost, retail, category, null);
			book.setAuthors(authors);
			
			ResultCode code = new BookService().modOne(book);
			if(code == ResultCode.SUCCESS) {
				request.setAttribute("info", "修改成功!");
				request.getRequestDispatcher("/success.jsp").forward(request, response);
			}else {
				request.setAttribute("info", "修改失败,系统异常!");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
