package org.panda.servlet;

import java.io.IOException;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.project.panda.DOLogin;

/**
 * Servlet implementation class DataOwnerLogin
 */
@WebServlet("/DataOwnerLogin")
public class DataOwnerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataOwnerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
 	
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname, pass;
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
		
        //request.getRequestDispatcher("DataOwnerLogin.jsp").include(request, response);
        
		uname = request.getParameter("username");
		pass =  request.getParameter("pwd");
		
		//System.out.println(uname+ " "+ pass);
		
		DOLogin dl = new DOLogin();
		HttpSession session = request.getSession(true);
		boolean result = dl.CheckLogin(uname, pass);
		//boolean result = true;
		if(result){
			session.setAttribute("DOName", uname);
			response.sendRedirect("DataOwnerHome.jsp");
			return;
		}
		else{
			//response.sendRedirect("error.jsp");
			//return;
			session.invalidate();
			request.setAttribute("errorMessage", "Invalid user or password");
			RequestDispatcher rd = request.getRequestDispatcher("DataOwnerLogin.jsp");
            rd.forward(request, response); 
			//out.print("Sorry, username or password error!");  
             
		}
		
		out.close();
	}

}
