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
			CuentaNegocio ctaNeg = new CuentaNegocioImpl();
			
			obj = negocio.Obtener(nombre, clave);
			
			List<Cuenta> lCta = ctaNeg.ObtenerPorUsuario(obj.getId());
			System.out.println("objjjjj: " + lCta);
			
			if(obj.getId() == 0 || !obj.getActivo()) {
				request.setAttribute("usuarioIncorrecto", true);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			} 
			else {
				request.getSession().setAttribute("UsuarioActual", obj);
				request.getSession().setAttribute("cuentasDDL", lCta);
				
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

}
