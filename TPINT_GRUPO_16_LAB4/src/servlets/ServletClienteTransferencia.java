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
import entidad.MovimientoTipo;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoTipoNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletClienteTransferencia
 */
@WebServlet("/ServletClienteTransferencia")
public class ServletClienteTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienteTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnClienteTransferencia") != null)
		{
			CuentaNegocio negCuenta = new CuentaNegocioImpl();
			int usuarioId = ((Usuario)request.getSession().getAttribute("UsuarioActual")).getId();
			ArrayList<Cuenta> cuentas = negCuenta.ObtenerPorUsuario(usuarioId);
			request.setAttribute("Lista_Cuentas", cuentas);
			
			ArrayList<MovimientoTipo> tipos = new MovimientoTipoNegocioImpl().ObtenerTodos();
			request.setAttribute("Lista_MovimientoTipos", tipos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ClienteTransferencia.jsp");
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
