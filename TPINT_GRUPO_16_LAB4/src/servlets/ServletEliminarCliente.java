package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Usuario;
import entidad.UsuarioTipo;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import negocioImpl.UsuarioTipoNegocioImpl;

/**
 * Servlet implementation class ServletEliminarCliente
 */
@WebServlet("/ServletEliminarCliente")
public class ServletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarCliente() {
        super();
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

		if(request.getParameter("btnEliminarCliente") != null)
		{
			ClienteNegocio neg = new ClienteNegocioImpl();
			int id = Integer.parseInt(request.getParameter("clientId"));
			
			Cliente obj = neg.Obtener(id);
			Usuario user = obj.getUsuario();
			user.setActivo(false);
			int filas = new UsuarioNegocioImpl().Guardar(user);
			
	        request.setAttribute("Filas", filas);
	        RequestDispatcher rd = request.getRequestDispatcher("/BuscadorCliente.jsp");
	        rd.forward(request, response);
		}
		
		
		doGet(request, response);
	}

}
