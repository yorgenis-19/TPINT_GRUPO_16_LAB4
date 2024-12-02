<%@page import="entidad.Localidad"%>
<%@page import="negocioImpl.LocalidadNegocioImpl"%>
<%@page import="negocioImpl.ProvinciaNegocioImpl"%>
<%@page import="entidad.Provincia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocioImpl.UsuarioTipoNegocioImpl"%>
<%@page import="negocio.UsuarioTipoNegocio"%>
<%@page import="entidad.UsuarioTipo"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- Archivo CSS externo -->
<link rel="stylesheet" type="text/css" href="style.css">
<style>
	<jsp:include page="style.css"></jsp:include>
	.button-row{
		  margin: 12px 0;
	}
</style>
</head>
<body>
<script type="text/javascript">

function eventoSeleccionarProvincia() {
		var provinciaId = document.getElementById("cmbProvincia").value;
		var nombre = document.getElementById("txtNombre").value;
		var apellido = document.getElementById("txtApellido").value;
		//var usuario = document.getElementById("txtUsuario").value;
		var sexo = document.getElementById("cmbSexo").value;
		var dni = document.getElementById("txtDNI").value;
		//var clave = document.getElementById("txtClave").value;
		var cuil = document.getElementById("txtCUIL").value;
		var telefono = document.getElementById("txtTelefono").value;
		//var claveConfirmar = document.getElementById("txtClaveConfirmar").value;txtEmail
		var email = document.getElementById("txtEmail").value;
		var fechaNacimiento = document.getElementById("txtFechaNacimiento").value;
		var direccion = document.getElementById("txtDireccion").value;
		var id = document.getElementById("txtId").value;
		 window.location.replace("ServletProvincia?cargaLocalidad="+provinciaId
				 +"&txtNombre="+nombre+"&txtApellido="+apellido+
				 "&cmbSexo="+sexo+"&txtDNI="+dni+"&txtCUIL="+cuil+
				 "&txtTelefono="+telefono+"&txtEmail="+email+
				 "&txtFechaNacimiento="+fechaNacimiento+"&txtDireccion="+direccion+"&txtId="+id);
	  }
	  
</script>

