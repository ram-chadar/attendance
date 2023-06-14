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
import javax.websocket.Session;

import com.dbcon.Conn;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;


public class PrintReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	Connection con;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   // PrintWriter out=response.getWriter();
		String branch,sem;
		branch=request.getParameter("branch");
		sem=request.getParameter("sem");
		try {
			Conn c= new Conn();
			con=c.getCon();
		HashMap<String, Object> param =new HashMap<String, Object>();
		
		param.put("branch",branch);
		param.put("sem",sem);
	
		/*InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
		JasperPrint jp=JasperFillManager.fillReport(is, param,con);
		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);*/
		ServletContext context=request.getServletContext();
		
		String jrxmlfile=context.getRealPath("/Reports/ListStudent.jrxml");
		InputStream input=new FileInputStream(new File(jrxmlfile));
		
		
		JasperReport jr=JasperCompileManager.compileReport(input);
		JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
		JasperExportManager.exportReportToPdfStream(jp,response.getOutputStream());

		JasperViewer jw=new JasperViewer(jp);
		jw.setVisible(true);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		/*RequestDispatcher rd=request.getRequestDispatcher("/view_all_student.jsp");
		rd.forward(request, response);  */
		
		}catch(Exception exc)
		{
			System.out.println(exc);
					//out.println(exc);
						//out.println("print page");
		}
			
	}

}
