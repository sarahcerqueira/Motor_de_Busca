<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UC Busca</title>
</head>
<body>


<c:choose>
	<c:when test="${session.admin}">
		User registered as admin options:
		<br>
		<a href="search.jsp">Search</a>
		<br>
		<a href=<s:url action='historic	'/>>See navigation historic</a>
		<br>
		<a href="index.jsp"> Add URL</a>
		<br>
		<a href=<s:url action='t10pages'/>> Top10 searched pages</a>
		<br>
		<a href=<s:url action='t10words'/>> Top10 searched words</a>
		<br>
		<a href=<s:url action='usepage.jsp'/>> Exit</a>
		</c:when>
			
		<c:when test="${!session.admin}">
		User registered as nonAdmin options:
		
			<a href="searchResults.jsp">Search</a>
			<br>
			<a href=<s:url action='searchResults'/>>searchResults</a>
			<a href=<s:url action='historic'/>>Historic</a>
			<br>
			<a href="usepage.jsp">Exit</a>		
			</c:when>
	</c:choose>
<br>



</body>
</html>