package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map; // Importa Map de java.util
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.CuentaTipo;
import negocio.ReporteNegocio;
import negocioImpl.ReporteNegocioImpl;


/**
 * Servlet implementation class ServletGenerarReporte
 */
@WebServlet("/ServletGenerarReporte")
public class ServletGenerarReporte extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReporteNegocio reportenegocio;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGenerarReporte() {
        super();
        reportenegocio = new ReporteNegocioImpl();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Este método no está siendo usado en este caso
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String fechaInicio = request.getParameter("fechaInicio");
    	 String fechaFin = request.getParameter("fechaFin");
    	  // Obtenemos el saldo total de las cuentas utilizando la capa de negocio
    	 if (request.getParameter("btnReporte1") != null) {
        double saldoTotal = reportenegocio.obtenerSaldoTotalCuentas(fechaInicio, fechaFin);
        // Agregamos el saldo total al request para que el JSP lo muestre
        request.setAttribute("saldoTotal", saldoTotal);
        request.setAttribute("tipoReporte", "saldoTotal");
    	    } else if (request.getParameter("btnReporte2") != null) {
    	        // Lógica para el botón "Generar Informe de Cuentas por Tipo"
    	    	 Map<String, Integer> cuentasPorTipo = (Map<String, Integer>) reportenegocio.obtenerCuentaPorTipo(fechaInicio, fechaFin);
    	         request.setAttribute("cuentasPorTipo", cuentasPorTipo);
    	         request.setAttribute("tipoReporte", "cuentasPorTipo");
    	    }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Reporte.jsp");
        dispatcher.forward(request, response);
        
    }
}

