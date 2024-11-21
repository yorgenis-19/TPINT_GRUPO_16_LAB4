<%@page import="negocioImpl.CuentaTipoNegocioImpl"%>
<%@page import="entidad.CuentaTipo"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

<div class="container">
    <h2>Crear Cuenta</h2>
    
    <form action="ServletNuevaCuenta" method="post">
    <div id="BusquedaCliente">
        <input type="number" id="IDCliente" name="IDCliente" placeholder="Ingrese el ID del cliente" required>
        <input type="hidden" name="action" value="buscarCliente">
        <input type="submit" value="Buscar Cliente" class="btn btn-primary">
    </div>
</form>
    
    <% 
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    if (cliente != null) {
    %>
         <p><strong>Cliente ID:</strong> <%= cliente.getId() %></p>
    <p><strong>Nombre:</strong> <%= cliente.getNombre() %></p>
    <p><strong>Apellido:</strong> <%= cliente.getApellido() %></p>
    <p><strong>DNI:</strong> <%= cliente.getDni() %></p>
<% } else if (request.getParameter("IDCliente") != null) { %>
    <p style="color: red;">No se encontró información del cliente.</p>
<% } %>

    <form method="post" action="ServletNuevaCuenta">
    <input type="hidden" name="IDCliente" value="<%= cliente != null ? cliente.getId() : "" %>">
    <input type="hidden" name="action" value="crearCuenta">

    <div class="mb-3">
        <label for="Monto" class="form-label">Monto Inicial</label>
        <input type="number" step="0.01" class="form-control" id="Monto" name="Monto" required>
    </div>

    <div class="mb-3">
    <label for="TipoCuenta" class="form-label">Tipo de Cuenta</label>
    <select class="form-control" id="TipoCuenta" name="TipoCuenta" required>
        <%
            ArrayList<CuentaTipo> tiposDeCuenta = (ArrayList<CuentaTipo>) request.getAttribute("tiposDeCuenta");
            if (tiposDeCuenta != null && !tiposDeCuenta.isEmpty()) {
                for (CuentaTipo tipo : tiposDeCuenta) {
        %>
                    <option value="<%= tipo.getId() %>"><%= tipo.getDescripcion() %></option>
        <%
                }
            } else {
        %>
                <option disabled>No hay tipos de cuenta disponibles.</option>
        <%
            }
        %>
        <% 
String mensajeExito = (String) request.getAttribute("mensajeExito");
String mensajeError = (String) request.getAttribute("mensajeError");
if (mensajeExito != null) { 
%>
    <div class="alert alert-success">
        <%= mensajeExito %>
    </div>
<% } else if (mensajeError != null) { %>
    <div class="alert alert-danger">
        <%= mensajeError %>
    </div>
<% } %>
    </select>
</div>

    <button type="submit" class="btn btn-primary">Crear Cuenta</button>
</form>
</div>

</body>
</html>