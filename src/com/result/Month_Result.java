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

public class Month_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String year, month, branch, sem, subject, rollno;
		year = request.getParameter("year");
		month = request.getParameter("month");

		branch = request.getParameter("branch");
		sem = request.getParameter("sem");

		subject = request.getParameter("subject");
		rollno = request.getParameter("rollno");

		PrintWriter out = response.getWriter();
		Connection con = null;
		Conn c = new Conn();
		con = c.getCon();
		ResultSet rs;
		PreparedStatement ps = null;

		response.setContentType("text/html");

		List dataList1 = new ArrayList();
		List dataList2 = new ArrayList();

		if (year.equals("") || branch.equals("") || sem.equals("") || rollno.equals("") || subject.equals("")
				|| month.equals("")) {
			request.setAttribute("msg", "Plz Fill All Field");
			RequestDispatcher rd2 = request.getRequestDispatcher("monthly.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		try {
			String sql1 = "select distinct date from attendance where year=? and month=? and branch=? and sem=? and subject=? and rollno=?";
			ps = con.prepareStatement(sql1);
			ps.setString(1, year);
			ps.setString(2, month);
			ps.setString(3, branch);
			ps.setString(4, sem);
			ps.setString(5, subject);
			ps.setString(6, rollno);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				dataList1.add(rs.getString("date"));
			}

			String sql2 = "select distinct date from attendance where year=? and month=? and branch=? and sem=? and subject=?";
			ps = con.prepareStatement(sql2);
			ps.setString(1, year);
			ps.setString(2, month);
			ps.setString(3, branch);
			ps.setString(4, sem);
			ps.setString(5, subject);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				dataList2.add(rs.getString("date"));
			}

		} catch (Exception e) {
			out.println(e);
		}
		float status;
		status=dataList1.size()*100/dataList2.size();
		
	
		request.setAttribute("totlec",dataList2.size());
		request.setAttribute("attlec", dataList1.size());
		request.setAttribute("status", status);
	
		request.setAttribute("attenddate", dataList1);
		request.setAttribute("totaldate", dataList2);
		// Disptching request
		RequestDispatcher rd2 = request.getRequestDispatcher("monthly.jsp");
		if (rd2 != null) {
			rd2.forward(request, response);
		}

	}

}
