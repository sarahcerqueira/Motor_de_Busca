<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UC Busca</title>
</head>
<body>

User registered as nonAdmin options:
<br>
<a href="searchResults.jsp">Search</a>
<br>

<a href=<s:url action='searchResults'/>>searchResults</a>

<a href=<s:url action='historic'/>>Historic</a>

<br>
<a href="usepage.jsp">Exit</a>


</body>
</html>