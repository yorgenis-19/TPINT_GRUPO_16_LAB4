<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscador Clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">    
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
	<jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
<%
Usuario usuario = new Usuario(); 
if(session.getAttribute("UsuarioActual") != null)
{
	usuario = (Usuario)session.getAttribute("UsuarioActual");
}
%>
<nav class="navbar bg-primary navbar-expand-lg " data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="Administrador.jsp">
    	<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20"><path fill="currentColor" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5" clip-rule="evenodd"/></svg>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Administrador.jsp">Inicio</a>
        </li>
      </ul>
      <form class="d-flex" role="search" method="post" action="ServletLogout">
      	<span class="username"><%=usuario.getNombre()%></span>
        <button class="btn btn-secondary" type="submit" name="btnLogout">Cerrar sesión</button>
      </form>
    </div>
  </div>
</nav>
<div class="container page-container">
        <h1 style="text-align: center;">Buscar Clientes</h1>
        <div class="header">
	        <div class="filter-container">
	        	<div class="filter-container-column">
	        		<label for="txtNombre">NOMBRE: </label>
					<input type="text" name="txtNombre" id="txtNombre"/>
					<label for="txtApellido">APELLIDO: </label>
					<input type="text" name="txtApellido" id="txtApellido"/>
	        	</div>
	        	<div class="filter-container-column">
	        		<label for="txtDNI">D.N.I.: </label>
					<input type="text" name="txtDNI" id="txtDNI"/>
					<label for="txtEmail">EMAIL: </label>
					<input type="text" name="txtEmail" id="txtEmail"/>
	        	</div>
	        </div>
	        
	       	<div class="button-wrapper">
	          <button type="submit" class="button">
	              Buscar
	          </button>
	        </div>
        </div>
        <div class="grid-container">
        	<table border="1" style="width: 100%;" class="table">
        		<tr>
        			<th>APELLIDO</th>
        			<th>NOMBRE</th>
        			<th>DNI</th>
        			<th>EMAIL</th>
        			<th></th>
        		</tr>
        		<%
        		for(int i = 0; i < 5; i++) {
    			%>
        		<tr>
        			<td>Perez</td>
        			<td>Juan</td>
        			<td>12345678</td>
        			<td>asd@asd.com</td>
        			<td>
	        			<button type="submit" class="button">
	                        Ver
	                    </button>
                    </td>
                    <td>
	        			<button type="submit" class="button">
	                        Eliminar
	                    </button>
                    </td>
        		</tr>
        		<%} %>
        	</table>
        </div>
</div>
</body>
</html>