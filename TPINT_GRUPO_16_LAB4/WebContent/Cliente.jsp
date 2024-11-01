<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente</title>
<style>
        .button {
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: green; 
            color: white; 
        }
        .button:hover {
            background-color: olive; 
        }
        .button-container {
            display: flex; /* Usar flexbox para alinearlos en fila */
            justify-content: center; /* Centrar los botones */
            margin-top: 20px; /* Margen superior */
        }
    </style>
</head>
<body>
 <div>
  <div class="button-container">
        <form action="MisDatos.jsp" method="get">
            <button type="submit" class="button">Mis Datos</button>
        </form>
        <form action="Cuentas.jsp" method="get">
            <button type="submit" class="button">Cuentas</button>
        </form>
        <form action="Prestamos.jsp" method="get">
            <button type="submit" class="button">Préstamos</button>
        </form>
    </div>
    </div>
</body>
</html>