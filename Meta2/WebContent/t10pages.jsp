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
	
	<c:when test="${session.pages.size <= 0}">
		There are not search pages.
		</c:when>
			
		<c:when test="${session.pages.size > 0}">
		<br>
			<c:forEach items="${session.pages.text}" var="value">
				<c:out value="${value}" /><br>
			</c:forEach>
		
		</c:when>
	</c:choose>
</body>
</html>