package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        reportenegocio=new ReporteNegocioImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        // Obtenemos el saldo total de las cuentas utilizando la capa de negocio
        double saldoTotal = reportenegocio.obtenerSaldoTotalCuentas(fechaInicio, fechaFin);

        // Agregamos el saldo total al request para que el JSP lo muestre
        request.setAttribute("saldoTotal", saldoTotal);

        // Redirigimos a la vista de informe de cuentas
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Reporte.jsp");
        dispatcher.forward(request, response);
	}
	

}
