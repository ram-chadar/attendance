package com.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String type = (String) session.getAttribute("usertype");

		 if (type.equals("Admin")) {
		
			response.sendRedirect("user_detail.jsp");
		} 
		
		else 
		{
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('YOU ARE NOT AUTHORIZED USER LOGIN AS A ADMIN');");
			out.println("</script>");
		}
	}

}
