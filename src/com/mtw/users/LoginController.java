package com.mtw.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtw.common.ResultCode;

/**
 * Servlet implementation class LoginControoller
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(1);
		System.out.println(username + password);
		ResultCode code = new UserService().checkLogin(new User(username, password));
		System.out.println(2);
		System.out.println(code);
		switch (code) {
		case SUCCESS:
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
			break;
		case ERROR_USER:
			request.setAttribute("info", "用户名密码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case ERROR_SYSTEM:
			request.setAttribute("info", "系统错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
