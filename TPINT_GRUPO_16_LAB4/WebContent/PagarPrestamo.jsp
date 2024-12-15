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

    <!-- Header -->
    <header class="header bg-info p-3">

		<div class="logged">
			<a class="navbar-brand" href="Cliente.jsp"> <svg
					xmlns="http://www.w3.org/2000/svg" width="48" height="48"
					viewBox="0 0 20 20">
					<path fill="white" fill-rule="evenodd"
						d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5"
						clip-rule="evenodd" /></svg>
			</a> <span class="mx-2 fw-bold"> <svg
					xmlns="http://www.w3.org/2000/svg" height="1em"
					viewBox="0 0 448 512">
                    <path
						d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" />
                </svg> <%= usuario != null ? usuario.getNombre() : "Usuario" %>
			</span> <a class="btn btn-link" href="gestionarCuentas.jsp"> <svg
					xmlns="http://www.w3.org/2000/svg" height="1em"
					viewBox="0 0 512 512">
                    <path
						d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z" />
                </svg> <span style="color: black; font-weight: bold;">Gestion</span>
			</a>
		</div>
	</header>

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