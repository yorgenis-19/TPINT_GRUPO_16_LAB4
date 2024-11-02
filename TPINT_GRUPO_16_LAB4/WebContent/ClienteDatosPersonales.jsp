<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
    <style>
    	.body{
    		padding: 20px;
    		margin:20px;
    		}
        /* [Estilos anteriores] */
        .profile-info {
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 8px;
            margin: 20px 0;
        }
        .info-row {
            display: flex;
            margin: 10px 0;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .info-label {
            font-weight: bold;
            width: 200px;
        }
        .info-value {
            flex-grow: 1;
        }
        .button-container {
            text-align: center;
            margin-top: 30px;
        }
        
        .button {
            background-color: #2c5282;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .button:hover {
            background-color: #1a365d;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 style="text-align: center;">Mis Datos Personales</h1>
        
        <div class="profile-info">
            <div class="info-row">
                <span class="info-label">Nombre:</span>
                <span class="info-value">{cliente.nombre}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Apellido:</span>
                <span class="info-value">{cliente.apellido}</span>
            </div>
            <div class="info-row">
                <span class="info-label">DNI:</span>
                <span class="info-value">{cliente.dni}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Dirección:</span>
                <span class="info-value">{cliente.direccion}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Teléfono:</span>
                <span class="info-value">{cliente.telefono}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Email:</span>
                <span class="info-value">{cliente.email}</span>
            </div>
        </div>
        
        <div class="button-wrapper">
                <form action="" method="get">
                    <button type="submit" class="button">
                        Volver al menu principal
                    </button>
                </form>
            </div>
    </div>
</body>
</html>