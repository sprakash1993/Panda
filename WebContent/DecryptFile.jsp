<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Decrypt File</title>
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
	
	String qry = "select * from DOfiles";
	System.out.println(qry);
	
	resultset=mystmt.executeQuery(qry);
%>

<h1>Decrypt Upload</h1>
<form method="post" action="FileDecrypt" onsubmit="">
            <table border="0">
                <tr>
                <th>Select File<th>
                <td>
                <select name="DocID" id="DocID">
			<option>--Select--</option>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
            <% } %>     
        </select>
                </td>
                </tr>
     <tr>           
  	<td colspan="2"><p id="errMsg" style="color:red"><%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%></p></td>
  	</tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Getfile">
                    </td>
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