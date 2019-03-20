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

import com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Attendance
 */
@WebServlet("/Attendance")
public class Attendance extends HttpServlet {
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
	public Attendance() {
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

			//Statement instance that will Execute SQL query
			Statement stmt = conn.createStatement();
			String sql;
			
			// Get the selected course id and student id
			String courseId = request.getParameter("courseId");
			String studentId = request.getParameter("stId");

			// Fetch the available attendance count from the database
			sql = "SELECT attendance  FROM enrollments WHERE course_id='" + courseId + "' AND st_id='" + studentId + "';";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				// Increase the attendance by 1
				int attendanceCount = Integer.parseInt(rs.getString("attendance"));
				++attendanceCount;
				// Set the new attendance in the database
				sql = "UPDATE enrollments set attendance=" + attendanceCount + ", marked='marked' WHERE (course_id='" + courseId + "' AND st_id='" + studentId + "');";
				stmt.executeUpdate(sql);
			}
			
			rs.close();
			conn.close();
			response.sendRedirect("/mycourses/Enrollments");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

}
