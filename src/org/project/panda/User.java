package org.project.panda;

public class User {

	String firstName, lastName, userID, password, privateKey;
	boolean readAccess, writeAccess;
	
	public User(){
		
	}
	
	public User(String fname, String lname, String uid, String pwd){

		this.firstName=fname;
		this.lastName=lname;
		this.userID=uid;
		this.password=pwd;
		this.privateKey="123456";
		this.readAccess=false;
		this.writeAccess=false;		
		
	}
	
}
