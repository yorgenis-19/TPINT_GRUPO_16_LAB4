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

import dao.LocalidadDao;
import dao.ProvinciaDao;
import daoImpl.LocalidadDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

/**
 * Servlet implementation class ServletC
 */
@WebServlet("/ServletProvincia")
public class ServletProvincia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProvincia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("cargaProvincia")!=null)
		{
			ProvinciaDao dao = new ProvinciaDaoImpl();
			ArrayList<Provincia> lista = dao.ObtenerTodos();
			request.setAttribute("Provincias", lista);
		}
		
		if(request.getParameter("cargaLocalidad")!=null)
		{
			//Obtiene las localidades de la provincia seleccionada:
			int provinciaId = Integer.parseInt(request.getParameter("cargaLocalidad"));
			int id = Integer.parseInt(request.getParameter("txtId"));
			
			ClienteNegocio neg = new ClienteNegocioImpl();
	        Cliente obj = new Cliente();
			if(id != 0)
			{
				obj = neg.Obtener(id);
			}
			String nombre = (String)(request.getParameter("txtNombre"));
			String apellido = (String)(request.getParameter("txtApellido"));
			String sexo = (String)(request.getParameter("cmbSexo"));
			String dni = (String)(request.getParameter("txtDNI"));
			String cuil = (String)(request.getParameter("txtCUIL"));
			String telefono = (String)(request.getParameter("txtTelefono"));
			String email = (String)(request.getParameter("txtEmail"));
			Date fechaNacimiento = new Date();
			try {
				fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtFechaNacimiento"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String direccion = (String)(request.getParameter("txtDireccion"));
			obj.setNombre(nombre);
			obj.setApellido(apellido);
			obj.setSexo(sexo);
			obj.setDni(dni);
			obj.setCuil(cuil);
			obj.setTelefono(telefono);
			obj.setEmail(email);
			obj.setFechaNacimiento(fechaNacimiento);
			obj.setDireccion(direccion);
			obj.setProvinciaId(provinciaId);
			request.setAttribute("ClienteActual", obj);
			
			LocalidadDao dl = new LocalidadDaoImpl();
			ArrayList<Localidad> listaL= dl.ObtenerPorProvincia(provinciaId);
			request.setAttribute("Localidades", listaL);	
	
			ProvinciaDao dp = new ProvinciaDaoImpl();
			ArrayList<Provincia> listaP= dp.ObtenerTodos();
			request.setAttribute("Provincias", listaP);
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/ABMCliente.jsp");   
        rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
