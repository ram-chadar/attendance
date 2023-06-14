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

public class ByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String branch, sem, date, subject;
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		date = request.getParameter("attdate");
		subject = request.getParameter("subject");

		PrintWriter out = response.getWriter();

		Conn c = new Conn();
		con = c.getCon();
		ResultSet rs;
		PreparedStatement ps = null;

		response.setContentType("text/html");

		List dataList1 = new ArrayList();

		if (date.equals("") || branch.equals("") || sem.equals("") || sem.equals("")) {
			request.setAttribute("msg", "Plz Fill All Field");
			RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
			if (rd2 != null) {
				rd2.forward(request, response);
			}
		}
		try {

			String sql1 = "select distinct rollno from attendance where date=? and branch=? and sem=? and subject=?";
			ps = con.prepareStatement(sql1);
			ps.setString(1, date);
			ps.setString(2, branch);
			ps.setString(3, sem);
			ps.setString(4, subject);

			rs = ps.executeQuery();

			while (rs.next()) {
				// Add records into data list
				dataList1.add(rs.getString("rollno"));
			}

		} catch (Exception e) {
			out.println(e);
		}

		request.setAttribute("rollbydate", dataList1);

		// Disptching request
		RequestDispatcher rd2 = request.getRequestDispatcher("daily.jsp");
		if (rd2 != null) {
			rd2.forward(request, response);
		}

	}

}
