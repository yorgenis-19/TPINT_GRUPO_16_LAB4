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
import entidad.CuentaTipo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.CuentaTipoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuentaTipoNegocioImpl;
/**
 * Servlet implementation class ServletActualizarCuenta
 */
@WebServlet("/ServletActualizarCuenta")
public class ServletActualizarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	CuentaTipoNegocio cuentaTipoNeg = new CuentaTipoNegocioImpl();
	ClienteNegocio clienteNeg = new ClienteNegocioImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActualizarCuenta() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CuentaTipo> tiposDeCuenta = cuentaTipoNeg.ObtenerTodosLosTiposDeCuenta();
		request.setAttribute("tiposDeCuenta", tiposDeCuenta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("L.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cuentaId = Integer.parseInt(request.getParameter("cuentaId"));
        boolean estado = Integer.parseInt(request.getParameter("estado")) == 1;  // Convertir a boolean
        
        boolean actualizado = cuentaNeg.actualizarEstadoCuenta(cuentaId, estado);
        
        if (actualizado) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
	}
	}


