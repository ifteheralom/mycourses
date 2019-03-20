package com.ialom;

import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost:3306/coursesdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "333";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		Connection conn = null;
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Statement instance that will submit the sql query
			Statement stmt = conn.createStatement();
			String sql;

			// Fetch username and password from the form
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			// query the dataabase for user credentials
			sql = "SELECT *  FROM users WHERE user_name='" + username + "' AND pass_word='" + password + "';";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (!rs.next()) {
				// if the credentials are incorrect return an error message
				request.setAttribute("errorMessage", "Incorrect username or password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				//response.sendRedirect("/mycourses/Login");
			} else {
				// user credentials were correct set parameters that will be required for the session
				HttpSession session = request.getSession(true);
				session.setAttribute("userName", username);
				session.setAttribute("fullName", rs.getString("full_name"));
				session.setAttribute("userId", rs.getString("user_id"));
				
				// redirect to user specific pages
				switch(rs.getString("type")) {
					case "admin": {
						response.sendRedirect("/mycourses/Admin");
						break;
					}
					case "teacher": {
						response.sendRedirect("/mycourses/Teacher");
						break;
					}
					case "student": {
						response.sendRedirect("/mycourses/Student");
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
	}

}
