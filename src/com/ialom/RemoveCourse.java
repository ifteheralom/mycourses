package com.ialom;

import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class RemoveCourse
 */
@WebServlet("/RemoveCourse")
public class RemoveCourse extends HttpServlet {
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
	public RemoveCourse() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		Connection conn = null;

		try {
			// Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			Statement stmt = conn.createStatement();
			String sql;

			String id = request.getParameter("id");

			sql = "DELETE  FROM courses WHERE course_id='" + id + "';";
			stmt.executeUpdate(sql);
			conn.close();
			response.sendRedirect("/mycourses/Admin");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

}
