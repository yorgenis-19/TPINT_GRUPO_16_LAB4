<%@page import="entidad.Usuario"%>
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
        a{
        	text-decoration:none;
        	colot:white;
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
<%
Usuario usuario = new Usuario(); 
if(session.getAttribute("UsuarioActual") != null)
{
	usuario = (Usuario)session.getAttribute("UsuarioActual");
}
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
 <div class="container page-container">
        <h1>Portal Cliente Bancario</h1>
        
        <div class="button-container">
        	<a  href="ClienteDatos.jsp">
            	<div class="button-wrapper">
                    <button type="submit" class="button">
                        <i class="fas fa-user-circle me-2"></i>
                        Mis Datos Personales
                    </button>
            	</div>
            </a>
            
            <div class="button-wrapper">
                <form action="ServletClienteCuentas" method="get">
                    <button type="submit" class="button" name="btnClienteCuentas">
                        <i class="fas fa-wallet me-2"></i>
                        Mis Cuentas
                    </button>
                </form>
            </div>
            
            <div class="button-wrapper">
                <form action="ServletClienteTransferencia" method="get">
                    <button type="submit" class="button" name="btnClienteTransferencia">
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