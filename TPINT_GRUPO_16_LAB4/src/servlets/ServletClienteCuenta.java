package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class ServletClienteCuenta
 */
@WebServlet("/ServletClienteCuenta")
public class ServletClienteCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienteCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnClienteCuenta") != null)
		{
			Cuenta obj = new CuentaNegocioImpl().Obtener(Integer.parseInt(request.getParameter("cuentaId")));
			request.setAttribute("CuentaSeleccionada", obj);
			ArrayList<Movimiento> movimientos = new MovimientoNegocioImpl().ObtenerPorCuenta(obj.getId());
			request.setAttribute("Movimientos", movimientos);
			RequestDispatcher rd = request.getRequestDispatcher("/ClienteCuenta.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
