<%@page import="negocioImpl.CuentaTipoNegocioImpl"%>
<%@page import="entidad.CuentaTipo"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscador Cuentas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">    
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
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
ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
if(request.getAttribute("CuentassResultado") != null) {
	cuentas = (ArrayList<Cuenta>)request.getAttribute("CuentassResultado");
}
ArrayList<Cliente> clientes = new ClienteNegocioImpl().Obtener("", "", "", "", true);
ArrayList<CuentaTipo> tipos = new CuentaTipoNegocioImpl().ObtenerTodos();
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
        <h1 style="text-align: center;">CUENTAS</h1>
        <div class="header">
	        <div class="filter-container">
	         <form class="row g-3" method="get" action="ServletBuscarCuentas">
	         	  <div class="col-md-4">
				    <label for="cmbCliente" class="form-label">Cliente</label>
				    <select class="form-select" id="cmbCliente" name="cmbCliente">
			        	<option value="0" selected>Todos</option>
					    <%
						    for(Cliente cliente : clientes)
						    {%>
						        <option value="<%=cliente.getId()%>">
						        <%=cliente.getNombreCompleto()%>
						        </option>
						    <%}
					    %>
				  	</select>
				  </div>
			      <div class="col-md-4">
				    <label for="txtCBU" class="form-label">CBU</label>
				    <input type="text" class="form-control" id="txtCBU" name="txtCBU">
				  </div>
			      <div class="col-md-4">
				    <label for="cmbTipo" class="form-label">Tipo</label>
				    <select class="form-select" id="cmbTipo" name="cmbTipo">
			        	<option value="0" selected>Todos</option>
					    <%
						    for(CuentaTipo tipo : tipos)
						    {%>
						        <option value="<%=tipo.getId()%>">
						        <%=tipo.getDescripcion()%>
						        </option>
						    <%}
					    %>
				  	</select>
				  </div>
				  <div class="col-md-4">
				    <label for="txtMonto" class="form-label">Monto</label>
				    <input type="number" class="form-control" id="txtMonto" name="txtMonto">
				  </div>
				  
				  <div class="col-10">
				  </div>
				  <div class="col-1">
				    <button type="submit" class="btn btn-primary" name=btnBuscar>Buscar</button>
				  </div>
				  <div class="col-1">
				    <a class="btn btn-success" href="NuevaCuenta.jsp">Nuevo</a>
				  </div>
	         </form>
	         
	        </div>
	         
        </div>
        <div class="grid-container">
        	<table class="table table-striped table-hover">
        		<tr>
        			<th>Cliente</th>
        			<th>Monto</th>
        			<th>CBU</th>
        			<th>Tipo</th>
        			<th></th>
        			<th></th>
        		</tr>
        		<%
    				if(clientes != null) {
        			for(Cuenta obj : cuentas) {
    			%>
        		<tr>
        			<td><%=obj.getCliente().getNombreCompleto()%></td>
        			<td><%=obj.getMonto()%></td>
        			<td><%=obj.getCBU()%></td>
        			<td><%=obj.getTipo().getDescripcion()%></td>
        			<td>
	        			<form method="get" action="ServletVerCuenta">
	        				<input name="Id" value="<%=obj.getId()%>" style="display:none;">
		        			<button type="submit" name="btnVer" class="btn btn-outline-primarybtn btn-outline-primary">
		                        Ver
		                    </button>        			
	        			</form>
                    </td>
        		</tr>
        		<%} 
        		}%>
        	</table>
        </div>
</div>
</body>
</html>