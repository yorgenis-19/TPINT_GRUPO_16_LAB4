<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.CuentaTipo"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="entidad.Usuario"%>

<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css" rel="stylesheet" />
    <style>
        /* Include external CSS */
        <jsp:include page="style.css"></jsp:include>
    </style>
</head>
<body>
<%
    Usuario usuario = new Usuario(); 
    if(session.getAttribute("UsuarioActual") != null) {
        usuario = (Usuario)session.getAttribute("UsuarioActual");
    }
%>

<nav class="navbar bg-primary navbar-expand-lg " data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="Administrador.jsp">
            <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20">
                <path fill="currentColor" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5" clip-rule="evenodd"/>
            </svg>
        </a>
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


<h1 class="text-center">Generar Reporte</h1>

<form action="ServletGenerarReporte" method="post" class="abm_form">
    <div class="abm_form-column">
        <label for="tipoReporte" class="form-label">Seleccione el tipo de informe:</label>
        <select id="tipoReporte" name="tipoReporte" class="form-select" required>
            <option value="saldoTotal">Saldo total por rango de fechas</option>
            <option value="cuentasPorTipo">Cuentas agrupadas por tipo</option>
        </select>
    </div>
    <div class="abm_form-column">
        <label for="fechaInicio" class="form-label">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" class="form-input" required>
    </div>
    <div class="abm_form-column">
        <label for="fechaFin" class="form-label">Fecha de Fin:</label>
        <input type="date" id="fechaFin" name="fechaFin" class="form-input" required>
    </div>
    <div class="abm_form-column">
        <button type="submit" name="btnReporte1" class="button button-primary">Generar Informe</button>
    </div>
        <div class="abm_form-column">
        <button type="submit" class="button button-primary" name="btnReporte2">Generar Informe2</button>
    </div>
</form>

<%
    String tipoReporte = (String) request.getAttribute("tipoReporte");
    if (tipoReporte != null) {
        if (tipoReporte.equals("saldoTotal")) {
%>
            <div class="report-container">
                <h2>Saldo Total de Cuentas</h2>
                <p>El saldo total de las cuentas entre las fechas seleccionadas es: $<%= request.getAttribute("saldoTotal") %></p>
            </div>
<%
        } else if (tipoReporte.equals("cuentasPorTipo")) {
        	Map<String, Object> cuentasPorTipo = (Map<String, Object>) request.getAttribute("cuentasPorTipo");
            if (cuentasPorTipo != null && !cuentasPorTipo.isEmpty()) {
%>
                <div class="report-container">
                    <h2>Cuentas agrupadas por tipo</h2>
                    <table id="tablaCuentas" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Tipo de Cuenta</th>
                             <th>Total de Cuentas</th>
                            <th>Cuentas Activas</th>
                            <th>Cuentas Inactivas</th>
                            <th>Porcentaje Activas</th>
                            <th>Porcentaje Inactivas</th>
                            
                        </tr>
                        </thead>
       					<tbody>
                        <%
                        for (Map.Entry<String, Object> entry : cuentasPorTipo.entrySet()) {
                            Map<String, Integer> cuentaData = (Map<String, Integer>) entry.getValue();
                            String descripcion = entry.getKey();
                            int totalCuentas = cuentaData.get("TotalCuentas");
                            int cuentasActivas = cuentaData.get("CuentasActivas");
                            int cuentasInactivas = cuentaData.get("CuentasInactivas");

                            double porcentajeActivas = (totalCuentas > 0) ? ((double) cuentasActivas / totalCuentas) * 100 : 0;
                            double porcentajeInactivas = (totalCuentas > 0) ? ((double) cuentasInactivas / totalCuentas) * 100 : 0;
                        %>
                            <tr>
                                <td><%= descripcion %></td>
                                <td><%= totalCuentas %></td>
                                <td><%= cuentasActivas %></td>
                                <td><%= cuentasInactivas %></td>
                                <td><%= String.format("%.2f", porcentajeActivas) + "%" %></td>
                                <td><%= String.format("%.2f", porcentajeInactivas) + "%" %></td>
                            </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
<%
            } else {
%>
                <p>No se encontró información para el tipo de informe seleccionado.</p>
<%
            }
        }
    }
%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    // Inicializar DataTables en la tabla
    $(document).ready(function() {
        $('#tablaCuentas').DataTable({
        order: [[0, 'desc']],
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros en total)",
            loadingRecords: "Cargando...",
            zeroRecords: "No se encontraron registros coincidentes",
            emptyTable: "No hay datos disponibles en la tabla",
            paginate: {
                first: "Primero",
                previous: "Anterior",
                next: "Siguiente",
                last: "Último"
            },
            aria: {
                sortAscending: ": activar para ordenar columna ascendente",
                sortDescending: ": activar para ordenar columna descendente"
            },
            lengthMenu: "Cantidad registros MENU",
            search: "Buscar:"
        },
        dom: 'lfrtip'
    });
   });
</script>
</body>
</html>

