package com.ialom;

import java.io.*;
import java.util.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Teacher
 */
@WebServlet("/Teacher")
public class Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/coursesdb";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "333";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn = null;
		
		// get session parameters for a user
		String user_name = (String)request.getSession(false).getAttribute("userName");
		String full_name = (String)request.getSession(false).getAttribute("fullName");
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Statement instance that will Execute SQL query
			Statement stmt = conn.createStatement();
			String sql;

			// Fetch the courses that has been assigned to the logged in teacher
			sql = "SELECT *  FROM courses WHERE course_teacher='" + full_name + "';";
			ResultSet rs = stmt.executeQuery(sql);

			List<String[]> courseData = new ArrayList<String[]>();
			
			// Collect the result in a list of arrays
			while (rs.next()) {
				String ara[] = {rs.getString(1), rs.getString(2), rs.getString(3)};
				courseData.add(ara);
			}
			rs.close();
			conn.close();
			
			// Pass the list to the jsp age
			request.setAttribute("username", user_name);
			request.setAttribute("fullname", full_name);
			request.setAttribute("courses", courseData);
			request.getRequestDispatcher("/home-teacher.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
