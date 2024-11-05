<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuenta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
	<jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
<div class="container">
        <h1 style="text-align: center;">Cuenta</h1>
        
       	<div class="button-wrapper">
	        <form action="" method="get">
	            <button type="submit" class="button btn btn-primary">
	                TRANSFERIR
	            </button>
	            <button type="submit" class="button btn btn-danger">
	                DAR DE BAJA
	            </button>
	            <button type="submit" class="button btn btn-primary">
	                HISTORIAL DE MOVIMIENTOS
	            </button>
	            <button type="submit" class="button btn btn-primary">
	                PRÉSTAMOS
	            </button>
	        </form>
        </div>
        
        <div class="header_form">
			<form method="post" action="ServletCuenta" class="abm_form">
				<div class="abm_form-column">
					<label for="txtCBU">CBU: </label>
					<input type="text" name="txtCBU" id="txtCBU"/>
					<label for="cmbTipo">TIPO: </label>
					<select name="cmbTipo" id="cmbTipo">
						<option value="1">Caja de Ahorro</option>
						<option value="2">Cuenta Corriente</option>
					</select>
					<label for="txtFechaCreacion">FECHA DE CREACIÓN: </label>
					<input type="date" name="txtFechaCreacion" id="txtFechaCreacion" readonly="readonly"/>
				</div>
				<div class="abm_form-column">
					<span>CLIENTE: Juan Perez 12345678</span>
					<span>ESTADO: ACTIVA</span>
					<span>MONTO: $123,00</span>
				</div>
				
			</form>
		</div>
       	<div class="button-wrapper">
	        <form action="" method="get">
	            <button type="submit" class="button btn btn-success">
	                Guardar
	            </button>
	            <button type="submit" class="button btn btn-secondary">
	                Volver
	            </button>
	        </form>
        </div>
</div>
</body>
</html>