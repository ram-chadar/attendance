package com.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dbcon.Conn;
import com.mail.SendMail;

public class StudentReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 Connection con=null;
	    PreparedStatement ps=null;
	  ResultSet rs=null;
	    int result=0;
	String addate,branch,year,sem;
	static public String rollno;
	static public String studentname;
	static public String enrno;
	static public String email;
	
	String gender;
	String dob;
	String fname;
	String fmono;
	String mname;
	String mmono;
	String smono;
	String address;
	
	//Part part;
    public StudentReg() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Conn c= new Conn();
		con=c.getCon();
		PrintWriter out=response.getWriter();
		addate=request.getParameter("attDate");
		enrno=request.getParameter("enrollmentno");
		branch=request.getParameter("branch");
		year=request.getParameter("year");
		sem=request.getParameter("sem");
		rollno=request.getParameter("rollno");
		studentname=request.getParameter("studentname");
		gender=request.getParameter("gender");
		dob=request.getParameter("dob");
		fname=request.getParameter("fname");
		fmono=request.getParameter("fmono");
		mname=request.getParameter("mname");
		mmono=request.getParameter("mmono");
		smono=request.getParameter("smono");
		address=request.getParameter("address");
		email=request.getParameter("email");
		String filename = request.getParameter("file1");  //image is a image textfield name
		System.out.println(filename);
		
		String button = request.getParameter("action");
		if (button.equals("Save")) {
			if(addate.equals("")||enrno.equals("")||rollno.equals("")||studentname.equals("")||email.equals(""))
			{
				request.setAttribute("msg", "Required Detail Not Filled Correctly");
				RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
				rd.forward(request, response);
			}
		try
		{
			  
		    ps=con.prepareStatement("insert into studentregistration values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    /*InputStream is=part.getInputStream();*/
		    ps.setString(1, addate);
			ps.setString(2, rollno);
			ps.setString(3, enrno);
			ps.setString(4, studentname);
			ps.setString(5, branch);
			ps.setString(6, dob);
			ps.setString(7, year);
			ps.setString(8, gender );
			ps.setString(9, sem);
			ps.setString(10, fname);
		
			ps.setString(11, mname);
			ps.setString(12, fmono);
			ps.setString(13, mmono);
			ps.setString(14, smono);
			ps.setString(15, address);
			ps.setString(16, email);
			ps.setString(17, filename);
			
			
			result=ps.executeUpdate();
			if(result>0)
			{
				
				RequestDispatcher rd=request.getRequestDispatcher("/studentmail");
				rd.forward(request, response);
				
			}
			else
			{
				request.setAttribute("msg", "Something Wrong");
				RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
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
				  if(rollno.equals(""))
				  {
					  request.setAttribute("msg", "Check Roll NO");
						RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
						rd.forward(request, response);
				  }
				ps=con.prepareStatement("delete from studentregistration where rollno=?");
				ps.setString(1, rollno);
				result=ps.executeUpdate();
				if(result>0)
				{
					request.setAttribute("msg", "Student Record Deleted Successfully");
					RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("msg", "Something Wrong");
					RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
					rd.forward(request, response);
				}
			}
			catch(Exception e1)
			{
				out.print(e1);
			}
		}
		
		else if(button.equals("Update"))
		{
			try
			{
				 if(rollno.equals(""))
				  {
					  request.setAttribute("msg", "Check Roll NO");
						RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
						rd.forward(request, response);
				  }
					ps=con.prepareStatement(("update studentregistration set admisiondate=?,enrollmentno=?,studentname=?,branch=?,dob=?,year=?,gender=?,sem=?,fathername=?,mothername=?,fathermono=?,mothermono=?,studentmono=?,address=?,email=?,image=? where rollno=?"));
					ps.setString(1,addate);
					ps.setString(2,enrno);
					ps.setString(3,studentname);
					ps.setString(4,branch);
					ps.setString(5,dob);
					ps.setString(6,year);
					ps.setString(7,gender);
					ps.setString(8,sem);
					ps.setString(9,fname);
					ps.setString(10,mname);
					ps.setString(11,fmono);
					ps.setString(12,mmono);
					ps.setString(13,smono);
					ps.setString(14,address);
					ps.setString(15,email);
					ps.setString(16,filename);
					ps.setString(17,rollno);
					
					int i=ps.executeUpdate();
					
					if(i>0) 
						{
						request.setAttribute("msg", "Student Update Successfully");
							RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
					          rd.forward(request, response);
					    }
					
				
				
				else
				{
					request.setAttribute("msg", "Something Wrong");
					RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
			          rd.forward(request, response);
				}
				  
			}
			catch(Exception e)
			{
				out.println(e);
				
			}
			
		}
		else
		{
			try {
				 if(rollno.equals(""))
				  {
					  request.setAttribute("msg", "Check Roll NO");
						RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
						rd.forward(request, response);
				  }
				ps=con.prepareStatement("select * from studentregistration where rollno=?");
				ps.setString(1, rollno);
				rs=ps.executeQuery();
				if(rs.next())
				{
					
				//	System.out.println(adate);
					request.setAttribute("admisiondate",rs.getString("admisiondate"));
					request.setAttribute("enrno",rs.getString("enrollmentno"));
					request.setAttribute("rollno",rs.getString("rollno"));
					request.setAttribute("sname",rs.getString("studentname"));
					request.setAttribute("branch",rs.getString("branch"));
					request.setAttribute("dob",rs.getString("dob"));
					request.setAttribute("year",rs.getString("year"));
					request.setAttribute("gender",rs.getString("gender"));
					request.setAttribute("sem",rs.getString("sem"));
					request.setAttribute("fname",rs.getString("fathername"));
					request.setAttribute("mname",rs.getString("mothername"));
					request.setAttribute("fmono",rs.getString("fathermono"));
					request.setAttribute("mmono",rs.getString("mothermono"));
					request.setAttribute("smono",rs.getString("studentmono"));
					request.setAttribute("address",rs.getString("address"));
					request.setAttribute("email",rs.getString("email"));
					//request.setAttribute("image",rs.getString("image"));
					RequestDispatcher rd=request.getRequestDispatcher("/student_detail.jsp");
			          rd.forward(request, response);
				}
				
			}
			catch(Exception e)
			
			{
			out.println(e);
			}
		}
	}

}
