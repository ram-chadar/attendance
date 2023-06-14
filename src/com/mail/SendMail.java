package com.mail;

import java.io.IOException;
import com.mail.EmailUtility;
import com.user.StudentReg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMail extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentReg s=new StudentReg();
		

		ServletContext context = getServletContext();
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		
		
		String recipient=s.email;
	
		String subject="Registration Successfull";
		String content="Hi..."+s.studentname+"..Your Registration Has Been Successfully Done.. "
				+ "Your Roll No Is.."+s.rollno+  
				 "..EnrollMent No Is.."+s.enrno+"";
		
		
		try {

			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
			RequestDispatcher rd = request.getRequestDispatcher("student_detail.jsp");
			request.setAttribute("resultMessage", "Email Successfully Send");
			rd.forward(request, response);
		} catch (Exception ex) {
			
			RequestDispatcher rd = request.getRequestDispatcher("student_detail.jsp");
			request.setAttribute("resultMessage", ex.getMessage());
			rd.forward(request, response);
		}

		
	}
}
