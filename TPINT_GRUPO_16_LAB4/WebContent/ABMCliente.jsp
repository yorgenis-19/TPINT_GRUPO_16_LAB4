<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- Archivo CSS externo -->
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<%
Usuario usuario = new Usuario(); 
if(session.getAttribute("UsuarioActual") != null)
{
	usuario = (Usuario)session.getAttribute("UsuarioActual");
}
%>
<nav class="navbar bg-primary navbar-expand-lg " data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="Administrador.jsp">
    	<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20"><path fill="currentColor" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5" clip-rule="evenodd"/></svg>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
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
<div class="container page-container">
        <h1 style="text-align: center;">Cliente</h1>
        <div class="header_form">
			<form method="post" action="ServletCliente" class="container">
			
			<div class="row">
			  <div class="col-sm">
    			<label for="txtNombre" class="col-sm-2 col-form-label">Nombre</label>
			    <input type="text" class="form-control" placeholder="Nombre" aria-label="Nombre" id="txtNombre" name="txtNombre">
			  </div>
			  <div class="col-sm">
    			<label for="txtApellido" class="col-sm-2 col-form-label">Apellido</label>
			    <input type="text" class="form-control" placeholder="Apellido" aria-label="Apellido"  id="txtApellido" name="txtApellido">
			  </div>
			</div>
			<div class="row">
			  <div class="col-sm">
    			<label for="cmbSexo" class="col-sm-2 col-form-label">Sexo</label>
			    <select class="form-select" id="cmbSexo" name="cmbSexo">
				    <option value="1" selected>Masculino</option>
				    <option value="2">Femenino</option>
			  	</select>				
			  </div>
			  <div class="col-sm">
    			<label for="txtDNI" class="col-sm-2 col-form-label">D.N.I.</label>
			    <input type="text" class="form-control" placeholder="D.N.I." aria-label="D.N.I." name="txtDNI" id="txtDNI">
			  </div>
			</div>
			
			<div class="row">
			  <div class="col-sm">
    			<label for="txtCUIL" class="col-sm-2 col-form-label">C.U.I.L.</label>
			    <input type="text" class="form-control" placeholder="C.U.I.L." aria-label="C.U.I.L." name="txtCUIL" id="txtCUIL">
			  </div>
			  <div class="col-sm">
    			<label for="txtTelefono" class="col-sm-2 col-form-label">Teléfono</label>
			    <input type="text" class="form-control" placeholder="Telefono" aria-label="Telefono"  name="txtTelefono" id="txtTelefono">
			  </div>
			</div>
			
			<div class="row">
			  <div class="col-sm">
    			<label for="txtEmail" class="col-sm-2 col-form-label">Email</label>
			    <input type="text" class="form-control" placeholder="Email" aria-label="Email" name="txtEmail" id="txtEmail">
			  </div>
			  <div class="col-sm">
    			<label for="txtFechaNacimiento" class="col-sm col-form-label">Fecha de Nacimiento</label>
			    <input type="text" class="form-control" placeholder="Fecha de Nacimiento" aria-label="Fecha de Nacimiento"  name="txtFechaNacimiento" id="txtFechaNacimiento">
			  </div>
			</div>
			<!-- 
				<div class="abm_form-column">
					<label for="txtNombre">NOMBRE: </label>
					<input type="text" name="txtNombre" id="txtNombre"/>
					<label for="txtApellido">APELLIDO: </label>
					<input type="text" name="txtApellido" id="txtApellido"/>
					<label for="cmbSexo">SEXO: </label>
					<select name="cmbSexo" id="cmbSexo">
						<option value="1">MASCULINO</option>
						<option value="2">FEMENINO</option>
					</select>
					<label for="txtDNI">D.N.I.: </label>
					<input type="text" name="txtDNI" id="txtDNI"/>
					<label for="txtCUIL">C.U.I.L.: </label>
					<input type="text" name="txtCUIL" id="txtCUIL"/>
					<label for="txtTelefono">TELÉFONO: </label>
					<input type="text" name="txtTelefono" id="txtTelefono"/>
				</div>
				<div class="abm_form-column">
					<label for="txtEmail">EMAIL: </label>
					<input type="email" name="txtEmail" id="txtEmail"/>
					<label for="txtFechaNacimiento">FECHA DE NACIMIENTO: </label>
					<input type="date" name="txtFechaNacimiento" id="txtFechaNacimiento"/>
					<label for="txtDireccion">DIRECCIÓN: </label>
					<input type="text" name="txtDireccion" id="txtDireccion"/>
					<label for="cmbLocalidad">LOCALIDAD: </label>
					<select name="cmbLocalidad" id="cmbLocalidad">
						<option value="1">Localidad_1</option>
						<option value="2">Localidad_2</option>
					</select>
					<label for="cmbProvincia">PROVINCIA: </label>
					<select name="cmbProvincia" id="cmbProvincia">
						<option value="1">Provincia_1</option>
						<option value="2">Provincia_2</option>
					</select>
				</div>
				-->
			</form>
        </div>
       	<div class="button-wrapper">
               <form action="" method="get">
                   <button type="submit" class="button">
                       GUARDAR
                   </button>
                   <a class="btn btn-secondary" href="BuscadorCliente.jsp">
                       VOLVER
                   </a>
               </form>
           </div>
</div>
</body>
</html>
