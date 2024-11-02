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
        body {
            background-color: #f8f9fa;
            min-height: 100vh;
            display: flex;
            align-items: center;
            padding: 20px 0;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
        }
        
        h1 {
            color: #1a5f7a;
            margin-bottom: 50px;
            text-align: center;
            font-size: 2.5rem;
            font-weight: bold;
            text-shadow: 1px 1px 2px rgba(0,0,0,0.1);
        }
        
        .button-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 20px;
        }
        
        .button-wrapper {
            flex: 0 1 auto;
        }
        
        .button {
            padding: 15px 30px;
            font-size: 1.1rem;
            font-weight: 500;
            cursor: pointer;
            border: none;
            border-radius: 10px;
            background-color: #2c8c3c;
            color: white;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            width: 220px;
            height: 100px;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }
        
        .button:hover {
            background-color: #236e2f;
            transform: translateY(-2px);
            box-shadow: 0 6px 8px rgba(0,0,0,0.15);
        }
        
        .button:active {
            transform: translateY(0);
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        /* Responsive adjustments */
        @media (max-width: 768px) {
            .button-container {
                flex-direction: column;
                align-items: center;
            }
            
            .button {
                width: 100%;
                max-width: 300px;
                height: 80px;
            }
            
            h1 {
                font-size: 2rem;
                margin-bottom: 30px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Portal Cliente Bancario</h1>
        
        <div class="button-container">
            <div class="button-wrapper">
                <form action="MisDatos.jsp" method="get">
                    <button type="submit" class="button">
                        <i class="fas fa-user-circle me-2"></i>
                        Mis Datos Personales
                    </button>
                </form>
            </div>
            
            <div class="button-wrapper">
                <form action="Cuentas.jsp" method="get">
                    <button type="submit" class="button">
                        <i class="fas fa-wallet me-2"></i>
                        Mis Cuentas
                    </button>
                </form>
            </div>
            
            <div class="button-wrapper">
                <form action="Transferencias.jsp" method="get">
                    <button type="submit" class="button">
                        <i class="fas fa-exchange-alt me-2"></i>
                        Transferencias
                    </button>
                </form>
            </div>
            
            <div class="button-wrapper">
                <form action="Prestamos.jsp" method="get">
                    <button type="submit" class="button">
                        <i class="fas fa-hand-holding-usd me-2"></i>
                        Solicitar Préstamo
                    </button>
                </form>
            </div>
            
            <div class="button-wrapper">
                <form action="PagarPrestamos.jsp" method="get">
                    <button type="submit" class="button">
                        <i class="fas fa-file-invoice-dollar me-2"></i>
                        Pagar Cuotas
                    </button>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>