package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import entidad.Usuario;
import entidad.UsuarioTipo;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import negocioImpl.UsuarioTipoNegocioImpl;
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
		boolean error = false;
		if(request.getParameter("btnGuardar") != null)
		{
			ClienteNegocio neg = new ClienteNegocioImpl();
			int id = Integer.parseInt(request.getParameter("txtId"));
			String email = request.getParameter("txtEmail"); 
			String dni = request.getParameter("txtDNI"); 
			String cuil = request.getParameter("txtCUIL");
			String nombre = request.getParameter("txtNombre"); 
			String apellido = request.getParameter("txtApellido");
			String sexo = request.getParameter("cmbSexo"); 
			String telefono = request.getParameter("txtTelefono");
			String direccion = request.getParameter("txtDireccion");
			int provinciaId = Integer.parseInt(request.getParameter("cmbProvincia"));
			int localidadId = Integer.parseInt(request.getParameter("cmbLocalidad"));
			Date fechaNacimiento = new Date();
			try {
				fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtFechaNacimiento"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LocalDate birthLocalDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate currentDate = LocalDate.now();
	        int age = Period.between(birthLocalDate, currentDate).getYears();
			if(age < 18) {
				error = true;
				request.setAttribute("MENOR_EDAD", true);
			}
			if(neg.ExisteMail(id, email)) {
				error = true;
				request.setAttribute("MAIL_EXISTENTE", true);
			}
			if(neg.ExisteDNI(id, dni)) {
				error = true;
				request.setAttribute("DNI_EXISTENTE", true);
			}
			if(neg.ExisteCUIL(id, cuil)) {
				error = true;
				request.setAttribute("CUIL_EXISTENTE", true);
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
			obj.setDireccion(direccion);
			obj.setProvinciaId(provinciaId);
			obj.setLocalidadId(localidadId);
			
			if(id == 0) {
				String usuario = request.getParameter("txtUsuario");
				if(neg.ExisteUsuario(usuario)) 
				{
					error = true;
					request.setAttribute("USUARIO_EXISTENTE", true);
				}
				String clave = request.getParameter("txtClave");
				String claveConfirmacion = request.getParameter("txtClaveConfirmar");
				if(!clave.equals(claveConfirmacion))
				{
					error = true;
					request.setAttribute("CLAVE_DISTINTA", true);
				}
				if(!error)
				{
					Usuario usu = new Usuario();
					usu.setNombre(usuario);
					usu.setClave(clave);
					usu.setActivo(true);
					UsuarioTipo tipo = new UsuarioTipoNegocioImpl().Obtener("Cliente");
					usu.setTipo(tipo);
					UsuarioNegocio negUsuario = new UsuarioNegocioImpl();
					negUsuario.Guardar(usu);				
					obj.setUsuario(negUsuario.Obtener(usu.getNombre(), usu.getClave()));
					
				}
			}
			request.setAttribute("ClienteActual", obj);

	        ArrayList<Provincia> provincias = new ProvinciaNegocioImpl().ObtenerTodos();
	        ArrayList<Localidad> localidades = new LocalidadNegocioImpl().ObtenerTodos();
	        
	        
	        request.setAttribute("Provincias", provincias);
	        request.setAttribute("Localidades", localidades);
	        
			if(error)
			{
				
		        RequestDispatcher rd = request.getRequestDispatcher("/ABMCliente.jsp");
		        rd.forward(request, response);
			}
			else
			{

		        int filas = neg.Guardar(obj);

		        request.setAttribute("Filas", filas);
		        request.setAttribute("ClienteActual", obj);
		        
		        RequestDispatcher rd = request.getRequestDispatcher("/ABMCliente.jsp");
		        rd.forward(request, response);
			}
			
		}
		
		doGet(request, response);
	}

}
