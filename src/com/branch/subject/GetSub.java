package com.branch.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class GetSub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
   

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Conn c= new Conn();
		con=c.getCon();
		String branch,sem,month,from,to,year;
		branch=request.getParameter("branch");
		sem=request.getParameter("sem");
		month=request.getParameter("month");
		from=request.getParameter("smonth");
		to=request.getParameter("endmonth");
		year=request.getParameter("year");
	/*	System.out.println(from);
		System.out.println(to);
		System.out.println(sem);*/
		
		String action=request.getParameter("action");
		
		
		
		PrintWriter out = response.getWriter();
		//Establish connection to MySQL database
		//String connectionURL = "jdbc:mysql://node38208-digitalstud.cloud.cms500.com/samsdatabase";
		
		ResultSet rs;
		
		response.setContentType("text/html");
		List dataList=new ArrayList(); 
		List dataList1=new ArrayList(); 
		List dataList2=new ArrayList(); 
		
			try {
			
			String sql = "select  distinct branch from branchdetail";
			PreparedStatement ps = con.prepareStatement(sql);
			rs=ps.executeQuery ();
			
			while (rs.next ()){
				//Add records into data list
				dataList.add(rs.getString("branch"));
			}
			
			
			String sql1 = "select distinct sub from branchdetail where branch=? and sem=?" ;
			ps= con.prepareStatement(sql1);
			ps.setString(1, branch);
			ps.setString(2, sem);
			
			rs=ps.executeQuery ();
			
			while (rs.next ()){
				//Add records into data list
				dataList1.add(rs.getString("sub"));
			}
			
			
			
			String sql2 = "select  rollno from studentregistration where branch=? and sem=?" ;
			ps= con.prepareStatement(sql2);
			ps.setString(1, branch);
			ps.setString(2, sem);
			
			rs=ps.executeQuery ();
			
			while (rs.next ()){
				//Add records into data list
				dataList2.add(rs.getString("rollno"));
			
			}
			
			}catch(Exception e){
			out.println(e);
			}
			request.setAttribute("data",dataList);
			request.setAttribute("subject", dataList1);
			request.setAttribute("attrollno", dataList2);
			request.setAttribute("sem",sem);
			request.setAttribute("year", year);
			request.setAttribute("branch", branch);
			
			request.setAttribute("month", month);
			
			request.setAttribute("smonth",from);
			request.setAttribute("endmonth", to);
			
			//Disptching request
			
			
			
			 if(action.equals("Get"))
			   {
				   RequestDispatcher rd1 = request.getRequestDispatcher("attendance.jsp");
				   if (rd1 != null)
				  {
					rd1.forward(request, response);
				  } 
			   }
			 
			 else if(action.equals("Get Subject"))
			   {
				   RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
				   if (rd2 != null)
				  {
					rd2.forward(request, response);
				  } 
			   }
			 
			 else if(action.equals("Get Subjects"))
			   {
				
				   RequestDispatcher rd3 = request.getRequestDispatcher("monthly.jsp");
				   if (rd3 != null)
				  {
					rd3.forward(request, response);
				  } 
			   }
			 
			 else if(action.equals("Get_Subjects"))
			   {
				   RequestDispatcher rd4 = request.getRequestDispatcher("sem.jsp");
				   if (rd4 != null)
				  {
					rd4.forward(request, response);
				  } 
			   }
	}

}