<%
	Usuario usuario = new Usuario(); 
	if(session.getAttribute("UsuarioActual") != null)
	{
		usuario = (Usuario)session.getAttribute("UsuarioActual");
	}
	Cliente cliente = new Cliente();
	if(request.getAttribute("ClienteActual") != null){
		cliente = (Cliente)request.getAttribute("ClienteActual");
	}

	boolean errorEmail = request.getAttribute("MAIL_EXISTENTE") != null ? (boolean)request.getAttribute("MAIL_EXISTENTE") : false;
	boolean errorDni = request.getAttribute("DNI_EXISTENTE") != null ? (boolean)request.getAttribute("DNI_EXISTENTE") : false;
	boolean errorCuil = request.getAttribute("CUIL_EXISTENTE") != null ? (boolean)request.getAttribute("CUIL_EXISTENTE") : false;
	boolean errorUsuario = request.getAttribute("USUARIO_EXISTENTE") != null ? (boolean)request.getAttribute("USUARIO_EXISTENTE") : false;
	boolean errorClave = request.getAttribute("CLAVE_DISTINTA") != null ? (boolean)request.getAttribute("CLAVE_DISTINTA") : false;
	
	int filas = request.getAttribute("Filas") != null ? (int)request.getAttribute("Filas") : 0;
	
	ArrayList<Provincia> provincias = (ArrayList<Provincia>)request.getAttribute("Provincias");
	ArrayList<Localidad> localidades = (ArrayList<Localidad>)request.getAttribute("Localidades");
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
		<div class="title" >
			<div style="width: 240px;">
				<div class="alert alert-success <%if(filas > 0){%> visible <%}else{%> invisible <%}%>" role="alert">
				  Registro guardado con éxito!
				</div>
			</div>
			<div>
	        	<h1 style="text-align: center;">Clientes</h1>
	        </div>
			<div style="width: 240px;">
			 	
	        </div>
		</div>
        <div class="header_form">
			<form method="post" action="ServletGuardarCliente" class="container needs-validation">
			
		    <input type="hidden" class="form-control" value="<%=cliente.getId()%>" id="txtId" name="txtId">
				    
			<div class="row">
			  <div class="col-sm">
    			<label for="txtNombre" class="col-sm-2 col-form-label">Nombre:</label>
   			    <div class="input-group has-validation">
				    <input type="text" class="form-control" value="<%=cliente.getNombre() %>" placeholder="Nombre" aria-label="Nombre" id="txtNombre" name="txtNombre" required>
			    </div>
			  </div>
			  <div class="col-sm">
    			<label for="txtApellido" class="col-sm-2 col-form-label">Apellido:</label>
			    <input type="text" class="form-control"value="<%=cliente.getApellido() %>"  placeholder="Apellido" aria-label="Apellido"  id="txtApellido" name="txtApellido" required>
			  </div>
			  
			  
				<div class="col-sm">
				  <label for="txtDireccion" class="col-sm-2 col-form-label">Dirección:</label>
				  <input type="text" class="form-control" value="<%=cliente.getDireccion() %>" placeholder="Calle y número" aria-label="Direccion" name="txtDireccion" id="txtDireccion" required>
				</div>
			</div>
			
			<div class="row">
			  <div class="col-sm">
    			<label for="cmbSexo" class="col-sm-2 col-form-label">Sexo:</label>
			    <select class="form-select" value="<%=cliente.getSexo() %>" id="cmbSexo" name="cmbSexo" required>
				    <option value="Masculino" <%if(cliente.getId() == 0 || cliente.getSexo().equals("Masculino")){%> selected <%}%>>Masculino</option>
				    <option value="Femenino" <%if(cliente.getId() != 0 && cliente.getSexo().equals("Femenino")){%> selected <%}%>>Femenino</option>
			  	</select>
			  </div>
			  <div class="col-sm">
    			<label for="txtDNI" class="col-sm-2 col-form-label">D.N.I.:</label>
			    <input type="text" class="form-control"value="<%=cliente.getDni() %>"  placeholder="D.N.I." aria-label="D.N.I." name="txtDNI" id="txtDNI" required>
			    <%if(errorDni) {%>
	    	    <div class="invalid-feedback" style="display:block;">
			      El D.N.I. ingresado ya existe.
			    </div>
			    <%}%>
			  </div>
			  
			  
				<div class="col-sm">
					<label for="cmbProvincia" class="col-sm col-form-label">Provincia:</label>
			  		<select class="form-select" value="<%=cliente.getProvinciaId() %>" id="cmbProvincia" name="cmbProvincia" required onchange="eventoSeleccionarProvincia()">
			  		<%for(Provincia provincia : provincias){ %>
					    <option value="<%=provincia.getId()%>" 
					    <%if(cliente.getProvinciaId() == provincia.getId()) {
					    %>selected 
					    <%}%>><%=provincia.getNombre()%></option>
					<%}%>
				  	</select>
				</div>
			</div>
			
			<div class="row">
			  <div class="col-sm">
			    <label for="txtCUIL" class="col-sm-2 col-form-label">C.U.I.L.:</label>
			    <input type="text" class="form-control" value="<%=cliente.getCuil() %>" placeholder="C.U.I.L." aria-label="C.U.I.L." name="txtCUIL" id="txtCUIL" required>
			  </div>
			  <div class="col-sm">
			    <label for="txtTelefono" class="col-sm-2 col-form-label">Teléfono:</label>
			    <input type="text" class="form-control" value="<%=cliente.getTelefono() %>" placeholder="Telefono" aria-label="Telefono"  name="txtTelefono" id="txtTelefono" required>
			  </div>
				
			  
			    <div class="col-sm">
					<label for="cmbLocalidad" class="col-sm col-form-label">Localidad:</label>
			  		<select class="form-select" value="<%=cliente.getLocalidadId() %>" id="cmbLocalidad" name="cmbLocalidad" required>
			  		<%for(Localidad localidad : localidades){ %>
			  			
					    <option value="<%=localidad.getId() %>"
					    <%if(cliente.getLocalidadId() == localidad.getId()) {
					    %>selected 
					    <%}%>>
					    <%=localidad.getNombre()%></option>
					<%}%>
				  	</select>
			    </div>
			</div>
			
			<div class="row">
			  <div class="col-sm">
			    <label for="txtEmail" class="col-sm-2 col-form-label">Email:</label>
			    <input type="email" class="form-control" value="<%=cliente.getEmail() %>" placeholder="Email" aria-label="Email" name="txtEmail" id="txtEmail" required>
			    <%if(errorEmail) {%>
	    	    <div class="invalid-feedback" style="display:block;">
			      El email ingresado ya existe.
			    </div>
			    <%}%>
			  </div>
				<div class="col-sm">
				<label for="txtFechaNacimiento" class="col-sm col-form-label">Fecha de Nacimiento:</label>
			    <input type="date" class="form-control" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(cliente.getFechaNacimiento()) %>" placeholder="Fecha de Nacimiento" aria-label="Fecha de Nacimiento"  name="txtFechaNacimiento" id="txtFechaNacimiento" required>
				</div>
			  <div class="col-sm">
			  
			  </div>
			</div>
			
			<div class="row">
			
			  <div class="col-sm">		
    			<label for="txtUsuario" class="col-sm-2 col-form-label">Usuario:</label>
		  		<%if(cliente.getId() == 0){%>
	        		<input type="text" class="form-control" placeholder="Usuario" aria-label="Nombre" id="txtUsuario" name="txtUsuario" required>
	        		<%if(errorUsuario) {%>
		    	    <div class="invalid-feedback" style="display:block;">
				      El usuario ingresado ya existe.
				    </div>
				    <%}%>
		  		<%} else {%>
		  			<input type="text" class="form-control" value="<%=cliente.getUsuario().getNombre() %>" disabled placeholder="Nombre" aria-label="Nombre" id="txtNombre" name="txtNombre">
		  		<%} %>
			  </div>
				
			  <div class="col-sm">
		  		<%if(cliente.getId() == 0){%>
	    			<label for="txtClaveConfirmar" class="col-sm col-form-label">Confirmar Clave:</label>
        			<input type="password" class="form-control" placeholder="Clave" aria-label="Clave" id="txtClaveConfirmar" name="txtClaveConfirmar" required>
        			<%if(errorClave) {%>
		    	    <div class="invalid-feedback" style="display:block;">
				      Las claves ingresadas no coinciden.
				    </div>
				    <%}%>
		  		<%}%>
			  </div>
			  
			  
			  
			  <div class="col-sm">		
		  		<%if(cliente.getId() == 0){%>
	    			<label for="txtClave" class="col-sm-2 col-form-label">Clave:</label>
        			<input type="password" class="form-control" placeholder="Clave" aria-label="Clave" id="txtClave" name="txtClave" required>
        			
		  		<%}%>		  	
			  </div>
			</div>
			
			<div class="row button-row">
			  <div class="col-sm">
			  <button type="submit" class="btn btn-primary" name="btnGuardar">
	                GUARDAR
	            </button>
	            <a class="btn btn-secondary" href="BuscadorCliente.jsp">
	                VOLVER
	            </a>
           	  </div>
			</div>
			</form>
        </div>
</div>
</body>
</html>
