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
		<p>Here are your results, ${session.username}</p>
		<br>
		
		
	<c:choose>
	
	<c:when test="${session.searchRBean.size <= 0}">
			<p>Você não tem results</p>
		</c:when>
			
		<c:when test="${session.searchRBean.size > 0}">
			<p>Você tem results</p>
		</c:when>
	</c:choose>
	
	<c:forEach items="${session.searchRBean.historic}" var="value">
		<c:out value="${value}" /><br>
	</c:forEach>
	
		
		
    	
</body>
</html>