<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="j" %>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
    <title>Cadastro de Cliente</title>
</head>
<body>
 
<h2>Formulário de Cadastro de Cliente</h2>

<form action="/WebSpring/salvaCliente">
	<input type="text" name="nome">
	<input type="submit">
</form>
<a href="/cliente/listaClientes">Lista</a>
<br>
<c:if test="${pessoas != null }">
<br>
<c:forEach items="${pessoas}" var="item">
    ${item.nome}<br>
</c:forEach>
</c:if>
</body>
</html>