<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<script type="text/javascript">
function validateForm() {
	var uname = document.forms["frmDOLogin"]["username"].value;
	var password = document.forms["frmDOLogin"]["pwd"].value;
	if (uname == "" || uname == null){
		alert("Enter username");
		return false;		
	}
	if (password == "" || password == null){
		alert("Enter password");
		return false;		
	}
}

function registerUser(){
	window.location.href="UserRegister.jsp";	
}

</script>

</head>
<body>
<form name="frmUserLogin" action="UserLogin" method="post" onsubmit="return validateForm()">
  <h1>User Login</h1><br/>
  <table>
  	<tr>
  	<th>UserName</th>
  	<td> <input type="text" id="username" name="username"></input></td>
  	</tr>
  	<tr>
  	<th>Password</th>
  	<td><input type="password" id="pwd" name="pwd"></td>
  	</tr>
  	<tr>
  	<td colspan="2" ><p id="errMsg" style="color:red"><%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%></p></td>
  	</tr>
  	<br/>
  	<tr>
  	<td>
<input type="submit" value="Login">
</td>
<td>
<input type="button" value="Register" onclick="registerUser()" >
</td>
</tr>
  </table>  
</form>

</body>
</html>