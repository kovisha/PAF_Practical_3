<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import = "com.item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Item</title>
</head>
<body>



<%

if (request.getParameter("itemCode") == null)
{
 	item itemObj = new item();
 	
	itemObj.connect();
}



%>

</body>
</html>