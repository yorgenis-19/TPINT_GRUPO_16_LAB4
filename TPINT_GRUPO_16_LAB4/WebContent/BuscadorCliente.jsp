<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscador Clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">    
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
	<jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
<div class="container">
        <h1 style="text-align: center;">Buscar Clientes</h1>
        <div class="header">
	        <div class="filter-container">
	        	<div class="filter-container-column">
	        		<label for="txtNombre">NOMBRE: </label>
					<input type="text" name="txtNombre" id="txtNombre"/>
					<label for="txtApellido">APELLIDO: </label>
					<input type="text" name="txtApellido" id="txtApellido"/>
	        	</div>
	        	<div class="filter-container-column">
	        		<label for="txtDNI">D.N.I.: </label>
					<input type="text" name="txtDNI" id="txtDNI"/>
					<label for="txtEmail">EMAIL: </label>
					<input type="text" name="txtEmail" id="txtEmail"/>
	        	</div>
	        </div>
	        
	       	<div class="button-wrapper">
	          <button type="submit" class="button">
	              Buscar
	          </button>
	        </div>
        </div>
        <div class="grid-container">
        	<table border="1" style="width: 100%;" class="table">
        		<tr>
        			<th>APELLIDO</th>
        			<th>NOMBRE</th>
        			<th>DNI</th>
        			<th>EMAIL</th>
        			<th></th>
        		</tr>
        		<%
        		for(int i = 0; i < 5; i++) {
    			%>
        		<tr>
        			<td>Perez</td>
        			<td>Juan</td>
        			<td>12345678</td>
        			<td>asd@asd.com</td>
        			<td>
	        			<button type="submit" class="button">
	                        Ver
	                    </button>
                    </td>
                    <td>
	        			<button type="submit" class="button">
	                        Eliminar
	                    </button>
                    </td>
        		</tr>
        		<%} %>
        	</table>
        </div>
</div>
</body>
</html>