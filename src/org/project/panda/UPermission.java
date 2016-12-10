package org.project.panda;

import java.sql.*;

public class UPermission {
	
	@SuppressWarnings("finally")
	public boolean updatePermission(String uID, boolean rAccess, boolean wAccess){
		Connection myconn =null;
		String url = "jdbc:mysql://localhost:3306/panda";
		int rows=0;
		try{
			Class.forName ("com.mysql.jdbc.Driver");
			myconn = DriverManager.getConnection (url, "root", "root");
			
			Statement mystmt = myconn.createStatement();
			
			//String qry = "insert into user values ('"+us.userID+"','"+us.firstName+"','"+us.lastName+"','"+us.password+"',"+"123456,"+false+","+false+")";
			String qry;
			if(rAccess == true && wAccess==true){
			qry = "update user set readAccess = true, writeAccess = true where userID='"+uID+"' ;";
			}
			else if(rAccess == true){
				qry = "update user set readAccess = true where userID='"+uID+"' ;";;
			}
			else{
				qry = "update user set writeAccess = true where userID='"+uID+"' ;";;
			}
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
