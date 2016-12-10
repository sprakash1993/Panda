package org.panda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DataOwnerLogout
 */
@WebServlet("/DataOwnerLogout")
public class DataOwnerLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataOwnerLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		//out.println("thanq you!!, Your session was destroyed successfully!!");
		HttpSession session = request.getSession(false);
		// session.setAttribute("user", null);
		session.removeAttribute("DOName");
		session.invalidate();
		request.setAttribute("errorMessage", "Logged out succesfully");
		RequestDispatcher rd = request.getRequestDispatcher("DataOwnerLogin.jsp");
        rd.forward(request, response); 
	}

}
