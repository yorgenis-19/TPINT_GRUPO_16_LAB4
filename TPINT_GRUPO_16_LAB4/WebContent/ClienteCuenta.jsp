<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuenta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
Cuenta cuenta = (Cuenta)request.getAttribute("CuentaSeleccionada");

ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>)request.getAttribute("Movimientos");

%>
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
<div class="container page-container">
     <h1 style="text-align: center;">Cuenta</h1>
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col">
					Monto: <%=cuenta.getMonto()%>
				</div>
				<div class="col">
					C.B.U.: <%=cuenta.getCBU()%>
				</div>
				<div class="col">
					Tipo: <%=cuenta.getTipo().getDescripcion()%>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
        <div class="grid-container">
        	<table class="table table-striped table-hover">
        		<tr>
        			<th>Origen</th>
        			<th>Destino</th>
        			<th>Importe</th>
        			<th>Fecha</th>
        			<th>Detalle</th>
        			<th>Tipo</th>
        		</tr>
        		<%
        			for(Movimiento obj : movimientos) {
    			%>
        		<tr>
        			<td><%=obj.getCuentaOrigen().getCBU()%></td>
        			<td><%=obj.getCuentaDestino().getCBU()%></td>
        			<td><%=obj.getImporte()%></td>
        			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(obj.getFecha())%></td>
        			<td><%=obj.getDetalle()%></td>
        			<td><%=obj.getTipo().getDescripcion()%></td>
        		</tr>
        		<%}%>
        	</table>
        </div>
	</div>
	
	
	<div>
        <form action="ServletClienteCuentas" method="get">
           <button type="submit" class="btn btn-secondary" name="btnClienteCuentas">
               Volver
           </button>
       </form>
	</div>
</div>
</body>
</html>