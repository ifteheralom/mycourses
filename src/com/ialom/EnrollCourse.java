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
 * Servlet implementation class EnrollCourse
 */
@WebServlet("/EnrollCourse")
public class EnrollCourse extends HttpServlet {
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
	public EnrollCourse() {
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

		// Get the user parameters from the session
		String user_id = (String)request.getSession(false).getAttribute("userId");
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

			// Get the selected course parameters
			String id = request.getParameter("courseId");
			String name = request.getParameter("courseName");
			String teacher = request.getParameter("courseTeacher");
			
			// Check if the student is already enrolled for this course
			sql = "SELECT * FROM enroll WHERE (user_name='" + user_name + "' and course_id='" + id + "');";
			ResultSet rs = stmt.executeQuery(sql);
			
			// If the user has not enrolled for this course
			if(!rs.next()) {
				// add the course in the database under the students enrolled courses
				sql = "INSERT  INTO enroll VALUES ('" + id + "','" + name + "','" + teacher + "','" + user_name + "');";
				stmt.executeUpdate(sql);
				
				// add the students details in the course enrollments for the teacher
				sql = "INSERT  INTO enrollments VALUES ('" + user_id + "','" + full_name + "','" + id + "', 0, null);";
				stmt.executeUpdate(sql);
			}
			
			conn.close();
			response.sendRedirect("/mycourses/Student");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

}
