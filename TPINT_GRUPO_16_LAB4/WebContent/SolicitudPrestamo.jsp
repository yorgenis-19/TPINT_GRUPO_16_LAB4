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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
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
    <header class="header bg-info p-3">
        <div class="logged">
            <a class="navbar-brand" href="Cliente.jsp">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20">
                    <path fill="white" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5"/>
                </svg>
            </a>
            <span class="mx-2 fw-bold">
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                    <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/>
                </svg>
                <%= usuario.getNombre() %>
            </span>
            <a class="btn btn-link" href="gestionarCuentas.jsp">
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                    <path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
                </svg>
                <span style="color: black; font-weight: bold;">Gestión</span>
            </a>
        </div>
    </header>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0 text-center">Solicitud de Préstamo</h4>
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
                                <label for="getCuenta">Cuenta para el depósito <span class="text-danger">*</span></label>
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
                                <button type="submit" class="btn btn-primary btn-lg" name="btnRealizarSolicitudPrestamo">
                                    Solicitar Préstamo
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
                        <small>Recibirás información sobre la solicitud en las próximas 48 horas hábiles.</small>
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
            mostrarError('Por favor, seleccione una cuenta para el depósito.');
            return false;
        }
        
        if (!monto || monto <= 0) {
            mostrarError('Por favor, ingrese un monto válido mayor a 0.');
            return false;
        }

        // Formatear el monto para mostrarlo con dos decimales
        const montoFormateado = new Intl.NumberFormat('es-AR', { 
            style: 'currency', 
            currency: 'ARS' 
        }).format(monto);
        
        return confirm(`¿Está seguro de solicitar el préstamo por ${montoFormateado}?`);
    }

    function mostrarError(mensaje) {
        alert(mensaje);
        return false;
    }
    </script>
</body>
</html>