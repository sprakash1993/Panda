package org.panda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.project.panda.*;
/**
 * Servlet implementation class DataOwnerRegister
 */
@WebServlet("/DataOwnerRegister")
public class DataOwnerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName, lastName, userID, password;
		
		firstName=request.getParameter("firstname");
		lastName=request.getParameter("lastname");
		userID=request.getParameter("userID");
		password=request.getParameter("pwd");
		
		DataOwner d = new DataOwner(firstName,lastName,userID,password);
		
		DORegister dreg = new DORegister();
		
		if(dreg.registerDataOwner(d)){
			request.setAttribute("errorMessage", "Data Owner Registered succesfully");
			RequestDispatcher rd = request.getRequestDispatcher("DataOwnerRegister.jsp");
            rd.forward(request, response); 
			//response.sendRedirect("test.jsp");
		} 
		
		else{
			request.setAttribute("errorMessage", "Data Owner Registration Failed");
			RequestDispatcher rd = request.getRequestDispatcher("DataOwnerRegister.jsp");
            rd.forward(request, response);
			//response.sendRedirect("error.jsp");
		}
		
		
		
		
		
	}

}
