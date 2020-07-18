package com.mtw.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtw.common.ResultCode;

/**
 * Servlet implementation class CheckISBNJSONServlet
 */
public class CheckISBNJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckISBNJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		ResultCode code = new BookService().checkISBN(
				request.getParameter("isbn"));
		if(code == ResultCode.SUCCESS) {
			response.getWriter().print("{'code':'ok','info':'�ɹ�'}");
		}else if(code == ResultCode.ERROR_SYSTEM) {
			response.getWriter().print("{'code':'error','info':'ϵͳ�쳣'}");
		}else {
			response.getWriter().print("{'code':'error','info':'��ISBN�ѱ�ռ��'}");
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
