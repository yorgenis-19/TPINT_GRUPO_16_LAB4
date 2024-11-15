package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletNuevaCuenta
 */
@WebServlet("/ServletNuevaCuenta")
public class ServletBuscarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnBuscar") != null)
		{
			CuentaNegocio neg = new CuentaNegocioImpl();
			int clienteId = Integer.parseInt(request.getParameter("cmbCliente")); 
			int cbu = Integer.parseInt(request.getParameter("txtCBU"));
			int tipo = Integer.parseInt(request.getParameter("cmbTipo")); 
			float monto = Float.parseFloat(request.getParameter("txtMonto"));
	        ArrayList<Cuenta> objs = neg.Obtener(clienteId, cbu, tipo, monto);

	        request.setAttribute("ClientesResultado", objs);
	        RequestDispatcher rd = request.getRequestDispatcher("/BuscadorCliente.jsp");
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
