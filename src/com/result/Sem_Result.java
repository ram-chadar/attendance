package com.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class Sem_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	float status;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String year,smonth,emonth, branch,sem,subject,rollno;
		year=request.getParameter("year");
		smonth=request.getParameter("smonth");
		emonth=request.getParameter("endmonth");
		branch=request.getParameter("branch");
		sem=request.getParameter("sem");
		subject=request.getParameter("subject");
		rollno=request.getParameter("rollno");
		
		
	
		
		PrintWriter out = response.getWriter();
		Connection con=null;
		Conn c= new Conn();
		con=c.getCon();
		PreparedStatement ps =null; 
	
		ResultSet rs;
		
		response.setContentType("text/html");
		
		List dataList1=new ArrayList(); 
		List dataList2=new ArrayList();
		
		if(year.equals("")||branch.equals("")||sem.equals("")||rollno.equals("")||subject.equals("")||smonth.equals("")||emonth.equals(""))
			{
			request.setAttribute("msg", "Plz Fill All Field");
			 RequestDispatcher rd2 = request.getRequestDispatcher("sem.jsp");
			   if (rd2 != null)
			  {
				rd2.forward(request, response);
			  } 
			}
			try {
				
			String sql1="select DISTINCT date from attendance where branch=? and sem=? and subject=? and rollno=? and month between ? and ? and year=?";
			ps= con.prepareStatement(sql1);
			ps.setString(1, branch);
			ps.setString(2, sem);
			ps.setString(3, subject);
			ps.setString(4, rollno);
			ps.setString(5, smonth);
			ps.setString(6, emonth);
			ps.setString(7, year);
			
			rs=ps.executeQuery ();
			
			while (rs.next ()){
				//Add records into data list
				dataList1.add(rs.getString("date"));
				
			}
			
			
			String sql2="select DISTINCT date from attendance where branch=? and sem=? and subject=? and  month between ? and ? and year=?";
			ps= con.prepareStatement(sql2);
			ps.setString(1, branch);
			ps.setString(2, sem);
			ps.setString(3, subject);
			ps.setString(4, smonth);
			ps.setString(5, emonth);
			ps.setString(6, year);
			
			rs=ps.executeQuery ();
			
			while (rs.next ()){
				//Add records into data list
				dataList2.add(rs.getString("date"));
				
			}
			
			}catch(Exception e){
			out.println(e);
			}
			
			if(dataList1.size()>0) {
			status=dataList1.size()*100/dataList2.size();
			}
			request.setAttribute("attenddatesem", dataList1);
			request.setAttribute("totaldatesem", dataList2);
			request.setAttribute("totlec",dataList2.size());
			request.setAttribute("attlec", dataList1.size());
			request.setAttribute("status", status);
			//Disptching request
			 RequestDispatcher rd2 = request.getRequestDispatcher("sem.jsp");
				   if (rd2 != null)
				  {
					rd2.forward(request, response);
				  } 
			  
			 
			
	}

}
