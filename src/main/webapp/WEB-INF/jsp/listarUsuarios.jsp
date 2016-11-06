<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de usuários clean</title>
<script type="text/javascript">
	
	function confirmaExclusao(id){
		
		if(window.confirm("tem certeza que deseja realizar a exclusão?")){
			location.href =  "usuController.do?acao=excluir&id="+id;
		}
	}

</script>

</head>
<body>

<table border="1">
<tr>
	<th>ID</th>
	<th>Nome</th>
	<th>Sobrenome</th>
	<th>Login</th>
	<th>excluir</th>
	<th>alterar</th>
</tr>
<%
List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<%for(Usuario usuario : usuarios){ %>
	<tr>
		<td>
			<%=usuario.getId()%>
		</td>
		<td>
			<%=usuario.getNome()%>
		</td>
		<td>
			<%=usuario.getSobrenome()%>
		</td>
		<td>
			<%=usuario.getLogin()%>
		</td>
		<td>
			<a href="javascript:confirmaExclusao(<%=usuario.getId()%>)">excluir</a>
		</td>
		<td>
			<a href="usuController.do?acao=alterar&id=<%=usuario.getId()%>">alterar</a>
		</td>
	</tr>
<% 
}
%>

</table>
<br/>
<a href="usuController.do?acao=inserir">Cadastrar novo usuário</a>
</body>
</html>