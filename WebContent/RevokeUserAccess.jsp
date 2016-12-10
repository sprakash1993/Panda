<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revoke Access</title>
<script type="text/javascript">

function validateForm() {

	 //alert(document.forms["frmUserPermission"]["UserID"].value);
 if(document.forms["frmRevokeAccess"]["UserID"].selectedIndex == "0"){
	 alert("Select User");
	 return false;
 }
	
}

function cancelUpdate(){
	window.location.href="DataOwnerHome.jsp";	
}

</script>

</head>
<body>
<% Connection myconn =null;
String url = "jdbc:mysql://localhost:3306/panda";
int rows=0;
try{
	Class.forName ("com.mysql.jdbc.Driver");
	myconn = DriverManager.getConnection (url, "root", "root");
	//Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
	Statement mystmt = myconn.createStatement();
	
	String qry = "select * from user";
	System.out.println(qry);
	
	resultset=mystmt.executeQuery(qry);
%>
<h5 align="right" style="color:Blue">Logged in as:  <% if (session != null) {
	if (session.getAttribute("DOName") != null) {
		String name = (String) session.getAttribute("DOName");
		out.print(" "+name);
	} else {
		out.println("No Session");
		request.setAttribute("errorMessage", "Session Expired, Login Again.");
		RequestDispatcher rd = request.getRequestDispatcher("DataOwnerLogin.jsp");
        rd.forward(request, response); 
	}
} %></h5>
<h1>User Permission</h1>

<form action="RevokeAccess" name="frmRevokeAccess" method="post" onsubmit="return validateForm()">
<table>
<tr>
<th>Select User</th>
<td><select name="UserID" id="UserID">
			<option>--Select--</option>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
            <% } %>     
        </select></td>
</tr>
<tr>
<th rowspan="2">Permissions</th>
<td rowspan="2">
<input type="checkbox" name="readPermission" id="readPermission" value="Read" disabled="disabled" >Read<br>
<input type="checkbox" name="writePermission" id="writePermission" value="Write" disabled="disabled">Write
</td>
</tr><tr></tr>
<tr><td colspan="2"><p id="errMsg" style="color:red"> <%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%></p></td></tr>
<tr>
<td> <input type="submit" value="Submit">  </td>
<td> <input type="button" onclick="cancelUpdate()" value="Cancel"> </td>
</tr>
</table>
</form>
<%} 
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
} %>
</body>
</html>