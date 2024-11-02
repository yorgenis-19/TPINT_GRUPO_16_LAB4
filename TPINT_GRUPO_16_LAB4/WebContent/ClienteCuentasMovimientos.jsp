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
        body {
            background-color: #f8f9fa;
            padding: 20px 0;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
        }
        
        h1 {
            color: #2c5282;
            margin-bottom: 30px;
            text-align: center;
        }
        
        .account-card {
            background: white;
            padding: 25px;
            margin: 20px 0;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            font-weight: 600;
            color: #2c5282;
            margin-bottom: 8px;
            display: block;
        }
        
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #cbd5e0;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        
        #infoCuenta {
            background-color: #f8fafc;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        
        #infoCuenta h3 {
            color: #2c5282;
            margin-bottom: 15px;
            font-size: 1.2rem;
        }
        
        #infoCuenta p {
            margin-bottom: 10px;
            color: #4a5568;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
            background-color: white;
        }
        
        th {
            background-color: #2c5282;
            color: white;
            padding: 12px;
            text-align: left;
            font-weight: 500;
        }
        
        td {
            padding: 12px;
            border-bottom: 1px solid #e2e8f0;
            color: #4a5568;
        }
        
        tr:hover {
            background-color: #f8fafc;
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
        <h1>Mis Cuentas</h1>
        
        <div class="account-card">
            <div class="form-group">
                <label for="selectCuenta">Seleccione una cuenta:</label>
                <select id="selectCuenta" name="cuenta" class="form-select" onchange="cargarMovimientos()">
                    <option value="">Seleccione una cuenta</option>
                </select>
            </div>
            
            <div id="infoCuenta">
                <h3>Información de la Cuenta</h3>
                <p>CBU: <span id="cbaCuenta"></span></p>
                <p>Saldo Actual: <span id="saldoCuenta"></span></p>
                <p>Tipo de Cuenta: <span id="tipoCuenta"></span></p>
            </div>
            
            <h3>Movimientos</h3>
            <div class="table-responsive">
                <table id="movimientosTable" class="table">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Tipo</th>
                            <th>Monto</th>
                            <th>Descripción</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Los movimientos se cargarán dinámicamente -->
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="button-container">
            <form action="index.jsp">
                <button type="submit" class="button">Volver al Menú Principal</button>
            </form>
        </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>