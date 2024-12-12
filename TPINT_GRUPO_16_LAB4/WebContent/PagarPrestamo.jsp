<%@page import="entidad.Usuario"%>

<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.CuotaPrestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagar prestamo</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style type="text/css">
</style>
</head>
<body>
	<%
    // Depuración de sesión de usuario
    System.out.println("UsuarioActual en sesión: " + session.getAttribute("UsuarioActual"));
 

    Usuario usuario = new Usuario(); 
    if(session.getAttribute("UsuarioActual") != null)
    {
        usuario = (Usuario)session.getAttribute("UsuarioActual");
    }
    ArrayList<Prestamo> prestamoList = new ArrayList<>();
    if(request.getAttribute("Prestamos") != null)
    {
        prestamoList = (ArrayList<Prestamo>) request.getAttribute("Prestamos");
        System.out.println("Número de préstamos recibidos: " + prestamoList.size());
        // Imprimir detalles de los préstamos
        for(Prestamo prestamo : prestamoList) {
            System.out.println("Préstamo - ID: " + prestamo.getId() + 
                               ", Importe: " + prestamo.getImporteMensualAPagar() + 
                               ", Estado: " + prestamo.getIdEstadoPrestamo());
        }
    } else {
        System.out.println("No se recibieron préstamos en el atributo de request");
    }
%>
	<%
    // Validaciones de seguridad
    if(session.getAttribute("Usuario") != null) {
        usuario = (Usuario)session.getAttribute("Usuario");
    }

    // Inicializar listas
    ArrayList<Cuenta> cuentasList = new ArrayList<>();
    ArrayList<CuotaPrestamo> cuotasList = new ArrayList<>();

    // Recuperar prestamos
    if(request.getAttribute("Prestamos") != null) {
        prestamoList = (ArrayList<Prestamo>) request.getAttribute("Prestamos");
        System.out.println("Número de préstamos (segunda verificación): " + prestamoList.size());
    }

    // Recuperar cuentas
    if(request.getSession().getAttribute("cuentasDDL") != null) {
        cuentasList = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentasDDL");
        System.out.println("Número de cuentas: " + cuentasList.size());
        // Imprimir detalles de las cuentas
        for(Cuenta cuenta : cuentasList) {
            System.out.println("Cuenta - ID: " + cuenta.getId() + 
                               ", Tipo: " + cuenta.getTipo().getDescripcion() + 
                               ", Activa: " + cuenta.isActiva());
        }
    } else {
        System.out.println("No se encontraron cuentas en la sesión");
    }

    // Recuperar cuotas
    if(request.getAttribute("Cuotas") != null) {
        cuotasList = (ArrayList<CuotaPrestamo>) request.getAttribute("Cuotas");
        System.out.println("Número de cuotas: " + cuotasList.size());
        // Imprimir detalles de las cuotas
        for(CuotaPrestamo cuota : cuotasList) {
            System.out.println("Cuota - ID: " + cuota.getId() + 
                               ", Préstamo ID: " + cuota.getPrestamoId() + 
                               ", Número de Cuota: " + cuota.getNumeroCuota() + 
                               ", Monto: " + cuota.getMonto() + 
                               ", Estado: " + cuota.getEstado());
        }
    } else {
        System.out.println("No se recibieron cuotas en el atributo de request");
    }

    // Validar que existan cuentas
    int pos = 0;
    Cuenta cuentaSeleccionada = null;
    if(cuentasList != null && !cuentasList.isEmpty()) {
        // Encontrar primera cuenta activa
        for(int i = 0; i < cuentasList.size(); i++) {
            if(cuentasList.get(i).isActiva()) {
                pos = i;
                cuentaSeleccionada = cuentasList.get(i);
                break;
            }
        }
    }

    // Validar saldo
    BigDecimal currentSaldo = BigDecimal.ZERO;
    if(request.getAttribute("Saldo") != null) {
        currentSaldo = (BigDecimal) request.getAttribute("Saldo");
        System.out.println("Saldo actual: " + currentSaldo);
    } else {
        System.out.println("No se recibió saldo en el atributo de request");
    }
