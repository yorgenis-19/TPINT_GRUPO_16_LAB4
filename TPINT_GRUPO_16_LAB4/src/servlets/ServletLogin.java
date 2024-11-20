package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import negocio.UsuarioNegocio;
import entidad.Cuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import src.main.java.servlets.Boolean;
import src.main.java.servlets.Cuenta;
import src.main.java.servlets.CuentaNegocio;
import src.main.java.servlets.CuentaNegocioImpl;
import src.main.java.servlets.HttpSession;
import src.main.java.servlets.List;
import src.main.java.servlets.String;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filas = 0;
		if(request.getParameter("btnIngresar") != null) 
		{
			Usuario obj = new Usuario();
			UsuarioNegocio negocio = new UsuarioNegocioImpl();
			String nombre = request.getParameter("txtUsuario");
			String clave = request.getParameter("txtClave");
			
			obj = negocio.Obtener(nombre, clave);
			if(obj.getId() == 0) {
				request.setAttribute("usuarioIncorrecto", true);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			} 
			else {
				request.getSession().setAttribute("UsuarioActual", obj);
				
				if(new String("Cliente").equals(obj.getTipo().getDescripcion()))
				{
					RequestDispatcher rd = request.getRequestDispatcher("/Cliente.jsp");	
					rd.forward(request, response);
				}
				else
				{
					RequestDispatcher rd = request.getRequestDispatcher("/Administrador.jsp");	
					rd.forward(request, response);
				}
			}

			//request.getSession().setAttribute("UsuarioActual", obj);
			//RequestDispatcher rd = request.getRequestDispatcher("/Cliente.jsp");
			//rd.forward(request, response);	
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Boolean iniciarSesion(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		String clave = request.getParameter("txtClave");
		java.lang.String usu = request.getParameter("txtUsuario");
		String DNI = request.getParameter("txtDNI");
		usuario.setId(Integer.parseInt(DNI));
		usuario.setNombre(usu);
		usuario.setClave(clave);
		UsuarioNegocioImpl usuNeg = new UsuarioNegocioImpl();

		CuentaNegocio ctaNeg = new CuentaNegocioImpl();
		Cuenta lCta = ctaNeg.Obtener(Integer.parseInt(DNI));

		if (usuNeg.IniciarSesion(usuario)) {
			request.getSession().setAttribute("Usuario", usuario);
			request.getSession().setAttribute("cuentasDDL", lCta);
			cuentasUsuario(request, usuario);
			return true;
		} else
			return false;

	}

}
