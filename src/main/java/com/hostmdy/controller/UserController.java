package com.hostmdy.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sql.DataSource;
import com.hostmdy.model.User;
import com.hostmdy.model.UserDAO;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/ministore")
	private DataSource dataSource;
	
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDAO = new UserDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		
		if (mode == null) {
			mode = "REGISTER_FORM";
		}
		switch (mode) {
		case "REGISTER_FORM":
			registerForm(request, response);
			break;
		case "REGISTER":
			register(request, response);
			break;

		default:
		    registerForm(request, response);
			break;
		}
	}
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Boolean active = Boolean.parseBoolean(request.getParameter("active"));
		Boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
		String role = admin ? "ROLE_ADMIN" : "ROLE_USER";
		User user = new User(firstname, lastname, email, password, active, role);
		int rowEffected = userDAO.register(user);
		PrintWriter out = response.getWriter();
		if (rowEffected > 0) {
			//out.print("<h1> registered </h1>");
			response.sendRedirect("login");
		}else out.print("<h1> fail to register </h1>");
	}
    protected void registerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
