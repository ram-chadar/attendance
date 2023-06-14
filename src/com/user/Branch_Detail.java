package com.user;
import com.dbcon.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Branch_Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	
	
    PreparedStatement ps=null;
    String bn,branchname,subname,subcode,sem;
    int result=0;
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Conn c= new Conn();
		con=c.getCon();
		PrintWriter out=response.getWriter();	
		bn=request.getParameter("bn");
		branchname=request.getParameter("branchname");
		subname=request.getParameter("subname");
		subcode=request.getParameter("subcode");
		sem=request.getParameter("sem");
		
		

		String button = request.getParameter("action");
		if (button.equals("Add")) {
		try
		{
			
			
			ps=con.prepareStatement("insert into branchdetail values(?,?,?,?,?)");
			ps.setString(1, bn);
			ps.setString(2, branchname);
			ps.setString(3, subname);
			ps.setString(4, sem);
			ps.setString(5, subcode);
			
			
			result=ps.executeUpdate();
			if(result>0)
			{
		
				request.setAttribute("msg", "Branch & Subject Added Successfully");
				
				RequestDispatcher rd=request.getRequestDispatcher("/branch_detail.jsp");
				rd.forward(request, response);
			}
			else
			{
				
				request.setAttribute("msg", "Something Wrong To Add Subject");
				RequestDispatcher rd=request.getRequestDispatcher("/branch_detail.jsp");
				rd.forward(request, response);
			}
			
			
		}catch(Exception e)
		  {
			
			out.print(e);
		   }
		}
		else if(button.equals("Delete"))
		{
			try
			{
				
				ps=con.prepareStatement("delete from branchdetail where branch=? and sub=? and sem=?");
				ps.setString(1, bn);
				ps.setString(2, subname);
				ps.setString(3, sem);

				result=ps.executeUpdate();
				if(result>0)
				{
					request.setAttribute("msg", "Subject Deleted Successfully");
					RequestDispatcher rd=request.getRequestDispatcher("/branch_detail.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Something Wrong For Deleting");
					RequestDispatcher rd=request.getRequestDispatcher("/branch_detail.jsp");
					rd.forward(request, response);
				}
			}
			catch(Exception e1)
			{
				out.print(e1);
			}
		}
		
		
	}

}
