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
import entidad.Localidad;
import entidad.Movimiento;
import entidad.Provincia;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class ServletClienteDatos
 */
@WebServlet("/ServletClienteDatos")
public class ServletClienteDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienteDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnClienteDatos") != null)
		{
			Usuario usuario = (Usuario)request.getSession().getAttribute("UsuarioActual");
			Cliente obj = new ClienteNegocioImpl().ObtenerPorUsuario(usuario.getId());
			Provincia provincia = new ProvinciaNegocioImpl().Obtener(obj.getProvinciaId());
			Localidad localidad = new LocalidadNegocioImpl().Obtener(obj.getLocalidadId());
			request.setAttribute("Cliente", obj);
			request.setAttribute("ProvinciaNombre", provincia.getNombre());
			request.setAttribute("LocalidadNombre", localidad.getNombre());
			
			RequestDispatcher rd = request.getRequestDispatcher("/ClienteDatos.jsp");
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
