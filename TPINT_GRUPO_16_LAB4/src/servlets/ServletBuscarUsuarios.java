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
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletBuscarUsuarios
 */
@WebServlet("/ServletBuscarUsuarios")
public class ServletBuscarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnBuscar") != null)
		{
			UsuarioNegocio neg = new UsuarioNegocioImpl();
			String nombre = request.getParameter("txtNombre");
			int tipo = Integer.parseInt(request.getParameter("cmbTipo"));
			String activo = request.getParameter("cmbActivo");
			
	        ArrayList<Usuario> objs = neg.Obtener(nombre, tipo, activo);

	        request.setAttribute("UsuariosResultado", objs);
	        request.setAttribute("Filtro_Nombre", nombre);
	        request.setAttribute("Filtro_Tipo", tipo);
	        request.setAttribute("Filtro_Activo", activo);
	        RequestDispatcher rd = request.getRequestDispatcher("/ABLUsuario.jsp");
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
