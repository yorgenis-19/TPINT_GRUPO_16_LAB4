<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Usuario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Solicitar prestamo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
 	
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

    ArrayList<Cuenta> cuentasList = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentasDDL");
    if(cuentasList == null || cuentasList.isEmpty()) {
        request.setAttribute("error", "No se encontraron cuentas disponibles.");
        response.sendRedirect("error.jsp");
        return;
    }
    %>

    <!-- Header -->
    
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
        <button class="btn btn-secondary" type="submit" name="btnLogout">Cerrar sesi�n</button>
      </form>
    </div>
  </div>
</nav>
</header>

 <div class="container page-container">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0 text-center">Solicitud de Pr�stamo</h4>
                    </div>
                    <div class="card-body">
                        <form id="formPrestamo" method="get" action="ServletPrestamosxAutorizar" onsubmit="return validarFormulario()">
                            <div class="form-group">
                                <label for="txtMonto">Monto solicitado <span class="text-danger">*</span></label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">$</span>
                                    </div>
                                    <input type="number" 
                                           class="form-control" 
                                           id="txtMonto" 
                                           name="txtMonto" 
                                           step="0.01" 
                                           required 
                                           placeholder="Ingrese el monto">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="getCuenta">Cuenta para el dep�sito <span class="text-danger">*</span></label>
                                <select class="form-control" id="getCuenta" name="getCuenta" required>
                                    <option value="">Seleccione una cuenta</option>
                                    <% for(Cuenta cuenta : cuentasList) { %>
                                        <option value="<%= cuenta.getId() %>">
                                            CBU: <%= cuenta.getCBU() %>
                                        </option>
                                    <% } %>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="txtCuotas">Cantidad de cuotas <span class="text-danger">*</span></label>
                                <select class="form-control" id="txtCuotas" name="txtCuotas" required>
                                    <option value="3">3 cuotas</option>
                                    <option value="6">6 cuotas</option>
                                    <option value="12">12 cuotas</option>
                                    <option value="24">24 cuotas</option>
                                    <option value="36">36 cuotas</option>
                                    <option value="48">48 cuotas</option>
                                    <option value="60">60 cuotas</option>
                                </select>
                            </div>

                            <input type="hidden" name="getCliente" value="<%= cuentasList.get(0).getCliente().getId() %>">

                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-success btn-lg" name="btnRealizarSolicitudPrestamo">
                                    Solicitar Pr�stamo
                                </button>
                            </div>
                        </form>

                        <% if(request.getAttribute("resString") != null) { %>
                            <div class="alert <%= request.getAttribute("resBoolean") != null && (Boolean)request.getAttribute("resBoolean") ? "alert-success" : "alert-danger" %> mt-3">
                                <%= request.getAttribute("resString") %>
                            </div>
                        <% } %>
                    </div>
                    <div class="card-footer text-center text-muted">
                        <small>Recibir�s informaci�n sobre la solicitud en las pr�ximas 48 horas h�biles.</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <script>
    function validarFormulario() {
        const monto = document.getElementById('txtMonto').value;
        const cuenta = document.getElementById('getCuenta').value;
        
        if (!cuenta) {
            mostrarError('Por favor, seleccione una cuenta para el dep�sito.');
            return false;
        }
        
        if (!monto || monto <= 0) {
            mostrarError('Por favor, ingrese un monto v�lido mayor a 0.');
            return false;
        }

        // Formatear el monto para mostrarlo con dos decimales
        const montoFormateado = new Intl.NumberFormat('es-AR', { 
            style: 'currency', 
            currency: 'ARS' 
        }).format(monto);
        
        return confirm(`�Est� seguro de solicitar el pr�stamo?`);
    }

    function mostrarError(mensaje) {
        alert(mensaje);
        return false;
    }
    </script>
</body>
</html>