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
<a href="search.jsp">Search</a>
<br>
<a href="historic.jsp">Historic</a>
<s:form action="historic" method="post">
		<s:submit type = "button" label = "Historic" />
		
</s:form>
<br>
<a href="usepage.jsp">Exit</a>


</body>
</html>