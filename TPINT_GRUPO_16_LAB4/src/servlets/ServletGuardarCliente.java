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
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
/**
 * Servlet implementation class ServletGuardarCliente
 */
@WebServlet("/ServletGuardarCliente")
public class ServletGuardarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuardarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("btnGuardar") != null)
		{
			ClienteNegocio neg = new ClienteNegocioImpl();
			int id = Integer.parseInt(request.getParameter("txtId"));
			String nombre = request.getParameter("txtNombre"); 
			String apellido = request.getParameter("txtApellido");
			String email = request.getParameter("txtEmail"); 
			String sexo = request.getParameter("cmbSexo"); 
			String dni = request.getParameter("txtDNI"); 
			String cuil = request.getParameter("txtCUIL"); 
			String telefono = request.getParameter("txtTelefono");
			Date fechaNacimiento = new Date();
			try {
				fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtFechaNacimiento"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cliente obj = new Cliente();
			if(id > 0) {
				obj = neg.Obtener(id);
			}
			obj.setNombre(nombre);
			obj.setApellido(apellido);
			obj.setEmail(email);
			obj.setSexo(sexo);
			obj.setDni(dni);
			obj.setCuil(cuil);
			obj.setTelefono(telefono);
			obj.setFechaNacimiento(fechaNacimiento);
	        int filas = neg.Guardar(obj);

	        request.setAttribute("Filas", filas);
	        RequestDispatcher rd = request.getRequestDispatcher("/BuscadorCliente.jsp");
	        rd.forward(request, response);
		}
		
		doGet(request, response);
	}

}
