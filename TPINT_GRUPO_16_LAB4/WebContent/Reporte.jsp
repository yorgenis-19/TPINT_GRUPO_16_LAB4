<%@page import="entidad.ReporteClientesCuentas"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.CuentaTipo"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="entidad.Usuario"%>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
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
    
	String tipo = request.getParameter("tipoReporte") != null ? request.getParameter("tipoReporte") : "valoresTotales";
	LocalDate desde = request.getParameter("fechaInicio") != null ? LocalDate.parse(request.getParameter("fechaInicio")) : LocalDate.now();
	LocalDate hasta = request.getParameter("fechaFin") != null ? LocalDate.parse(request.getParameter("fechaFin")) : LocalDate.now();
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

    <form id="formReporte" action="ServletGenerarReporte" method="post" class="abm_form" style="align-items: center;">
        <div class="abm_form-column">
            <label for="tipoReporte" class="form-label">Seleccione el tipo de informe:</label>
            <select id="tipoReporte" name="tipoReporte" class="form-select" required style="width: 350px;">
                <option value="valoresTotales" <% if(tipo.equals("valoresTotales")){%>selected<%} %>>Valores totales</option>
                <option value="cuentasPorTipo" <% if(tipo.equals("cuentasPorTipo")){%>selected<%} %>>Cuentas agrupadas por tipo</option>
                <option value="reporteClientesCuentas" <% if(tipo.equals("reporteClientesCuentas")){%>selected<%} %>>Clientes Cuentas</option>
            </select>
        </div>
        <div class="abm_form-column">
            <label for="fechaInicio" class="form-label">Fecha de Inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" class="form-input" value=<%=desde %> required>
        </div>
        <div class="abm_form-column">
            <label for="fechaFin" class="form-label">Fecha de Fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" class="form-input" value=<%=hasta %> required>
        </div>
        <div class="abm_form-column" style="margin-top: 20px;">
            <button type="submit" name="btnGenerarReporte" class="button button-primary">Generar Informe</button>
        </div>
        <!-- 
        <div class="abm_form-column">
            <button type="submit" class="button button-primary" name="btnReporte2">Informe Detallado</button>
        </div>
         -->
    </form>

