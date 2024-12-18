<%@page import="entidad.Usuario"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.CuotaPrestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Pagar prestamo</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    
    <style>
		<jsp:include page="style.css"></jsp:include>   
 	</style>
</head>
<body>
    <%
    Usuario usuario = (Usuario) session.getAttribute("UsuarioActual");
    if(usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ArrayList<Prestamo> prestamoList = (ArrayList<Prestamo>) request.getAttribute("Prestamos");
    ArrayList<CuotaPrestamo> cuotasList = (ArrayList<CuotaPrestamo>) request.getAttribute("Cuotas");
    ArrayList<Cuenta> cuentasList = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentasDDL");
    Integer nroCuenta = (Integer) request.getAttribute("NroCuenta");
    BigDecimal currentSaldo = (BigDecimal) request.getAttribute("Saldo");

    // Validación de parámetros necesarios
    if (prestamoList == null || cuotasList == null || cuentasList == null || nroCuenta == null || currentSaldo == null) {
        request.setAttribute("error", "Error en la carga de datos. Por favor, intente nuevamente.");
        response.sendRedirect("error.jsp");
        return;
    }

    // Encontrar la cuenta actual
    Cuenta cuentaActual = null;
		for (Cuenta c : cuentasList) {
    		if (c.isActiva() && c.getId() == nroCuenta) {
        		cuentaActual = c;
        		break;
    	}
}

    if (cuentaActual == null) {
        response.sendRedirect("error.jsp");
        return;
    }
    %>

<header>
<nav class="navbar bg-success navbar-expand-lg " data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="Cliente.jsp">
    	<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20"><path fill="currentColor" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5" clip-rule="evenodd"/></svg>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Cliente.jsp">Inicio</a>
        </li>
      </ul>
      <form class="d-flex" role="search" method="post" action="ServletLogout">
      	<span class="username"><%=usuario.getNombre()%></span>
        <button class="btn btn-secondary" type="submit" name="btnLogout">Cerrar sesión</button>
      </form>
    </div>
  </div>
</nav>
</header>

 <div class="container page-container">
    <h1 class="mt-5 text-center mb-4">Pagar prestamos</h1>

    <!-- Selector de préstamos -->
    <section class="container mb-4">
        <div class="form-group">
            <label>SELECCIONAR PRESTAMO</label>
            <select class="form-control" id="select-prestamo">
                <option value="-1">Seleccione un prestamo</option>
                <% if (!prestamoList.isEmpty()) {
                    for(Prestamo p : prestamoList) { %>
                        <option value="<%=p.getId()%>">
                            Codigo: <%=p.getId()%> - Monto: $<%=p.getImporteMensualAPagar()%>
                        </option>
                    <% }
                } %>
            </select>
            <button id="btnElegirPrestamo" class="btn btn-primary mt-2" onclick="getCuotasPrestamo()">
                Elegir prestamo
            </button>
        </div>
    </section>

    <!-- Información de la cuenta -->
    <% if(cuentaActual.getTipo().getId() == 1) { %>
    <section class="container mb-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><%=cuentaActual.getTipo().getDescripcion()%> - Cuenta Nro: <%=cuentaActual.getId()%></h5>
                <p class="card-text">Saldo disponible: $<%= String.format("%.2f", currentSaldo) %></p>
            </div>
        </div>
    </section>
    <% } %>

    <!-- Tabla de cuotas -->
    <section class="container mb-5">
        <div class="table-responsive">
            <table class="table table-hover" id="tabla-cuotas" style="display: none;">
                <thead class="thead-dark">
                    <tr>
                        <th>Cuota</th>
                        <th>Importe</th>
                        <th>Fecha de pago</th>
                        <th>Fecha vencimiento</th>
                        <th>Estado</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(CuotaPrestamo c : cuotasList) { %>
                    <tr class="cuotasTr cuoPrestamo-<%=c.getPrestamoId()%>" style="display: none">
                        <td><%=c.getNumeroCuota()%></td>
                        <td>$<%= String.format("%.2f", c.getMonto())%></td>
                        <td><%= c.getFechaPago() == null ? "-" : c.getFechaPago() %></td>
                        <td><%=c.getFechaVencimiento()%></td>
                        <td><%= c.getEstado() ? "Pendiente de pago" : "Pagado" %></td>
                        <td>
                            <% if(c.getEstado()) { %>
                            <button class="btn btn-success btn-sm"
                                    onclick="cuotaSeleccionada(<%=c.getId()%>, <%=c.getMonto()%>, <%= cuentasList.get(0).getCliente().getId() %>);"
                                    <%= currentSaldo.compareTo(BigDecimal.valueOf(c.getMonto())) < 0 ? "disabled" : "" %>>
                                Pagar
                            </button>
                            <% } else { %>
                            <span class="badge badge-success">Completado</span>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </section>

    <!-- Formulario oculto para el pago -->
    <form id="formPago" method="post" action="ServletCuota" style="display: none;">
        <input type="hidden" id="IdCuotaAPagar" name="IdCuotaAPagar">
        <input type="hidden" id="NroCuenta" name="NroCuenta" value="<%= cuentaActual.getId() %>">
        <input type="hidden" id="impCuota" name="impCuota">
        <input type="hidden" id="clienteId" name="clienteId">
        <input type="hidden" name="OPPAGARCUOTA" value="PAGAR">
    </form>
</div>
    <script>
    function cuotaSeleccionada(idCuota, importe, clienteId) {
        const saldoDisponible = <%= currentSaldo %>;
        
        if (importe > saldoDisponible) {
            alert("No tiene saldo suficiente para realizar el pago.");
            return;
        }

        if(confirm("¿Está seguro que desea pagar la cuota por $" + importe + "?")) {
            document.getElementById("IdCuotaAPagar").value = idCuota;
            document.getElementById("impCuota").value = importe;
            document.getElementById("clienteId").value = clienteId;
            document.getElementById("formPago").submit();
        }
    }

    function getCuotasPrestamo() {
        const prestamoId = document.getElementById("select-prestamo").value;
        const tabla = document.getElementById("tabla-cuotas");
        const todasLasCuotas = document.querySelectorAll('.cuotasTr');
        
        // Ocultar todas las cuotas
        todasLasCuotas.forEach(cuota => cuota.style.display = 'none');
        
        if (prestamoId !== "-1") {
            // Mostrar cuotas del préstamo seleccionado
            document.querySelectorAll(".cuoPrestamo-" + prestamoId)
                .forEach(cuota => cuota.style.display = 'table-row');
            tabla.style.display = 'table';
        } else {
            tabla.style.display = 'none';
        }
    }
    </script>
</body>
</html>