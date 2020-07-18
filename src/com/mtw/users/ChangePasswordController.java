package com.mtw.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtw.common.ResultCode;

/**
 * Servlet implementation class ChangePasswordController
 */
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String username =(String)session.getAttribute("username");
		String oldPwd=request.getParameter("old");
		UserService userService=new UserService();
		User user=new User(username,oldPwd);
		ResultCode code=userService.checkLogin(user);
		switch (code) {
		case SUCCESS:
			String newPwd=request.getParameter("new1");
			user=new User(username, newPwd);
			code=userService.changePassword(user);
			if(code==ResultCode.SUCCESS) {
				session.removeAttribute("username");
				String path=request.getContextPath();
				response.sendRedirect(path+"/login.jsp");
			}else {
				request.setAttribute("info", "系统内部异常");
				request.getRequestDispatcher("/WEB-INF/jsp/user/changepassword.jsp").forward(request, response);
			}
			break;
		case ERROR_USER:
			request.setAttribute("info", "旧密码不正确");
			request.getRequestDispatcher("/WEB-INF/jsp/user/changepassword.jsp").forward(request, response);
			break;
		case ERROR_SYSTEM:
			request.setAttribute("info", "系统内部异常");
			request.getRequestDispatcher("/WEB-INF/jsp/user/changepassword.jsp").forward(request, response);
			break;
		}
		String password=request.getParameter("new1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
