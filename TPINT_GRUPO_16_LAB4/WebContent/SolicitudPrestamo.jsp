<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Solicitar prestamo</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
<%
Usuario usuario = new Usuario(); 
if(session.getAttribute("UsuarioActual") != null)
{
	usuario = (Usuario)session.getAttribute("UsuarioActual");
}
%>

	<header class="header bg-info p-3" > 
		
		
	
		<div class="logged ">
				<a href="#">
				   <a class="navbar-brand" href="Cliente.jsp">
    		<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20"><path fill="white" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5" clip-rule="evenodd"/></svg>
    		</a>
			<span class="mx-2 fw-bold">
			<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
			<path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/>
			
			
			<a class="btn btn-link" href="gestionarCuentas.jsp"> 
			<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
			<path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
			</svg>  <span style="color: black; font-weight: bold;"><%=usuario.getNombre()%></span></a>
		
		</div>
	</header>

<br>


<div class="container mt-2">
    <div class="row">
		
			<div class="col-md-3 d-flex flex-column align-items-center border border-dark" style="background-color: #D1F2EB; background-image: url('img/prestamos3.jpg'); background-size: cover; background-position: center;">
			</div>
					
		
        <div class="col-md-8" style="background-color: #34495E;">	
	 <form class="form border border-dark rounded p-4" style="background-color: #AED6F1;" method="get" action="ServletPrestamosxAutorizar" onsubmit="return validarFormulario()">
    <h1 class="text-center mb-4">Nuevo préstamo</h1>

    <fieldset>
        <legend>Solicitud de préstamo para cuenta <%=usuario.getId()%></legend>

        <div class="mb-3">
            <label for="txtMonto" class="form-label">Monto solicitado</label>
            <input id="txtMonto" type="number" step="0.01" required name="txtMonto" class="form-control" placeholder="$..">
        </div>

        <div class="mb-3">
            <label for="txtCuotas" class="form-label">Cant. De cuotas</label>
            <select name="txtCuotas" id="txtCuotas" class="form-select">
                <option value="3">3</option>
                <option value="6">6</option>
                <option value="9">9</option>
                <option value="12">12</option>
                <option value="18">18</option>
                <option value="24">24</option>
                <option value="48">48</option>
                <option value="60">60</option>
                <option value="72">72</option>
            </select>
        </div>

        <input type="hidden" name="getCuenta" value=<%=usuario.getId()%>>
        <input type="hidden" name="getCliente" value=<%=usuario.getId()%>>

        <div class="mb-3">
            <button type="submit" class="btn btn-primary" name="btnRealizarSolicitudPrestamo">Solicitar Préstamo</button>
        </div>
    </fieldset>

    <!-- Agregar mensajes de respuesta -->
    <c:if test="${not empty resString}">
        <div class="alert ${resBoolean ? 'alert-success' : 'alert-danger'} mt-3">
            ${resString}
        </div>
    </c:if>
</form>


            <h4 class="text-center mb-4" style="font-family: 'Your Preferred Font'; color: #3498db;">
   				Recibirás información sobre la solicitud en las próximas 48 horas.
			</h4>
    </div>
</div>
<script>
function validarFormulario() {
    const monto = document.getElementById('txtMonto').value;
    const cuenta = document.querySelector('input[name="getCuenta"]').value;
    
    if (!cuenta) {
        alert('Error: No se ha especificado una cuenta.');
        return false;
    }
    
    if (!monto || monto <= 0) {
        alert('Por favor, ingrese un monto válido mayor a 0.');
        return false;
    }
    
    return confirm('¿Está seguro de solicitar el préstamo por $' + monto + '?');
}
</script>


</body>
</html>