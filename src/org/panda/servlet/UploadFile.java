package org.panda.servlet;

import java.io.*;
import java.sql.*;
//import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.project.panda.CryptoException;
import org.project.panda.CryptoUtils;

import javax.servlet.http.*;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
@MultipartConfig(maxFileSize = 16177215) 
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dbURL = "jdbc:mysql://localhost:3306/panda";
	private String dbUser = "root";
	private String dbPass = "root";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    String flName;
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream inputStream = null; // input stream of the upload file
        
		String key = "1234567890123456";
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("textFile");
        if (filePart != null) {
        	
        String fileName = getFileName(filePart); 
        System.out.println(fileName);
        
        File inputFile = new File(fileName);
        File encryptedFile = new File("document.encrypted");
        //File decryptedFile = new File("C:\\Users\\SP\\Desktop\\decrypted.txt");
        flName = inputFile.getName();
        try {
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            //CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            //inputStream = filePart.getInputStream();
            inputStream = new FileInputStream(encryptedFile);
        }
        
        
        
        
         
        Connection conn = null; // connection to the database
        String message = null;
        
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql;
            sql ="insert into DOfiles(textFile,createdDate,fileName) values(?,now(),'"+flName+"');";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            System.out.println(sql);
            
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(1, inputStream);
            }
            System.out.println(sql);
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            // sets the message in request scope
            //request.setAttribute("Message", message);
             
            request.setAttribute("errorMessage", message);
			RequestDispatcher rd = request.getRequestDispatcher("FileUpload.jsp");
            rd.forward(request, response);
            // forwards to the message page
            //getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }    
        
        
        
	}

	private String getFileName(final Part part) {
	    //final String partHeader = part.getHeader("content-disposition");
	    //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
