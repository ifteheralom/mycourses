package com.ialom;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnrollFwd
 */
@WebServlet("/EnrollFwd")
public class EnrollFwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollFwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// System.out.println("Hello"+ request.getParameter("userName") + request.getParameter("fullName") + request.getParameter("userId"));
		request.setAttribute("fullname", request.getParameter("fullName"));
		request.setAttribute("username", request.getParameter("userName"));
		request.setAttribute("userid", request.getParameter("userId"));
		request.getRequestDispatcher("/Enroll").forward(request, response);
	}

}
