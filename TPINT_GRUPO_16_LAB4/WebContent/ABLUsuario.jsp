<!DOCTYPE html>
<%@page import="negocioImpl.UsuarioTipoNegocioImpl"%>
<%@page import="negocio.UsuarioTipoNegocio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.UsuarioTipo"%>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>ABML Usuarios</title>
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
        /* Estilos CSS en línea */
        .container {
            max-width: 800px;
            margin-top: 20px;
        }
        .header-title {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>

<script>
function setDeleteInfo(usuarioId, usuarioName) {
  document.getElementById("usuarioId").value = usuarioId;
  document.getElementById("usuarioName").innerText = usuarioName;
}
$(document).ready(function() {
	$('#table_id').DataTable();
});
</script>
</head>
<%
	Usuario usuario = new Usuario(); 
	if(session.getAttribute("UsuarioActual") != null)
	{
		usuario = (Usuario)session.getAttribute("UsuarioActual");
	}	
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	if(request.getAttribute("UsuariosResultado") != null) {
		usuarios = (ArrayList<Usuario>)request.getAttribute("UsuariosResultado");
	}

	ArrayList<UsuarioTipo> tipos = (ArrayList<UsuarioTipo>)request.getAttribute("UsuarioTipos");
	String nombreFiltro = request.getAttribute("Filtro_Nombre") != null ? request.getAttribute("Filtro_Nombre").toString() : "";
	int tipoFiltro = request.getAttribute("Filtro_Tipo") != null ? (int)request.getAttribute("Filtro_Tipo") : 0;
	String activoFiltro = request.getAttribute("Filtro_Activo") != null ? request.getAttribute("Filtro_Activo").toString() : "";
%>
<body>
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
    <h1 style="text-align: center;">USUARIOS</h1>
    <div class="header">
	        <div class="filter-container">
	         <form class="row g-3" method="get" action="ServletBuscarUsuarios">
	         	  <div class="col-md-4">
				    <label for="txtNombre" class="form-label">Nombre</label>
				    <input type="text" class="form-control" id="txtNombre" name="txtNombre" value="<%=nombreFiltro%>" >
				  </div>
			      <div class="col-md-4">
				    <label for="cmbTipo" class="form-label">Tipo</label>
				    <select class="form-select" id="cmbTipo" name="cmbTipo">
					    <option value="0" <%if(tipoFiltro == 0){%>selected<%}%>>Todos</option>
					    <%for(UsuarioTipo tipo : tipos)
				    	{%>
				        <option value="<%=tipo.getId()%>" <%if(tipoFiltro == tipo.getId()){%>selected<%}%>>
				        <%=tipo.getDescripcion()%>
				        </option>
				    	<%}%>
				  	</select>
				  </div>
			      <div class="col-md-4">
				    <label for="cmbActivo" class="form-label">Activo</label>
				    <select class="form-select" value="" id="cmbActivo" name="cmbActivo">
				        <option value="TODOS" <%if(activoFiltro.equals("TODOS")){%>selected<%}%>>Todos</option>
				        <option value="ACTIVO" <%if(activoFiltro.equals("ACTIVO")){%>selected<%}%>>Si</option>
				        <option value="BAJA" <%if(activoFiltro.equals("BAJA")){%>selected<%}%>>No</option>
				  	</select>
				  </div>
				  <div class="col-10">
				  </div>
				  <div class="col-1">
				    <button type="submit" class="btn btn-primary" name=btnBuscar>Buscar</button>
				  </div>
				  <!-- <div class="col-1">
				    <a class="btn btn-success" href="ABMCliente.jsp">Nuevo</a>
				  </div> -->
	         </form>
	        </div>
        </div>

        <div class="grid-container">
        
        <table border="1" id="table_id">
			<thead>	
				<tr>
        			<th>NOMBRE</th>
        			<th>TIPO</th>
        			<th>ACTIVO</th>
        			<th></th>
				</tr>
			</thead>
			<tbody>
        		<%
    				if(usuarios != null) {
        			for(Usuario obj : usuarios) {
    			%>
				<tr>
        			<td><%=obj.getNombre()%></td>
        			<td><%=obj.getTipo().getDescripcion()%></td>
        			<td><%=obj.getActivoStr()%></td>
                    <td>
					    <input name="Id" value="<%=obj.getId()%>" id="hiddenUsuarioId" style="display:none;">
					    <%if(obj.getActivo()) {%>
					    <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmationModal" onclick="setDeleteInfo(<%=obj.getId()%>, '<%=obj.getNombre()%>')">
						      Baja
				    	</button>
				    	<%}%>
                   	</td>
				</tr>
        		<% 
        		}}%>
			</tbody>
		</table>
        <%-- 
        	<table class="table table-striped table-hover">
        		<tr>
        			<th>NOMBRE</th>
        			<th>TIPO</th>
        			<th>ACTIVO</th>
        			<th></th>
        			<th></th>
        		</tr>
        		<%
    				if(usuarios != null) {
        			for(Usuario obj : usuarios) {
    			%>
        		<tr>
        			<td><%=obj.getNombre()%></td>
        			<td><%=obj.getTipo().getDescripcion()%></td>
        			<td><%=obj.getActivoStr()%></td>
        			<!-- 
        			<td>
	        			<form method="get" action="ServletVerCliente">
	        				<input name="Id" value="<%=obj.getId()%>" style="display:none;">
		        			<button type="submit" name="btnVer" class="btn btn-outline-primarybtn btn-outline-primary">
		                        Ver
		                    </button>        			
	        			</form>
                    </td>
                     -->
                    <td>
					    <input name="Id" value="<%=obj.getId()%>" id="hiddenUsuarioId" style="display:none;">
					    <%if(obj.getActivo()) {%>
					    <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmationModal" onclick="setDeleteInfo(<%=obj.getId()%>, '<%=obj.getNombre()%>' )">
						      Baja
				    	</button>
				    	<%}%>
                   	</td>
        		</tr>
        		<%} 
        		}%>
        	</table>--%>
        </div>
        
 
	<div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="deleteConfirmationModalLabel">Eliminar Usuario</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        ¿Está seguro que desea dar de baja al usuario <span id="usuarioName"></span>?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <form method="post" action="ServletEliminarUsuario" id="deleteForm">
	          <input type="hidden" name="usuarioId" id="usuarioId" value="">
	          <button type="submit" name="btnEliminarUsuario" class="btn btn-danger">Eliminar</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
</div>
</body>
</html>

