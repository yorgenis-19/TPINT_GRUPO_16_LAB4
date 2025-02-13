<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscador Clientes</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>    
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
	<jsp:include page="style.css"></jsp:include>
	.btn-column{
	display: flex;
  flex-direction: column;
  justify-content: end;
  }
</style>
<script>
	function setDeleteInfo(clientId, clientName) {
	  document.getElementById("clientId").value = clientId;
	  document.getElementById("clienteName").innerText = clientName;
	}
	
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>
</head>
<body>
<%
Usuario usuario = new Usuario(); 
if(session.getAttribute("UsuarioActual") != null)
{
	usuario = (Usuario)session.getAttribute("UsuarioActual");
}
ArrayList<Cliente> clientes = new ArrayList<Cliente>();
if(request.getAttribute("ClientesResultado") != null) {
	clientes = (ArrayList<Cliente>)request.getAttribute("ClientesResultado");
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
        <h1 style="text-align: center;">CLIENTES</h1>
        <div class="header">
	        <div class="filter-container">
	         <form class="row g-3" method="get" action="ServletBuscarClientes">
	         	  <div class="col-md-4">
				    <label for="txtNombre" class="form-label">Nombre</label>
				    <input type="text" class="form-control" id="txtNombre" name="txtNombre" value="<%if(request.getAttribute("Filtro_Nombre") != null){ %><%=request.getAttribute("Filtro_Nombre")%><%} %>" >
				  </div>
			      <div class="col-md-4">
				    <label for="txtApellido" class="form-label">Apellido</label>
				    <input type="text" class="form-control" id="txtApellido" name="txtApellido" value="<%if(request.getAttribute("Filtro_Apellido") != null){ %><%=request.getAttribute("Filtro_Apellido") %><%} %>">
				  </div>
			      <div class="col-md-4">
				    <label for="txtDNI" class="form-label">DNI</label>
				    <input type="text" class="form-control" id="txtDNI" name="txtDNI" value="<%if(request.getAttribute("Filtro_Dni") != null){ %><%=request.getAttribute("Filtro_Dni") %><%} %>">
				  </div>
				  <div class="col-md-4">
				    <label for="txtEmail" class="form-label">Email</label>
				    <input type="text" class="form-control" id="txtEmail" name="txtEmail" value="<%if(request.getAttribute("Filtro_Email") != null){%><%=request.getAttribute("Filtro_Email")%><%}%>">
				  </div>
				  
				  <div class="col-10">
				  </div>
				  <div class="col-1">
				    <button type="submit" class="btn btn-primary" name=btnBuscar>Buscar</button>
				  </div>
	         	</form>
				  <div class="col-1 btn-column">
        			<form method="post" action="ServletVerCliente">
				    	<button class="btn btn-success" name="btnVer" type="submit">Nuevo</button>
				    </form>
				  </div>
	         
	        </div>
	         
        </div>
        <div class="grid-container">
        
        <table border="1" id="table_id">
			<thead>	
				<tr>
        			<th>APELLIDO</th>
        			<th>NOMBRE</th>
        			<th>DNI</th>
        			<th>C.U.I.L.</th>
        			<th>EMAIL</th>
        			<th>SEXO</th>
        			<th>USUARIO</th>
        			<th>FECHA DE NACIMIENTO</th>
        			<th></th>
        			<th></th>
				</tr>
			</thead>
			<tbody>
				<%
    				if(clientes != null) {
        			for(Cliente obj : clientes) {
				%>	
				<tr>
        			<td><%=obj.getApellido()%></td>
        			<td><%=obj.getNombre()%></td>
        			<td><%=obj.getDni()%></td>
        			<td><%=obj.getCuil()%></td>
        			<td><%=obj.getEmail()%></td>
        			<td><%=obj.getSexo()%></td>
        			<td><%=obj.getUsuario().getNombre()%></td>
        			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(obj.getFechaNacimiento())%></td>
        			<td>
	        			<form method="get" action="ServletVerCliente">
	        				<input name="Id" value="<%=obj.getId()%>" style="display:none;">
		        			<button type="submit" name="btnVer" class="btn btn-outline-primarybtn btn-outline-primary">
		                        Ver
		                    </button>        			
	        			</form>
                    </td>
                    <td>
					    <input name="Id" value="<%=obj.getId()%>" id="hiddenClientId" style="display:none;">
					    <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmationModal" onclick="setDeleteInfo(<%=obj.getId()%>, '<%=obj.getNombreCompleto()%>' )">
						      Eliminar
				    	</button>
                   	</td>
				</tr>
        		<% 
        		}}%>
			</tbody>
		</table>
        
        
        </div>
        
	<div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="deleteConfirmationModalLabel">Eliminar Cliente</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        ¿Está seguro que desea eliminar al cliente <span id="clienteName"></span>?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <form method="post" action="ServletEliminarCliente" id="deleteForm">
	          <input type="hidden" name="clientId" id="clientId" value="">
	          <button type="submit" name="btnEliminarCliente" class="btn btn-danger">Eliminar</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
</div>
</body>
</html>