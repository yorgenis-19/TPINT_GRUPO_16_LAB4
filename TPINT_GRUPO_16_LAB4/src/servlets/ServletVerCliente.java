package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.UsuarioTipo;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioTipoNegocioImpl;
/**
 * Servlet implementation class ServletVerCliente
 */
@WebServlet("/ServletVerCliente")
public class ServletVerCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVerCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("btnVer") != null)
		{
			ClienteNegocio neg = new ClienteNegocioImpl();
			
	        Cliente obj = neg.Obtener(Integer.parseInt(request.getParameter("Id")));

	    	ArrayList<Provincia> provincias = new ProvinciaNegocioImpl().ObtenerTodos();
	    	ArrayList<Localidad> localidades = new LocalidadNegocioImpl().ObtenerPorProvincia(obj.getProvinciaId());

	        request.setAttribute("Provincias", provincias);
	        request.setAttribute("Localidades", localidades);
	    	
	        request.setAttribute("ClienteActual", obj);
	        RequestDispatcher rd = request.getRequestDispatcher("/ABMCliente.jsp");
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		if(request.getParameter("btnVer") != null)
		{
			ClienteNegocio neg = new ClienteNegocioImpl();
			
	    	ArrayList<UsuarioTipo> tipos = new UsuarioTipoNegocioImpl().ObtenerTodos();
	    	ArrayList<Provincia> provincias = new ProvinciaNegocioImpl().ObtenerTodos();
	    	ArrayList<Localidad> localidades = new LocalidadNegocioImpl().ObtenerPorProvincia(1);

	        request.setAttribute("UsuarioTipos", tipos);
	        request.setAttribute("Provincias", provincias);
	        request.setAttribute("Localidades", localidades);
	        request.setAttribute("ProvinciaSeleccionada", provincias.get(0).getId());
	    	
	        RequestDispatcher rd = request.getRequestDispatcher("/ABMCliente.jsp");
	        rd.forward(request, response);
		}
	}

}