%>

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

	<h1 class="mt-5"
		style="margin: auto; text-align: center; margin-bottom: 30px;">Pagar
		prestamos</h1>

	<section class="eleccion-prestamos">
		<label>SELECCIONAR PRESTAMO</label> <select class="select"
			id="select-prestamo">
			<option value="-1">Seleccione un prestamo</option>
			<% 
            if (prestamoList != null && !prestamoList.isEmpty()) {
                for(Prestamo p : prestamoList) { 
            %>
			<option value="<%=p.getId()%>">Codigo:
				<%=p.getId()%> - Monto: $<%=p.getImporteMensualAPagar()%></option>
			<% 
                } 
            } else { 
            %>
			<option value="-1">No hay préstamos disponibles</option>
			<% } %>
		</select>
		<button id="btnElegirPrestamo" class="btnSeleccionar"
			onclick="getCuotasPretamo()">Elegir prestamo</button>
	</section>

	<% if (cuentaSeleccionada != null) { %>
	<section class="Cuenta" style="margin-bottom: 30px;">
		<div class="Cuenta-Tipo">
			<label id="lblCuentaTipo"> <%= cuentaSeleccionada.getTipo().getId() == 1 ? "CA$" : "CC$" %>
			</label>
		</div>
		<div class="Cuenta-Detalle">
			<label>$<%= String.format("%.2f", currentSaldo) %></label> <label
				id="lblDetalleCuenta"> <%= cuentaSeleccionada.getTipo().getDescripcion() %>
				- Cuenta Nro: <%= cuentaSeleccionada.getId() %>
			</label>
		</div>
	</section>
	<% } %>

	<section class="detalle-cuota mb-5">
		<table
			class="table text-dark shadow-lg p-3 mb-5 bg-body-tertiary rounded"
			id="tabla-cuotas"
			style="display: none; text-align: center; width: 80%; margin: auto;">
			<tr class="" style="text-align: center;">
				<th>Cuota</th>
				<th>Importe</th>
				<th>Fecha de pago</th>
				<th>Fecha vencimiento</th>
				<th>Estado</th>
				<th>Pagar</th>
			</tr>
			<% 
            if (cuotasList != null && !cuotasList.isEmpty()) {
                for(CuotaPrestamo c : cuotasList){ 
            %>
			<tr class="cuotasTr cuoPrestamo-<%=c.getPrestamoId()%>"
				style="display: none">
				<td><%=c.getNumeroCuota()%></td>
				<td>$<%= String.format("%.2f", c.getMonto())%></td>
				<td><%= c.getFechaPago() == null ? "-" : c.getFechaPago() %></td>
				<td><%=c.getFechaVencimiento() %></td>
				<td><%= c.getEstado() ? "Pendiente de pago" : "Pago" %></td>
				<td>
					<button class="btn btn-outline-success fw-bold"
						onclick="cuotaSeleccionada(<%=c.getId()%>, <%=c.getMonto()%>);">Pagar</button>
				</td>
			</tr>
			<% 
                } 
            } 
            %>
		</table>
	</section>

	<form method="post" action="ServletCuota" hidden>
		<input type="text" hidden id="IdCuotaAPagar" name="IdCuotaAPagar">
		<input type="text" hidden id="NroCuenta" name="NroCuenta"
			value="<%= cuentaSeleccionada != null ? cuentaSeleccionada.getId() : "" %>">
		<input type="text" hidden id="impCuot" name="impCuota"> <input
			type="text" name="OPPAGARCUOTA">
	</form>

	<script>
    function cuotaSeleccionada(idCuota, importe){
        var requestID = document.getElementById("IdCuotaAPagar");
        var requestImporte = document.getElementById("impCuot");
        requestID.value = idCuota;
        requestImporte.value = importe;

        <% if (cuentaSeleccionada != null) { %>
        if (importe > <%= currentSaldo %>) {
            alert("No tiene saldo suficiente para realizar el pago.");
            return;
        }
        <% } %>

        if(confirm("Presione aceptar para confirmar el pago de la cuota...")){
            document.forms[0].submit();
        } else {
            alert("Pago cancelado");
        }
    }

    var tabla = document.getElementById("tabla-cuotas");
    var prestamoSeleccionado = document.getElementById("select-prestamo");
        
    function getCuotasPretamo(){
        var btnElegirPrestamo = document.getElementById("btnElegirPrestamo");
        var CodPrestamo = prestamoSeleccionado.options[prestamoSeleccionado.selectedIndex].value;
        
        var allCuo = document.querySelectorAll('.cuotasTr');
        OcultarCuotas(allCuo);
        
        if (CodPrestamo != -1){
            var cuotasPrestamoSeleccionado = document.querySelectorAll(".cuoPrestamo-"+CodPrestamo);
            MostrarCuotas(cuotasPrestamoSeleccionado);
            MostrarTabla();
        } else {
            OcultarTabla();
        }
    }

    function OcultarCuotas(allCuo){
        for (var cuo of allCuo){
            cuo.style.display = 'none';
        }   
    }
    
    function MostrarCuotas(allCuo){
        for (var cuo of allCuo){
            cuo.style.display = "";
        }   
    }
    
    function OcultarTabla(){
        tabla.style.display = 'none';
    }
    
    function MostrarTabla(){
        tabla.style.display = '';
    }
    </script>
</body>
</html>