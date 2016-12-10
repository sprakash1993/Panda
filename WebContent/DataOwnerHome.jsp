<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<script type="text/javascript">

function openForm(url){
	window.location.href=url;	
}

</script>
</head>
<body>
<p style="align:right">Welcome : <% if (session != null) {
	if (session.getAttribute("DOName") != null) {
		String name = (String) session.getAttribute("DOName");
		out.print("Hello, " + name + "  Welcome to Home Page");
	} else {
		out.println("No Session");
		request.setAttribute("errorMessage", "Session Expired, Login Again.");
		RequestDispatcher rd = request.getRequestDispatcher("DataOwnerLogin.jsp");
        rd.forward(request, response); 
	}
} %> </p>

<input type="button" value="Permission" onclick="openForm('UserPermission.jsp')" >
<br/><br/><br/>
<input type="button" value="Upload" onclick="openForm('FileUpload.jsp')" >
<br/><br/><br/>
<input type="button" value="Revoke Access" onclick="openForm('RevokeUserAccess.jsp')" >
<br/><br/><br/>
<form action="DataOwnerLogout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>