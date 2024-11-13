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
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
/**
 * Servlet implementation class ServletBuscarClientes
 */
@WebServlet("/ServletBuscarClientes")
public class ServletBuscarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("btnBuscar") != null)
		{
			ClienteNegocio neg = new ClienteNegocioImpl();
			String nombre = request.getParameter("txtNombre"); 
			String apellido = request.getParameter("txtApellido");
			String email = request.getParameter("txtEmail"); 
			String dni = request.getParameter("txtDNI");
	        ArrayList<Cliente> objs = neg.Obtener(nombre, apellido, email, dni);

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
