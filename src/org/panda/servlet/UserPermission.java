package org.panda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.panda.UPermission;

/**
 * Servlet implementation class UserPermission
 */
@WebServlet("/UserPermission")
public class UserPermission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPermission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uID, rAcs="", wAcs="";
		boolean rA=false,wA=false;
		uID = request.getParameter("UserID");
		if(request.getParameter("readPermission")!=null){
			rAcs = request.getParameter("readPermission");
		}
		if(request.getParameter("writePermission")!=null){
			wAcs = request.getParameter("writePermission");
		}
		if(rAcs.contentEquals("Read")) rA=true;
		if(wAcs.contentEquals("Write")) wA=true;
		System.out.println("~"+rAcs+"~"+wAcs+"~"+rA);
		
		UPermission up = new UPermission(); 
		if(up.updatePermission(uID, rA, wA)){
			request.setAttribute("errorMessage", "Permissions updated succesfully");
			RequestDispatcher rd = request.getRequestDispatcher("UserPermission.jsp");
            rd.forward(request, response); 
		}
		else{
			request.setAttribute("errorMessage", "Permissions update failed");
			RequestDispatcher rd = request.getRequestDispatcher("UserPermission.jsp");
            rd.forward(request, response); 
		}
		
		//RequestDispatcher rd = request.getRequestDispatcher("DataOwnerHome.jsp");
        //rd.forward(request, response);
	}

}
