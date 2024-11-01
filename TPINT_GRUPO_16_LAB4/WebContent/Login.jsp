<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Iniciar Sesión</title>
</head>
<body>
<h2>¡Hola! Te damos la bienvenida</h2>

 <div>
                <p style="margin-top: 10%;">
                    <input id="usuario" type="text" placeholder="Usuario" required name="txtUsuario">
                </p>
                <p>
                    <input id="contrasenia" type="password" placeholder="Contraseña" name="txtContrasenia">
                </p>
                <p>
                    <input type="submit" name="btnIngresar" value="Ingresar"><br>
                </p>
	        	    	
			    <div id="popup" class="popup">
			        <span class="close-btn" onclick="closePopup()">&times;</span>
			        <p id="popupMessage"></p>
			    </div>
		 </div>
</body>
</html>