package org.project.panda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ULogin {
	public String CheckLogin(String uname, String pass){
		try{
			String url = "jdbc:mysql://localhost:3306/panda";
			Class.forName ("com.mysql.jdbc.Driver");
			Connection myconn = DriverManager.getConnection (url, "root", "root");
			//Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement mystmt = myconn.createStatement();
			
			String qry = "select * from user";
			
			ResultSet rs = mystmt.executeQuery(qry);
			
			while(rs.next()){
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
				if(rs.getString(1).contains(uname) && rs.getString(4).contains(pass)){
					return rs.getString(2);
				}
			}
		}
		catch(Exception e){
			System.out.println("Connection not established");
			e.printStackTrace();
		}
		
		
		return null;
		
		}
}
