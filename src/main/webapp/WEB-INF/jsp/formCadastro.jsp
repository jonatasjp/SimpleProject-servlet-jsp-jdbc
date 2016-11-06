<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cadastro</title>
</head>
<body>
	<%Usuario usuario = (Usuario)request.getAttribute("usuario");%>
	<form action="usuController.do" method="post">
		
		<input type="hidden" name="id" value="<%=usuario.getId()%>">
		
		nome:<input type="text" name="nome" value="<%=usuario.getNome()%>">
		<br/>
		sobrenome:<input type="text" name="sobrenome" value="<%=usuario.getSobrenome()%>">
		<br/>
		login:<input type="text" name="login" value="<%=usuario.getLogin()%>">
		<br/>
		senha:<input type="text" name="senha" value="<%=usuario.getSenha()%>">
		<br/>
		
		<input type="submit" value="enviar">
	
	</form>


</body>
</html>