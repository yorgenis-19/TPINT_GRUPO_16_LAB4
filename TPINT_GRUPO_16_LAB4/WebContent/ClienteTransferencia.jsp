<%@page import="entidad.MovimientoTipo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencia</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
	<jsp:include page="style.css"></jsp:include>
</style>
<script>
  // Add event listener on submit
  document.querySelector('form').addEventListener('submit', function(event) {
    const importeInput = document.getElementById('txtImporte');
    const importeError = document.getElementById('importeError');
    const selectedCuenta = document.getElementById('cmbCuenta');
    const selectedCuentaValue = parseInt(selectedCuenta.value);

    // Check if selectedCuenta has a value (to avoid errors)
    if (isNaN(selectedCuentaValue)) {
      event.preventDefault();
      importeError.textContent = "Debe seleccionar una cuenta.";
      importeInput.classList.add('is-invalid');
      return;
    }

    const importe = parseFloat(importeInput.value);
    
    if (importe < 10 || importe > getMontoByCuentaId(selectedCuentaValue)) {
      event.preventDefault();
      importeError.textContent = "Importe debe ser mayor a 10 y menor o igual al monto disponible.";
      importeInput.classList.add('is-invalid');
      return;
    }

    // If validation passes, remove error message and class
    importeError.textContent = "";
    importeInput.classList.remove('is-invalid');
  });

  // Function to get monto based on cuentaId (assuming you have this functionality)
  function getMontoByCuentaId(cuentaId) {
    // Implement your logic to retrieve monto based on cuentaId
    // This could involve searching the 'cuentas' array or making an AJAX request
    // Replace with your actual implementation
    for (const cuenta of cuentas) {
      if (cuenta.getId() === cuentaId) {
        return cuenta.getMonto();
      }
    }
    return 0; // Handle case where cuenta is not found
  }
</script>
</head>
<body>
<%
Usuario usuario = new Usuario(); 
if(session.getAttribute("UsuarioActual") != null)
{
	usuario = (Usuario)session.getAttribute("UsuarioActual");
}
ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>)request.getAttribute("Lista_Cuentas");
ArrayList<MovimientoTipo> tipos = (ArrayList<MovimientoTipo>)request.getAttribute("Lista_MovimientoTipos");

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

<div class="container  page-container">
	<div class="header">
		<form method="post" action="ServletClienteTransferir" class="container needs-validation" style="width: 400px;">
			<div class="mb-3">
			  <label for="cmbCuenta" class="form-label">Cuenta:</label>
			    <select class="form-select" value="" id="cmbCuenta" name="cmbCuenta" name="cmbCuenta" required>
			    <%for(Cuenta cuenta : cuentas){ 
			    	if(cuenta.getMonto() <= 0) continue;
			    %>
				    <option value="<%=cuenta.getId()%>"><%=cuenta.getCBU()%> - $<%=cuenta.getMonto()%></option>
				<%}%>
			  	</select>
			</div>
			<div class="mb-3">
			  <label for="txtCBUDestino" class="form-label">C.B.U. Cuenta de Destino:</label>
		      <input type="number" min="0" step="1" class="form-control" id="txtCBUDestino" name="txtCBUDestino" required>
			</div>
			<div class="mb-3">
			  <label for="txtImporte" class="form-label">Importe:</label>
		      <input type="number" step="0.01" class="form-control" id="txtImporte" name="txtImporte" min="10" required>
		      <div id="importeError" class="invalid-feedback">
			      Importe debe ser mayor a 10 y menor o igual al monto de la cuenta seleccionada.
			    </div>
			</div>
			<div class="mb-3">
			  <label for="txtDetalle" class="form-label">Detalle:</label>
	  		  <textarea class="form-control" id="txtDetalle" rows="3" name="txtDetalle" required></textarea>		
	  		</div>
			<div class="mb-3">
			<div class="row">
				<div class="col">
					<button type="submit" class="btn btn-success" name="btnTransferir">
		                TRANSFERIR
		            </button>
	            </div>
				<div class="col">
		            <a class="btn btn-secondary" href="Cliente.jsp">
		                VOLVER
		            </a>
	            </div>
			</div>	
	  		</div>
  		</form>
	</div>
</div>
</body>
</html>