<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File New M2</title>
</head>
<body>

	<c:choose>
		<c:when test="${Teste.username != null}">
			<p>Yep.</p>
		</c:when>
		<c:otherwise>
			<p>Write an username</p>
		</c:otherwise>
	</c:choose>


	<p><a href="<s:url action="index" />">Start</a></p>

</body>
</html>