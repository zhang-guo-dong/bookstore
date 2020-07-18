package com.mtw.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String realPath =config.getServletContext().getRealPath("");
		System.out.println(realPath);
		File xmlFile=new File(realPath+"WEB-INF"+File.separator+"db.xml");
		SAXReader reader=new SAXReader();
		try {
			Document xmlDoc=reader.read(xmlFile);
			//XPath Óï·¨
			Element host=(Element)xmlDoc.selectSingleNode("//host");
			DBInfo.host=host.getText();
			System.out.println(DBInfo.host);
			Element port=(Element)xmlDoc.selectSingleNode("//port");
			DBInfo.port=port.getText();
			Element sid=(Element)xmlDoc.selectSingleNode("//sid");
			DBInfo.sid=sid.getText();
			Element username=(Element)xmlDoc.selectSingleNode("//username");
			DBInfo.username=username.getText();
			Element password=(Element)xmlDoc.selectSingleNode("//password");
			DBInfo.password=password.getText();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
