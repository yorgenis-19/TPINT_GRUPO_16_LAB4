<%@page import="entidad.Usuario"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.CuentaTipo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado cuentas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	<jsp:include page="style.css"></jsp:include>
        .card{
        	text-decoration: none !important;
        	cursor: pointer;
        	width: 200px;
        }
        .card:hover{
        	opacity: 80%;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="path_to_your_css_file.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    
     <script type="text/javascript">
     $(document).ready(function() {
    	    $('#tablaCuentas').DataTable({
    	    	order: [[0, 'desc']],
    	        language: {
    	            lengthMenu: "Mostrar MENU registros",
    	            info: "Mostrando START a END de TOTAL registros",
    	            infoEmpty: "Mostrando 0 a 0 de 0 registros",
    	            infoFiltered: "(filtrado de MAX registros en total)",
    	            loadingRecords: "Cargando...",
    	            zeroRecords: "No se encontraron registros coincidentes",
    	            emptyTable: "No hay datos disponibles en la tabla",
    	            paginate: {
    	                first: "Primero",
    	                previous: "Anterior",
    	                next: "Siguiente",
    	                last: "Último"
    	            },
    	            aria: {
    	                sortAscending: ": activar para ordenar columna ascendente",
    	                sortDescending: ": activar para ordenar columna descendente"
    	            },
    	            lengthMenu: "Cantidad registros MENU",
    	            search: "Buscar:"
    	        },
    	        dom: 'lfrtip'
    	    });
    	    ocultarNoHabilitadas();
    	});
     function ocultarNoHabilitadas() {
         var noHabilitadas = document.getElementsByClassName('no-habilitada');
         for (var i = 0; i < noHabilitadas.length; i++) {
             noHabilitadas[i].style.display = 'none';
         }
     }		
    
     function toggleCuentas() {
    	    var checkbox = document.getElementById('toggleHabilitados');
    	    var habilitadas = document.getElementsByClassName('habilitada');
    	    var noHabilitadas = document.getElementsByClassName('no-habilitada');

    	    if (checkbox.checked) {
    	        // Mostrar tanto habilitadas como no habilitadas
    	        for (var i = 0; i < noHabilitadas.length; i++) {
    	            noHabilitadas[i].style.display = '';
    	        }
    	        for (var j = 0; j < habilitadas.length; j++) {
    	            habilitadas[j].style.display = '';
    	        }
    	    } else {
    	        // Solo mostrar las habilitadas
    	        for (var i = 0; i < noHabilitadas.length; i++) {
    	            noHabilitadas[i].style.display = 'none';
    	        }
    	        for (var j = 0; j < habilitadas.length; j++) {
    	            habilitadas[j].style.display = '';
    	        }
    	    }
    	}
     $(document).ready(function() {
    	    // Detecta cuando se cambia el valor del estado de la cuenta
    	    $('.activa').change(function() {
    	        var cuentaId = $(this).data('id');
    	        var nuevaEstado = $(this).val();
    	        
    	        // Realizar una solicitud AJAX para actualizar el estado
    	        $.ajax({
    	            url: 'ServletActualizarCuenta',  // Asegúrate de que esta URL corresponde a tu servlet
    	            method: 'POST',
    	            data: {
    	                cuentaId: cuentaId,
    	                estado: nuevaEstado
    	            },
    	            success: function(response) {
    	                if (response === 'success') {
    	                    alert('Estado de la cuenta actualizado correctamente.');
    	                } else {
    	                    alert('Hubo un error al actualizar el estado.');
    	                }
    	            }
    	        });
    	    });
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


<div class="table-container" >
 	<div class="toggle-container filtro-container" style="margin-top:5%;" >
    <input type="checkbox" id="toggleHabilitados" onchange="toggleCuentas()">
    <label for="toggleHabilitados">Mostrar todas las cuentas</label>
</div>
        <table id="tablaCuentas" class="display">
            <thead>
                <tr>
                    <th>Cliente</th>
                    <th>DNI del Cliente</th>
                    <th>Fecha de Creación</th>
                    <th>Tipo de Cuenta</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>Activa</th>
                </tr>
            </thead>
            <tbody>
                <%

                ArrayList<Cuenta> listaCuentas = null;
                listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");  
                if (listaCuentas != null) {
                    for (Cuenta cuenta : listaCuentas) {
                %>
                <tr class="<%= cuenta.isActiva()  ? "habilitada" : "no-habilitada" %>">
                    <td><%= cuenta.getCliente().getNombreCompleto() %></td>
                    <td><%= cuenta.getCliente().getDni() %></td>
                    <td><%= cuenta.getFechaDeCreacion()%></td>
					<td><%= cuenta.getTipo().getDescripcion() %></td>
                    <td><%= cuenta.getCBU() %></td>
                    <td><%= cuenta.getMonto() %></td>
                    <td>
					    <select class="activa" data-id="<%= cuenta.getId() %>">
					        <option value="1" <%= cuenta.isActiva() ? "selected" : "" %>>Sí</option>
					        <option value="0" <%= !cuenta.isActiva() ? "selected" : "" %>>No</option>
					    </select>
					</td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
    				<td colspan="7">No se encontraron cuentas.</td>
				</tr>
				<%
				    }
				%>
            </tbody>
        </table>
    </div>



		<div style="display:flex; justify-content: end; margin: 2%;" >
        	<input type="button" value="Volver" name="btnVolver" onclick="window.location.href='BuscadorCuenta.jsp';" class="btn btn-secondary">
        </div>

</body>
</html>