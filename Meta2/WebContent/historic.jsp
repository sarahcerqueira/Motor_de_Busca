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
	<s:form action="historic" method="post">
	<c:when test="${session.loggedin == true}">
			<p>Welcome, ${session.username}. Say HEY to someone.</p>
		</c:when>
		<p>Here is your historic, ${session.username}</p>
		<br>
		
		<c:forEach items="${session.historicresults}" var="v">
			<c:out value ="${v}" /><br>
		</c:forEach>
	
	</s:form>
</body>
</html>