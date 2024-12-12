<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Usuario"%>
<%
if(request.getAttribute("Prestamos") == null) {
    response.sendRedirect("ServletPrestamosxAutorizar?getPrestamos");
    return;
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
<style>
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

    Usuario usuario = (Usuario) session.getAttribute("Usuario");
	%>

<header style="padding: 25px;">
    <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarExample01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item active" style="background-color: highlight;">
                        <a class="nav-link" href="/TPINT_GRUPO_16_LAB4/Administrador.jsp">
                            <i class="fas fa-arrow-left"></i> Menu Principal
                        </a>
                    </li>
                </ul>
                <div class="alert alert-info ml-auto">
                    <i class="fas fa-user"></i>
                    <span>User</span>
                </div>
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
        			<td><%=p.getCuentaId()%></td>  <!-- Número de cuenta -->
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