package org.panda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.project.panda.URegister;
import org.project.panda.User;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String firstName, lastName, userID, password;
		
		firstName=request.getParameter("firstname");
		lastName=request.getParameter("lastname");
		userID=request.getParameter("userID");
		password=request.getParameter("pwd");
		
		User u = new User(firstName,lastName,userID,password);
		
		URegister ureg = new URegister();
		
		if(ureg.registerUser(u)){
			request.setAttribute("errorMessage", "User Registered succesfully");
			RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
            rd.forward(request, response); 
		} 
		
		else{
			request.setAttribute("errorMessage", "User Registration Failed");
			RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
            rd.forward(request, response);
		}

	}

}
