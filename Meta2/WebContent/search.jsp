<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UC Busca</title>
</head>
<body>

	<s:form action="search" method="post">
		<s:text name="Search:" />
		<s:textfield name="word" /><br>
		<s:submit />
	</s:form>
	
	<c:choose>
	
	<c:when test="${session.qtdsearch <= 0}">
		</c:when>
			
		<c:when test="${session.qtdsearch > 0}">
		<br>
			There are ${session.qtdsearch} results:
			<br><br>
			
			<s:form action="search2" method="post">
			<s:text name="Option:" />
			<s:textfield name="url" />
			<s:submit />
			</s:form>
		
			<c:forEach items="${session.search}" var="value">
				<br> Page: <c:out value="${value.num}" /><br>
				<c:out value="${value.title}" /><br>
				<!--   <a href="${value.url}"><c:out value="${value.url}" /></a>	-->
				<c:out value="${value.url}" /><br>			 
				<c:out value="${value.text}" /><br>
				<br>
				<br>
				
			</c:forEach>
		
		</c:when>
	</c:choose>
	
	
	
	
</body>
</html>