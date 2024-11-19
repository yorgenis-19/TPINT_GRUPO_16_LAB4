package servlets;

import java.io.IOException;

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
 * Servlet implementation class ServletEliminarUsuario
 */
@WebServlet("/ServletEliminarUsuario")
public class ServletEliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnEliminarUsuario") != null)
		{
			UsuarioNegocio neg = new UsuarioNegocioImpl();
			int id = Integer.parseInt(request.getParameter("usuarioId"));
			
			Usuario obj = neg.Obtener(id);
			obj.setActivo(false);
			int filas = new UsuarioNegocioImpl().Guardar(obj);
			
	        request.setAttribute("Filas", filas);
	        RequestDispatcher rd = request.getRequestDispatcher("/ABLUsuario.jsp");
	        rd.forward(request, response);
		}
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
