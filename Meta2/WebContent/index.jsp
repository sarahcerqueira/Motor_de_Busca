<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UC Busca</title>
</head>
<body>
	<s:form action="index" method="post">
		<s:text name="URL:" />
		<s:textfield name="url" /><br>
		<s:submit />
	</s:form>
	
	<c:choose>
		<c:when test="${session.indexacao}">
			<br>Sua solicitação foi enviada :)
		</c:when>
		<c:when test="${session.indexacao}">
			<br>Houve problemas com sua solicitacao :)
		</c:when>
	</c:choose>
</body>
</html>