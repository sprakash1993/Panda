<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
</head>
<body>
<h1>File Upload</h1>
<form method="post" action="UploadFile" enctype="multipart/form-data" onsubmit="">
            <table border="0">
                
                <tr>
                    <td>Select text file : </td>
                    <td><input type="file" name="textFile" id="textFile" size="30"/></td>
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
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>

</body>
</html>