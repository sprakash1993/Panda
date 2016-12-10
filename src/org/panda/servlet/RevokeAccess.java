package org.panda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.panda.RevokeUser;

/**
 * Servlet implementation class RevokeAccess
 */
@WebServlet("/RevokeAccess")
public class RevokeAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevokeAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uID;
		uID = request.getParameter("UserID");
		
		RevokeUser ru = new RevokeUser();
		if(ru.revokeAccess(uID)){
			request.setAttribute("errorMessage", "User Access Revoked Successfully");
			RequestDispatcher rd = request.getRequestDispatcher("RevokeUserAccess.jsp");
            rd.forward(request, response); 
		}
		else{
			request.setAttribute("errorMessage", "Failed to Revoke Access !!");
			RequestDispatcher rd = request.getRequestDispatcher("RevokeUserAccess.jsp");
            rd.forward(request, response);
		}
	}

}
