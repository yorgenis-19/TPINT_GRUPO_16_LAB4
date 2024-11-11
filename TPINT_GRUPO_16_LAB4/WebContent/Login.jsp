<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Iniciar Sesión</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	<jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>

<%
int filas = 0;
Usuario usuario = null;
%>

 <div class="container login-container">
	<h2 class="login-title">¡Hola! Te damos la bienvenida</h2>
 	<form class="login-form border border-primary" method="post" action="ServletLogin">
	  <div class="mb-3">
	    <label for="txtUsuario" class="form-label">Usuario</label>
	    <input type="text" class="form-control" id="txtUsuario" aria-describedby="emailHelp" name="txtUsuario">
	  </div>
	  <div class="mb-3">
	    <label for="txtClave" class="form-label">Password</label>
	    <input type="password" class="form-control" id="txtClave" name="txtClave">
	  </div>
	  <%

	  if(request.getAttribute("usuarioIncorrecto") != null && (boolean)request.getAttribute("usuarioIncorrecto") == true){
		  %>El nombre de usuario o la contraseña es incorrecta.<%
	  }
	  %>
	  <button type="submit" class="btn btn-primary" name="btnIngresar">Ingresar</button>
	</form>
	<!-- 
	<div id="popup" class="popup">
	    <span class="close-btn" onclick="closePopup()">&times;</span>
	    <p id="popupMessage"></p>
	</div>
	 -->
 </div>
</body>
</html>