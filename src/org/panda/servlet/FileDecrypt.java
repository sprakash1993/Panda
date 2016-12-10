package org.panda.servlet;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.panda.CryptoUtils;

/**
 * Servlet implementation class FileDecrypt
 */
@WebServlet("/FileDecrypt")
public class FileDecrypt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDecrypt() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final int BUFFER_SIZE = 4096;
    private String flName = null;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int docId = Integer.parseInt(request.getParameter("DocID"));
		
		File encryptedFile = new File("document.encrypted");
		
		
		Connection myconn =null;
		String url = "jdbc:mysql://localhost:3306/panda";
		//String flName;
		String key = "1234567890123456";
		try{
			Class.forName ("com.mysql.jdbc.Driver");
			myconn = DriverManager.getConnection (url, "root", "root");
			
			Statement mystmt = myconn.createStatement();
			
			//String qry = "insert into user values ('"+us.userID+"','"+us.firstName+"','"+us.lastName+"','"+us.password+"',"+"123456,"+false+","+false+")";
			String qry = "select * from DOfiles where docID="+docId+";";
			
			System.out.println(qry);
			ResultSet rs = mystmt.executeQuery(qry); 
			if (rs.next()){ 
			Blob blob = rs.getBlob("textFile");	
			flName = rs.getString("fileName");
			
			File decryptedFile = new File( "E:\\"+flName);
			
			InputStream inputStream = blob.getBinaryStream();
            OutputStream outputStream = new FileOutputStream(encryptedFile);
            
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
            System.out.println("File saved");
            
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
			}
			else{
				System.out.println("No result");
			}
		}
		catch(Exception e){
			//System.out.println("Connection not established");
			e.printStackTrace();
		}
		finally {
			try {
				myconn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			request.setAttribute("errorMessage", "File Decrypted to E:\\"+flName);
			RequestDispatcher rd = request.getRequestDispatcher("DecryptFile.jsp");
            rd.forward(request, response); 
		
		}
		
		
	}

}
