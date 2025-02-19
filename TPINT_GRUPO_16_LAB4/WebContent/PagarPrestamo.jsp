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
    
    BigDecimal currentSaldo = (BigDecimal) request.getAttribute("Saldo");
    Cuenta cuentaSeleccionada = (Cuenta) request.getAttribute("cuentaSeleccionada");
    
    Integer nroCuenta = null;
    if (cuentaSeleccionada == null && cuentasList != null && !cuentasList.isEmpty()) {
        cuentaSeleccionada = cuentasList.get(0);
        nroCuenta = cuentaSeleccionada.getId();
    } else if (cuentaSeleccionada != null) {
        nroCuenta = cuentaSeleccionada.getId();
    }



 // Validación de parámetros necesarios
    if (prestamoList == null || cuotasList == null || cuentasList == null || nroCuenta == null || currentSaldo == null || cuentaSeleccionada == null) {
        System.out.println("=== INICIO DE LOGS DE VALIDACIÓN ===");
        System.out.println("prestamoList: " + (prestamoList == null ? "ES NULL" : "Contiene " + prestamoList.size() + " elementos"));
        System.out.println("cuotasList: " + (cuotasList == null ? "ES NULL" : "Contiene " + cuotasList.size() + " elementos"));
        System.out.println("cuentasList: " + (cuentasList == null ? "ES NULL" : "Contiene " + cuentasList.size() + " elementos"));
        System.out.println("nroCuenta: " + (nroCuenta == null ? "ES NULL" : nroCuenta));
        System.out.println("currentSaldo: " + (currentSaldo == null ? "ES NULL" : currentSaldo));
        System.out.println("cuentaSeleccionada: " + (cuentaSeleccionada == null ? "ES NULL" : cuentaSeleccionada));
        System.out.println("=== FIN DE LOGS DE VALIDACIÓN ===");

        StringBuilder mensajeError = new StringBuilder("Error en la carga de datos: ");
        if (prestamoList == null) mensajeError.append("Lista de préstamos vacía. ");
        if (cuotasList == null) mensajeError.append("Lista de cuotas vacía. ");
        if (cuentasList == null) mensajeError.append("Lista de cuentas vacía. ");
        if (nroCuenta == null) mensajeError.append("Número de cuenta no especificado. ");
        if (currentSaldo == null) mensajeError.append("Saldo no disponible. ");
        if (cuentaSeleccionada == null) mensajeError.append("No ha seleccionado una cuenta. ");
        

        request.setAttribute("error", mensajeError.toString());
        response.sendRedirect("error.jsp");
        return;
    }
    System.out.println("=== ULTIMO PARAMETRO ===" + cuentaSeleccionada.getCliente().getId());
    

   
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

   <!-- Sección de selectores -->
<section class="container mb-4">
    <div class="form-group">
        <label>SELECCIONAR CUENTA</label>
        <select class="form-control" id="select-cuenta" name="NroCuenta">
            <option value="-1">Seleccione una cuenta</option>
            <% if (!cuentasList.isEmpty()) {
                for(Cuenta p : cuentasList) { %>
                    <option value="<%=p.getId()%>" <%= Integer.valueOf(p.getId()).equals(nroCuenta) ? "selected" : "" %>>
                        CBU: <%=p.getCBU()%> (ID: <%=p.getId()%> - MONTO: $<%=p.getMonto() %>)
                    </option>
                <% }
            } %>
        </select>
    </div>

    <div class="form-group mt-3">
        <label>SELECCIONAR PRESTAMO</label>
        <select class="form-control" id="select-prestamo" name="idPrestamo">
            <option value="-1">Seleccione un prestamo</option>
            <% if (!prestamoList.isEmpty()) {
                for(Prestamo p : prestamoList) { %>
                    <option value="<%=p.getId()%>">
                        Codigo: <%=p.getId()%> - Monto: $<%=p.getImporteMensualAPagar()%>
                    </option>
                <% }
            } %>
        </select>
    </div>

    <form action="ServletCuota" method="GET">
        <input type="hidden" name="OP" value="VER_PRESTAMOS">
        <input type="hidden" id="hiddenNroCuenta" name="NroCuenta" value="">
        <input type="hidden" id="hiddenIdPrestamo" name="idPrestamo" value="">
        <button type="submit" class="btn btn-primary mt-3" onclick="return validarYEnviar()">
            Seleccionar Cuenta y Préstamo
        </button>
    </form>
