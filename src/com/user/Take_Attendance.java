package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

public class Take_Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps = null;

	String date, year, month, branch, sem, subject;
	//int i = 0;
	int result = 0;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		date = request.getParameter("date");
		year = request.getParameter("year");
		month = request.getParameter("month");
		sem = request.getParameter("sem");
		branch = request.getParameter("branch");
		subject = request.getParameter("subject");
		String rollno[] = request.getParameterValues("rollno");
		int roll_len = rollno.length;

		Conn c = new Conn();
		con = c.getCon();
		if (sem.equals("")||branch.equals("")||year.equals("")||month.equals("")||subject.equals("")||date.equals("") || roll_len <= 0) {
			request.setAttribute("msg", "Plz Fill Or Check All Field");
			RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
			rd.forward(request, response);

		}
		try {
			for(int i=0;i<roll_len;i++)
			/*while (i < roll_len)*/ {
				ps = con.prepareStatement(
						"insert into attendance(date,branch,sem,subject,rollno,month,year) values(?,?,?,?,?,?,?)");
				ps.setString(1, date);
				ps.setString(2, branch);
				ps.setString(3, sem);
				ps.setString(4, subject);
				ps.setString(5, rollno[i]);
				ps.setString(6, month);
				ps.setString(7, year);

				result = ps.executeUpdate();

			}
			if (result > 0) {
				request.setAttribute("msg", "Successfully Submited");
				RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
				rd.forward(request, response);
			}

			else {
				request.setAttribute("msg", "Something Wrong");
				RequestDispatcher rd = request.getRequestDispatcher("/attendance.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {

			out.println(e);
		}
	}
}
