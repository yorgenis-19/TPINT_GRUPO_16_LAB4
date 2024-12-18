<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Usuario"%>
<%
if(request.getAttribute("Prestamos") == null) {
    response.sendRedirect("ServletPrestamosxAutorizar?getPrestamos");
    return;
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
<style>
	<jsp:include page="style.css"></jsp:include>
.dataTables_filter input {
    padding: 5px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.dataTables_length,
.dataTables_filter {
    display: inline-block;
    margin-bottom: 10px;
    margin-right: 5px;
}

.dataTables_length select,
.dataTables_filter input[type="search"] {
    padding: 6px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.dataTables_info {
    display: none;
}

.dataTables_paginate {
    margin-top: 15px;
}

.dataTables_paginate a {
    display: inline-block;
    padding: 5px 10px;
    margin-right: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
    text-decoration: none;
    color: #333;
}

.dataTables_paginate a.current {
    background-color: #007bff;
    color: #fff;
    border: 1px solid #007bff;
}

#miTabla_info, #miTabla_paginate {
    display: none;
}
</style>
<title>Administrar Prestamos - Admin</title>
</head>
<body>
	<% 
    ArrayList<Prestamo> listaPrestamos = null;
    if(request.getAttribute("Prestamos") != null) 
        listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("Prestamos");

    String resString = (String) request.getAttribute("resString");
    Boolean solicitado = (Boolean) request.getAttribute("resBoolean");

    Usuario usuario = (Usuario) session.getAttribute("UsuarioActual");
	%>
<header>
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
        <button class="btn btn-secondary" type="submit" name="btnLogout">Cerrar sesion</button>
      </form>
    </div>
   </div>
</nav>
    
</header>



<div class="container col-8 mt-5 pt-5">
    <br><br>
    <h2 style="color: #007bff; font-family: 'Arial', sans-serif; text-align: center;">Autorizacion de Prestamos</h2>
	<div>Cantidad de prestamos: <%= listaPrestamos != null ? listaPrestamos.size() : "Lista nula" %></div>
    <table id="miTabla" class="table table-striped table-hover text-center p-3">
        <caption>Prestamos disponibles para aprobacion</caption>
        <thead class="thead-dark">
            <tr>
                <th>Codigo de prestamo pendiente</th>
                <th>Numero de cuenta</th>
                <th>Importe Solicitado</th>
                <th>Cuotas</th>
                <th>Fecha Alta</th>
                <th>Estado</th>
                <th colspan="2">Autorizacion</th>
            </tr>
        </thead>
        <tbody>
        <% 
        if(listaPrestamos != null) {
            for(Prestamo p : listaPrestamos) { 
        %>
            <tr>
                <form action="ServletPrestamosxAutorizar" method="GET">
                    <td><%=p.getId()%></td>  <!-- Código de préstamo pendiente -->
        			<td><%=p.getCbu()%></td>  <!-- Número de cuenta -->
        			<td><%=p.getMontoSolicitado()%></td>  <!-- Importe solicitado -->
        			<td><%=p.getCantidadCuotas()%></td>  <!-- Cuotas -->
        			<td><%=p.getFechaAlta()%></td>  <!-- Fecha alta -->
                    <td>
                    <% 
                    switch(p.getIdEstadoPrestamo()) {
                        case 0: %>
                            Desaprobado
                        <% break;
                        case 1: %>
                            Pendiente de aprobacion
                        <% break;
                        case 2: %>
                            Aprobado
                        <% break;
                        case 4: %> 
                        Desaprobado
                    <% break;
                        default: %>
                            Error
                        <% break;
                    } %>
                    </td>
                    <td>
                        <button type="submit" name="btnAutorizar" class="btn btn-success mr-3 mx-4">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
                                <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
                            </svg>
                        </button>
                        <button type="submit" name="btnRechazar" class="btn btn-danger">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                            </svg>
                        </button>
                    </td>
                    <input type="hidden" name="codPrestamo" value="<%=p.getId()%>">
                </form>
            </tr>
        <% } } %>
        </tbody>
    </table>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#miTabla').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            },
            "columnDefs": [
                {
                    "targets": [0, 1, 2, 3, 4, 5, 6],
                    "searchable": true, // Permite la búsqueda en estas columnas
                    "orderable": false // Evita que se pueda ordenar por estas columnas si no es necesario
                }
            ]
        });
    });
</script>
</body>
</html>