package com.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class ByRollNO extends HttpServlet {
	Connection con=null;
    PreparedStatement ps=null;
  ResultSet rs=null;
    int result=0;
	
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		
		String date,branch,sem,subject,rollno;
		date=request.getParameter("date");
		branch=request.getParameter("branch");
		sem=request.getParameter("sem");
		subject=request.getParameter("subject");
		rollno=request.getParameter("rollno");
		
		Conn c= new Conn();
		con=c.getCon();
		
		try {
			String qur="select * from attendance"
					+ " where date=? and branch=? and sem=? and subject=? and rollno=?";
			 ps=(PreparedStatement) con.prepareStatement(qur);
			 ps.setString(1, date);
			 ps.setString(2, branch);
			 ps.setString(3,sem);
			 ps.setString(4, subject);
			 ps.setString(5, rollno);
			 
			rs=ps.executeQuery();
				
				
				if(rs.next())
				{
					String name=rs.getString("studentname");
					request.setAttribute("name",name);
					request.setAttribute("status", "Present");
					RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
					rd.forward(request, response);
				}
				else
				{
					
					request.setAttribute("name","Not Available");
					request.setAttribute("status", "Absent");
					RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
					rd.forward(request, response);
				}
			
		}catch(Exception e)
		{
			out.println(e);
		}
	}

}
