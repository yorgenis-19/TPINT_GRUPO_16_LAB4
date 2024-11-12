<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrador</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	<jsp:include page="style.css"></jsp:include>
        .card{
        	text-decoration: none !important;
        	cursor: pointer;
        	width: 200px;
        }
        .card:hover{
        	opacity: 80%;
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
<nav class="navbar bg-primary navbar-expand-lg " data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="Administrador.jsp">
    	<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 20 20"><path fill="currentColor" fill-rule="evenodd" d="M10 2.682L3.938 6.5h12.124zm.267-1.014a.5.5 0 0 0-.533 0L1.939 6.577c-.424.267-.235.923.267.923h15.588c.502 0 .691-.656.267-.923zM2 17a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1h-15A.5.5 0 0 1 2 17m1.5-8.5A.5.5 0 0 1 4 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0A.5.5 0 0 1 7 9v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5m3 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V9a.5.5 0 0 1 .5-.5" clip-rule="evenodd"/></svg>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Administrador.jsp">Inicio</a>
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
 
 <div class="container cards-container">
	<a class="card" href="BuscadorCliente.jsp">
	  <svg class="card-img-top" style="height: 100%;" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 32 32"><path fill="currentColor" d="M18 30h-4a2 2 0 0 1-2-2v-7a2 2 0 0 1-2-2v-6a3 3 0 0 1 3-3h6a3 3 0 0 1 3 3v6a2 2 0 0 1-2 2v7a2 2 0 0 1-2 2m-5-18a.94.94 0 0 0-1 1v6h2v9h4v-9h2v-6a.94.94 0 0 0-1-1zm3-3a4 4 0 1 1 4-4a4 4 0 0 1-4 4m0-6a2 2 0 1 0 2 2a2 2 0 0 0-2-2"/></svg>
	  <div class="card-body">
	    <h5 class="card-title">Clientes</h5>
	    <!-- <p class="card-text">Ver Clientes</p>-->
	  </div>
	</a>
 
 	<a class="card">
	  <svg class="card-img-top" style="height: 100%;" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24"><path fill="currentColor" d="M12 5a3.5 3.5 0 0 0-3.5 3.5A3.5 3.5 0 0 0 12 12a3.5 3.5 0 0 0 3.5-3.5A3.5 3.5 0 0 0 12 5m0 2a1.5 1.5 0 0 1 1.5 1.5A1.5 1.5 0 0 1 12 10a1.5 1.5 0 0 1-1.5-1.5A1.5 1.5 0 0 1 12 7M5.5 8A2.5 2.5 0 0 0 3 10.5c0 .94.53 1.75 1.29 2.18c.36.2.77.32 1.21.32s.85-.12 1.21-.32c.37-.21.68-.51.91-.87A5.42 5.42 0 0 1 6.5 8.5v-.28c-.3-.14-.64-.22-1-.22m13 0c-.36 0-.7.08-1 .22v.28c0 1.2-.39 2.36-1.12 3.31c.12.19.25.34.4.49a2.48 2.48 0 0 0 1.72.7c.44 0 .85-.12 1.21-.32c.76-.43 1.29-1.24 1.29-2.18A2.5 2.5 0 0 0 18.5 8M12 14c-2.34 0-7 1.17-7 3.5V19h14v-1.5c0-2.33-4.66-3.5-7-3.5m-7.29.55C2.78 14.78 0 15.76 0 17.5V19h3v-1.93c0-1.01.69-1.85 1.71-2.52m14.58 0c1.02.67 1.71 1.51 1.71 2.52V19h3v-1.5c0-1.74-2.78-2.72-4.71-2.95M12 16c1.53 0 3.24.5 4.23 1H7.77c.99-.5 2.7-1 4.23-1"/></svg>
	  <div class="card-body">
	    <h5 class="card-title">Usuarios</h5>
	    <!-- <p class="card-text">Ver usuarios</p>-->
	  </div>
	</a>
	
	<a class="card">
	  <svg  class="card-img-top" style="height: 100%;"  xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24"><path fill="currentColor" d="M18 2a2 2 0 0 1 2 2v16a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2zm0 2h-5v5l-2.5-2.3L8 9V4H6v16h12m-5-9a2 2 0 1 1-2 2a2 2 0 0 1 2-2m4 8H9v-1c0-1.33 2.67-2 4-2s4 .67 4 2z"/></svg>
	  <div class="card-body">
	    <h5 class="card-title">Cuentas</h5>
	    <!-- <p class="card-text">Ver Cuentas</p> -->
	  </div>
	</a>
	
	<a class="card">
	  <svg class="card-img-top" style="height: 100%;" xmlns="http://www.w3.org/2000/svg" width="72" height="48" viewBox="0 0 1920 1280"><path fill="currentColor" d="M768 896h384v-96h-128V352H910L762 489l77 80q42-37 55-57h2v288H768zm512-256q0 70-21 142t-59.5 134t-101.5 101t-138 39t-138-39t-101.5-101T661 782t-21-142t21-142t59.5-134T822 263t138-39t138 39t101.5 101t59.5 134t21 142m512 256V384q-106 0-181-75t-75-181H384q0 106-75 181t-181 75v512q106 0 181 75t75 181h1152q0-106 75-181t181-75m128-832v1152q0 26-19 45t-45 19H64q-26 0-45-19t-19-45V64q0-26 19-45T64 0h1792q26 0 45 19t19 45"/></svg>
	  <div class="card-body">
	    <h5 class="card-title">Préstamos</h5>
	    <!-- <p class="card-text">Ver Préstamos</p>-->
	  </div>
	</a>
	
	<a class="card" >
	  <svg class="card-img-top" style="height: 100%;" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 2048 2048"><path fill="currentColor" d="M1920 1280h-384V384h384zM0 1024h384v640H0zm1408 512h-128v128h-256V0h384zM512 384h384v1280H512zm1536 1280v128h-256v256h-128v-256h-256v-128h256v-256h128v256z"/></svg>
	  <div class="card-body">
	    <h5 class="card-title">Reportes</h5>
	    <!-- <p class="card-text">Ver Cuentas</p>-->
	  </div>
	</a>
 </div>
</div>

</body>
</html>