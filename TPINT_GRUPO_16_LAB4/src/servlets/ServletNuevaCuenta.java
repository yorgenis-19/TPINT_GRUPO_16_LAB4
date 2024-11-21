package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletNuevaCuenta
 */
@WebServlet("/ServletNuevaCuenta")
public class ServletNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	ClienteNegocio clienteNeg = new ClienteNegocioImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNuevaCuenta() {
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
		 String action = request.getParameter("action");

	        if ("buscarCliente".equals(action)) {
	            buscarCliente(request, response);
	        } else if ("crearCuenta".equals(action)) {
	            crearCuenta(request, response);
	        }
	}
	
	private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del cliente desde el formulario
        String idClienteParam = request.getParameter("IDCliente");
        int idCliente = 0;

        if (idClienteParam != null && !idClienteParam.isEmpty()) {
            idCliente = Integer.parseInt(idClienteParam);
        }

        // Llamar al método del DAO para obtener los datos del cliente
        Cliente cliente = clienteNeg.ObtenerClientePorId(idCliente);

        if (cliente != null) {
            request.setAttribute("cliente", cliente);
        } else {
            request.setAttribute("error", "Cliente no encontrado.");
        }

        // Redirigir nuevamente al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("NuevaCuenta.jsp");
        dispatcher.forward(request, response);
    }

    private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    }
	
}
