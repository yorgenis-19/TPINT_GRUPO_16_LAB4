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
import entidad.Cuenta;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletBuscarCuenta
 */
@WebServlet("/ServletBuscarCuenta")
public class ServletBuscarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	ClienteNegocio clienteNeg = new ClienteNegocioImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnBuscarC") != null)
		{
			 Cliente cliente = new Cliente();
		        ArrayList<Cuenta> listaCuentas = new ArrayList<>();

		        String idClienteParam = request.getParameter("IDCliente");
		        int ID = 0;

		        try {
		            // Validar y convertir el parámetro
		            if (idClienteParam != null && !idClienteParam.isEmpty()) {
		                ID = Integer.parseInt(idClienteParam);
		            } else {
		                throw new IllegalArgumentException("El ID del cliente no puede estar vacío.");
		            }

		            // Obtener cuentas por cliente ID
		            listaCuentas = cuentaNeg.ObtenerCuentasxClienteID(ID);

		            if (listaCuentas != null) {
		                cliente = clienteNeg.ObtenerPorUsuario(ID);

		                request.setAttribute("listaCuentas", listaCuentas);
		                request.setAttribute("cliente", cliente);

		                RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscadorCuenta.jsp");
		                dispatcher.forward(request, response);
		            } else {
		                // Manejar el caso de cliente sin cuentas
		                request.setAttribute("error", "No se encontraron cuentas para el cliente con ID: " + ID);
		                RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscadorCuenta.jsp");
		                dispatcher.forward(request, response);
		            }

		        } catch (NumberFormatException e) {
		            // Manejo de errores de conversión
		            request.setAttribute("error", "El ID ingresado no es válido. Debe ser un número.");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscadorCuenta.jsp");
		            dispatcher.forward(request, response);

		        } catch (IllegalArgumentException e) {
		            // Manejo de errores de validación
		            request.setAttribute("error", e.getMessage());
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscadorCuenta.jsp");
		            dispatcher.forward(request, response);

		        } catch (Exception e) {
		            // Manejo de otros errores
		            e.printStackTrace();
		            request.setAttribute("error", "Se produjo un error al procesar la solicitud.");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscadorCuenta.jsp");
		            dispatcher.forward(request, response);
		        }
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
