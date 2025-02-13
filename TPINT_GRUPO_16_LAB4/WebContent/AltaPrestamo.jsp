<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Usuario"%>
<%
if(request.getAttribute("Prestamos") == null) {
    response.sendRedirect("ServletPrestamosxAutorizar?getPrestamos");
    return;
}
%>
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
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


</style>


<script>
$(document).ready(function() {
	$('#table_id').DataTable();
});
</script>
<title>Administrar Prestamos - Admin</title>
</head>
<body>
	<% 
    ArrayList<Prestamo> listaPrestamos = null;
    if(request.getAttribute("Prestamos") != null) 
        listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("Prestamos");

    String resString = (String) request.getAttribute("resString");
    Boolean solicitado = (Boolean) request.getAttribute("resBoolean");

    Usuario usuario = (Usuario) session.getAttribute("UsuarioActual");
	%>
<header>
</header>
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
        <button class="btn btn-secondary" type="submit" name="btnLogout">Cerrar sesion</button>
      </form>
    </div>
   </div>
</nav>
    
<div class="container col-8 mt-5 pt-5">
    <br><br>
    <h2 style="color: #007bff; font-family: 'Arial', sans-serif; text-align: center;">Autorizacion de Prestamos</h2>
	<div>Cantidad de prestamos: <%= listaPrestamos != null ? listaPrestamos.size() : "Lista nula" %></div>
</div>  

<div class="container page-container">
	<div class="grid-container">
        <table border="1" id="table_id">
			<thead>	
				<tr>
	                <th>Id</th>
	                <th>Cuenta</th>
	                <th>ClienteId</th>
	                <th>Importe</th>
	                <th>Cuotas</th>
	                <th>Fecha</th>
	                <th>Estado</th>
	                <th></th>
	                <th></th>
				</tr>
			</thead>
			<tbody>
		        <% 
		        if(listaPrestamos != null) {
		            for(Prestamo p : listaPrestamos) { 
		        %>
				<tr>
        			<form action="ServletPrestamosxAutorizar" method="GET">
	                    <td><%=p.getId()%></td>  <!-- Código de préstamo pendiente -->
	        			<td><%=p.getCbu()%></td>  <!-- Número de cuenta -->
	                    <td><%=p.getClienteId()%></td>  <!-- Código de préstamo pendiente -->
	        			<td><%=p.getMontoSolicitado()%></td>  <!-- Importe solicitado -->
	        			<td><%=p.getCantidadCuotas()%></td>  <!-- Cuotas -->
	        			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaAlta())%></td>  <!-- Fecha alta -->
	                    <td>
	                    <% 
	                    switch(p.getIdEstadoPrestamo()) {
	                        case 0: %>
	                            Desaprobado
	                        <% break;
	                        case 1: %>
	                            Pendiente
	                        <% break;
	                        case 2: %>
	                            Aprobado
	                        <% break;
	                        case 4: %> 
	                        Desaprobado
	                    <% break;
	                        default: %>
	                            Error
	                        <% break;
	                    } %>
	                    </td>
	                    <td>
	                    <%if(p.getIdEstadoPrestamo() == 1){ %>
	                        <button type="submit" name="btnAutorizar" class="btn btn-success mr-3 mx-4">
	                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
	                                <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
	                            </svg>
	                        </button>
	                    <%} %>
	                    </td>
	                    <td>
	                    <%if(p.getIdEstadoPrestamo() == 1){ %>
	                        <button type="submit" name="btnRechazar" class="btn btn-danger">
	                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
	                                <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
	                            </svg>
	                        </button>
	                    <%} %>
	                    </td>
	                    <input type="hidden" name="codPrestamo" value="<%=p.getId()%>">
	                </form>
				</tr>
        		<% 
        		}}%>
			</tbody>
		</table>
	</div>
</div>

</body>
</html>