<%
    String tipoReporte = (String) request.getAttribute("tipoReporte");
    if (tipoReporte != null) {
        if (tipoReporte.equals("valoresTotales")) {
%>
            <div class="report-container">
                <h2>Totales</h2>
                <p>Cantidad de clientes: <%= ((Map<String, Object>)request.getAttribute("valoresTotales")).get("CantidadClientes") %></p>
                <p>Cantidad de cuentas: <%= ((Map<String, Object>)request.getAttribute("valoresTotales")).get("CantidadCuentas") %></p>
                <p>Saldo total de las cuentas: $<%= ((Map<String, Object>)request.getAttribute("valoresTotales")).get("SaldoTotalCuentas") %></p>
                <p>Saldo total de préstamos: $<%= ((Map<String, Object>)request.getAttribute("valoresTotales")).get("SaldoTotalPrestamos") %></p>
                <p>Saldo total de cuotas pagadas: $<%= ((Map<String, Object>)request.getAttribute("valoresTotales")).get("SaldoTotalCuotasPagas") %></p>
                <p>Saldo total de cuotas pendientes: $<%= ((Map<String, Object>)request.getAttribute("valoresTotales")).get("SaldoTotalCuotasPendientes") %></p>
            </div>
<%
        } else if (tipoReporte.equals("cuentasPorTipo")) {
        	Map<String, Object> cuentasPorTipo = (Map<String, Object>) request.getAttribute("cuentasPorTipo");
            if (cuentasPorTipo != null && !cuentasPorTipo.isEmpty()) {
            	
%>
                <div class="report-container" >
                    <h2>Cuentas agrupadas por tipo</h2>
                                     
                    <table id="tablaCuentas" class="table table-striped" style="max-width: 98%" >
                    <thead>
                        <tr>
                            <th>Tipo de Cuenta</th>
                            <th>Total de Cuentas</th>
                            <th>Cuentas Activas</th>
                            <th>Cuentas Inactivas</th>
                            <th>Préstamos Aprobados</th>
		                    <th>Préstamos Desaprobados</th>
		                    <th>Préstamos Pendientes</th>
                            
                        </tr>
                        </thead>
       					<tbody>
                        <%
                        int totalCuentasActivas = 0;
                        int totalCuentasInactivas = 0;
                        int totalPrestamosAprobados = 0;
                        int totalPrestamosDesaprobados = 0;
                        int totalPrestamosPendientes = 0;
                        
                        for (Map.Entry<String, Object> entry : cuentasPorTipo.entrySet()) {
                            Map<String, Integer> cuentaData = (Map<String, Integer>) entry.getValue();
                            String descripcion = entry.getKey();
                            int totalCuentas = cuentaData.get("TotalCuentas");
                            int cuentasActivas = cuentaData.get("CuentasActivas");
                            int cuentasInactivas = cuentaData.get("CuentasInactivas");
                            int prestamosAprobados = cuentaData.get("PrestamosAprobados");
                            int prestamosDesaprobados = cuentaData.get("PrestamosDesaprobados");
                            int prestamosPendientes = cuentaData.get("PrestamosPendientes");
                            
                            totalCuentasActivas += cuentasActivas;
                            totalCuentasInactivas += cuentasInactivas;
                            totalPrestamosAprobados += prestamosAprobados;
                            totalPrestamosDesaprobados += prestamosDesaprobados;
                            totalPrestamosPendientes += prestamosPendientes;


                        %>
                            <tr>
                                <td><%= descripcion %></td>
                                <td><%= totalCuentas %></td>
                                <td><%= cuentasActivas %></td>
                                <td><%= cuentasInactivas %></td>
                                <td><%= prestamosAprobados %></td>
		                        <td><%= prestamosDesaprobados %></td>
		                        <td><%= prestamosPendientes %></td>
                            </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    
                </div>
                <div class="grafico-container">
                <canvas id="graficoCuentas" class="grafico"></canvas>
                <canvas id="graficoPrestamos" class="grafico"></canvas>
                </div>
                
                <script>
                    // Datos para las cuentas activas e inactivas
                    const cuentasActivas = <%= totalCuentasActivas %>;
                    const cuentasInactivas = <%= totalCuentasInactivas %>;

                    // Datos para los préstamos
                    const prestamosAprobados = <%= totalPrestamosAprobados %>;
                    const prestamosDesaprobados = <%= totalPrestamosDesaprobados %>;
                    const prestamosPendientes = <%= totalPrestamosPendientes %>;

                    // Gráfico de Cuentas
                    const ctxCuentas = document.getElementById('graficoCuentas').getContext('2d');
                    new Chart(ctxCuentas, {
                        type: 'pie',
                        data: {
                            labels: ['Cuentas Activas', 'Cuentas Inactivas'],
                            datasets: [{
                                data: [cuentasActivas, cuentasInactivas],
                                backgroundColor: ['#4CAF50', '#F44336'],
                            }]
                        },
                        options: {
                            responsive: true,
                            plugins: {
                                legend: {
                                    position: 'top',
                                },
                                title: {
                                    display: true,
                                    text: 'Porcentaje de Cuentas Activas e Inactivas'
                                }
                            }
                        }
                    });

                    // Gráfico de Préstamos
                    const ctxPrestamos = document.getElementById('graficoPrestamos').getContext('2d');
                    new Chart(ctxPrestamos, {
                        type: 'bar',
                        data: {
                            labels: ['Aprobados', 'Desaprobados', 'Pendientes'],
                            datasets: [{
                                label: 'Préstamos',
                                data: [prestamosAprobados, prestamosDesaprobados, prestamosPendientes],
                                backgroundColor: ['#2196F3', '#FF9800', '#9C27B0'],
                            }]
                        },
                        options: {
                            responsive: true,
                            plugins: {
                                legend: {
                                    display: false,
                                },
                                title: {
                                    display: true,
                                    text: 'Préstamos por Estado'
                                }
                            },
                            scales: {
                                y: {
                                    beginAtZero: true, // Asegura que el eje Y comience en cero
                                    ticks: {
                                        stepSize: 1, // Esto controla la distancia entre los valores del eje Y
                                        precision: 0 // Asegura que los valores sean enteros
                                    }
                                }
                            }
                        }
                    });
                </script> 
<%
            } else {
%>
                <p>No se encontró información para el tipo de informe seleccionado.</p>
<%
            }
        } else if (tipoReporte.equals("reporteClientesCuentas")){
        	ArrayList<ReporteClientesCuentas> reporte = (ArrayList<ReporteClientesCuentas>)request.getAttribute("reporteClientesCuentas");
        	if (reporte != null && !reporte.isEmpty()) {%>
        		<table id="table_reporteClientesCuentas" class="table table-striped" style="max-width: 98%" >
					<thead>	
						<tr>
		        			<th>USUARIO</th>
		        			<th>APELLIDO</th>
		        			<th>NOMBRE</th>
		        			<th>DNI</th>
		        			<th>EMAIL</th>
		        			<th>SEXO</th>
		        			<th>DINERO TOTAL</th>
		        			<th>PRESTAMOS SOLICITADOS</th>
		        			<th>DINERO SOLICITADO</th>
		        			<th>DINERO CUOTAS PAGADAS</th>
						</tr>
					</thead>
					<tbody>
						<%
		        			for(ReporteClientesCuentas obj : reporte) {
						%>	
						<tr>
		        			<td><%=obj.getUsuario()%></td>
		        			<td><%=obj.getApellido()%></td>
		        			<td><%=obj.getNombre()%></td>
		        			<td><%=obj.getDni()%></td>
		        			<td><%=obj.getEmail()%></td>
		        			<td><%=obj.getSexo()%></td>
		        			<td><%=obj.getDineroTotal()%></td>
		        			<td><%=obj.getPrestamosSolicitados()%></td>
		        			<td><%=obj.getDineroSolicitado()%></td>
		        			<td><%=obj.getDineroCuotasPagadas()%></td>
						</tr>
		        		<%}%>
					</tbody>
				</table>
        	<%}
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
            lengthMenu: "Cantidad registros",
            search: "Buscar:"
        },
        dom: 'lfrtip'
    });
   });
</script>
<script>
    document.getElementById('formReporte').addEventListener('submit', function(event) {
        const fechaInicio = new Date(document.getElementById('fechaInicio').value);
        const fechaFin = new Date(document.getElementById('fechaFin').value);

        if (fechaFin < fechaInicio) {
            event.preventDefault(); // Prevenir el envío del formulario
            alert('La fecha de fin debe ser mayor o igual a la fecha de inicio.');
        }
    });
</script>
</body>
</html>

