package com.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class teamServlet
 */
@WebServlet("/teamServlet")
public class teamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = "local";
		String pass = "password";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/teams?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		try{
			PrintWriter out = response.getWriter();
			out.println("Connecting to database: ");
			Class.forName(driver);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("Successfully Connected");
			myConn.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
