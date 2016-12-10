package org.project.panda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class URegister {
	@SuppressWarnings("finally")
	public boolean registerUser(User us){
		Connection myconn =null;
		String url = "jdbc:mysql://localhost:3306/panda";
		int rows=0;
		try{
			Class.forName ("com.mysql.jdbc.Driver");
			myconn = DriverManager.getConnection (url, "root", "root");
			//Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement mystmt = myconn.createStatement();
			
			String qry = "insert into user values ('"+us.userID+"','"+us.firstName+"','"+us.lastName+"','"+us.password+"',"+"123456,"+false+","+false+")";
			System.out.println(qry);
			
			rows= mystmt.executeUpdate(qry);
			
			
			
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
			if (rows>0){
				return true;
			}
			return false;
		}
		
	}



}
