 package com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicBorders.RolloverButtonBorder;
import javax.websocket.Session;

import com.dbcon.Conn;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;


public class Daily_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	Connection con;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   // PrintWriter out=response.getWriter();
		String date,branch,sem,subject;
		branch = request.getParameter("branch");
		sem = request.getParameter("sem");
		date = request.getParameter("date");
		subject = request.getParameter("subject");
		System.out.println(branch);
		System.out.println(date);
		System.out.println(sem);
		System.out.println(subject);
		Conn c= new Conn();
		con=c.getCon();
		try {
		HashMap<String, Object> param =new HashMap<String, Object>();
		
		param.put("date", date);
		param.put("branch", branch);
		param.put("sem", sem);
		param.put("subject", subject);
	
		/*InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
		JasperPrint jp=JasperFillManager.fillReport(is, param,con);
		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);*/
		ServletContext context=request.getServletContext();
		
		String jrxmlfile=context.getRealPath("/Reports/DailyReport.jrxml");
		InputStream input=new FileInputStream(new File(jrxmlfile));
		
		
		JasperReport jr=JasperCompileManager.compileReport(input);
		JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
		JasperExportManager.exportReportToPdfStream(jp,response.getOutputStream());

		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		RequestDispatcher rd=request.getRequestDispatcher("/daily.jsp");
		rd.forward(request, response);  
		
		}catch(Exception exc)
		{
			System.out.println(exc);
					//out.println(exc);
						//out.println("print page");
		}
			
	}

}