</section>
	    

    <!-- Información de la cuenta -->
   
    <section class="container mb-4">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Cuenta Nro: <%=cuentaSeleccionada.getCBU()%></h5>
            <p class="card-text">Saldo disponible: $<%= String.format("%.2f", currentSaldo) %></p>
        </div>
    </div>
</section>
    

   <!-- Tabla de cuotas con visibilidad inicial basada en parámetros -->
<section class="container mb-5">
    <div class="table-responsive">
        <table class="table table-hover" id="tabla-cuotas" 
               style="display: <%= request.getParameter("idPrestamo") != null && !request.getParameter("idPrestamo").equals("-1") ? "table" : "none" %>;">
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
                <tr class="cuotasTr cuoPrestamo-<%=c.getPrestamoId()%>" 
                    style="display: <%= String.valueOf(c.getPrestamoId()).equals(request.getParameter("idPrestamo")) ? "table-row" : "none" %>">
                    <td><%=c.getNumeroCuota()%></td>
                    <td>$<%= String.format("%.2f", c.getMonto())%></td>
                    <td><%= c.getFechaPago() == null ? "-" : c.getFechaPago() %></td>
                    <td><%=c.getFechaVencimiento()%></td>
                    <td><%= c.getEstado() ? "Pendiente de pago" : "Pagado" %></td>
                    <td>
                        <% if(c.getEstado()) { %>
                        <button class="btn btn-success btn-sm"
                                onclick="cuotaSeleccionada(<%=c.getId()%>, <%=c.getMonto()%>, <%= cuentaSeleccionada.getCliente().getId() %>);"
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
        <input type="hidden" id="NroCuenta" name="NroCuenta">
        <input type="hidden" id="impCuota" name="impCuota">
        <input type="hidden" id="clienteId" name="clienteId">
        <input type="hidden" name="OPPAGARCUOTA" value="PAGAR">
    </form>
</div>
   <script>
function validarYEnviar() {
    const selectCuenta = document.getElementById("select-cuenta");
    const selectPrestamo = document.getElementById("select-prestamo");
    const hiddenNroCuenta = document.getElementById("hiddenNroCuenta");
    const hiddenIdPrestamo = document.getElementById("hiddenIdPrestamo");
    
    if (selectCuenta.value === "-1") {
        alert("Por favor, seleccione una cuenta válida");
        return false;
    }
    
    if (selectPrestamo.value === "-1") {
        alert("Por favor, seleccione un préstamo válido");
        return false;
    }
    
    // Actualizar los valores hidden antes de enviar
    hiddenNroCuenta.value = selectCuenta.value;
    hiddenIdPrestamo.value = selectPrestamo.value;
    
    return true;
}

function cuotaSeleccionada(idCuota, importe, clienteId) {
    const saldoDisponible = <%= currentSaldo %>;
    const cuentaSeleccionada = <%= nroCuenta %>; // Agregamos esta línea
    
    if (importe > saldoDisponible) {
        alert("No tiene saldo suficiente para realizar el pago.");
        return;
    }

    if(confirm("¿Está seguro que desea pagar la cuota por $" + importe + "?")) {
        document.getElementById("IdCuotaAPagar").value = idCuota;
        document.getElementById("NroCuenta").value = cuentaSeleccionada; // Usamos la cuenta seleccionada
        document.getElementById("impCuota").value = importe;
        document.getElementById("clienteId").value = clienteId;
        document.getElementById("formPago").submit();
    }
}

// Al cargar la página, seleccionar la cuenta actual
window.onload = function() {
    const selectCuenta = document.getElementById("select-cuenta");
    selectCuenta.value = <%= nroCuenta %>;
}
</script>
</body>
</html>