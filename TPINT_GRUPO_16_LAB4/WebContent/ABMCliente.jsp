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
<style>
	<jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
<div class="container">
        <h1 style="text-align: center;">Cliente</h1>
        <div class="header_form">
			<form method="post" action="ServletCliente" class="abm_form">
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
				
			</form>
        </div>
       	<div class="button-wrapper">
               <form action="" method="get">
                   <button type="submit" class="button">
                       Guardar
                   </button>
                   <button type="submit" class="button">
                       Volver
                   </button>
               </form>
           </div>
</div>
</body>
</html>