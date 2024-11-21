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
		if (request.getParameter("btnCrearC") != null) {
	        String idClienteParam = request.getParameter("IDCliente");
	        int clienteId = 0;

	        if (idClienteParam != null && !idClienteParam.isEmpty()) {
	            clienteId = Integer.parseInt(idClienteParam);
	        } else {
	            request.setAttribute("error", "El ID del cliente no puede estar vacío.");
	            RequestDispatcher rd = request.getRequestDispatcher("/CrearCuenta.jsp");
	            rd.forward(request, response);
	            return;
	        }

	        // Obtener cliente
	        Cliente cliente = clienteNeg.Obtener(clienteId);

	        // Validar si el cliente existe
	        if (cliente == null || cliente.getId() == 0) {
	            request.setAttribute("error", "El cliente no existe.");
	            RequestDispatcher rd = request.getRequestDispatcher("/CrearCuenta.jsp");
	            rd.forward(request, response);
	            return;
	        }

	        // Pasar los datos del cliente al JSP para continuar con la creación de la cuenta
	        request.setAttribute("cliente", cliente);
	        RequestDispatcher rd = request.getRequestDispatcher("/CrearCuenta.jsp");
	        rd.forward(request, response);
	    }

	    // Manejar el evento del botón "Confirmar Cuenta"
	    /*if (request.getParameter("btnConfirmarCuenta") != null) {
	        try {
	            int clienteId = Integer.parseInt(request.getParameter("IDCliente"));
	            double monto = Double.parseDouble(request.getParameter("Monto"));
	            int tipoCuenta = Integer.parseInt(request.getParameter("TipoCuenta"));

	            // Llamar al método para crear la cuenta
	           int resultado = cuentaNeg.CrearCuenta(clienteId, tipoCuenta);

	            // Verificar si la cuenta se creó correctamente
	            if (resultado > 0) {
	                request.setAttribute("mensaje", "Cuenta creada exitosamente.");
	            } else {
	                request.setAttribute("error", "No se pudo crear la cuenta.");
	            }

	        } catch (NumberFormatException e) {
	            request.setAttribute("error", "Datos inválidos proporcionados. Verifique el monto y el tipo de cuenta.");
	        } catch (Exception e) {
	            request.setAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
	        }

	        // Redirigir al resultado
	        RequestDispatcher rd = request.getRequestDispatcher("/Resultado.jsp");
	        rd.forward(request, response);
	    }*/
	}
}
