package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaTipo;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.CuentaTipoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuentaTipoNegocioImpl;

/**
 * Servlet implementation class ServletNuevaCuenta
 */
@WebServlet("/ServletNuevaCuenta")
public class ServletNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	CuentaTipoNegocio cuentaTipoNeg = new CuentaTipoNegocioImpl();
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

		// Obtener los tipos de cuenta
	    ArrayList<CuentaTipo> tiposDeCuenta = cuentaTipoNeg.ObtenerTodosLosTiposDeCuenta();

	    // Pasar los tipos de cuenta al JSP
	    request.setAttribute("tiposDeCuenta", tiposDeCuenta);

	    // Redirigir al JSP NuevaCuenta.jsp
	    RequestDispatcher dispatcher = request.getRequestDispatcher("NuevaCuenta.jsp");
	    dispatcher.forward(request, response);
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
        ArrayList<CuentaTipo> tiposDeCuenta = cuentaTipoNeg.ObtenerTodosLosTiposDeCuenta();
        request.setAttribute("tiposDeCuenta", tiposDeCuenta);

        // Redirigir nuevamente al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("NuevaCuenta.jsp");
        dispatcher.forward(request, response);
    }

    private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 try {
    	        // Obtener datos del formulario
    	        int clienteId = Integer.parseInt(request.getParameter("IDCliente"));
    	        float monto = Float.parseFloat(request.getParameter("Monto"));
    	        int tipoId = Integer.parseInt(request.getParameter("TipoCuenta"));

    	        // Validar la cantidad de cuentas del cliente
    	        if (!cuentaNeg.ValidarCantidad(clienteId)) {
    	            // Si el cliente ya tiene 3 cuentas activas, mostrar el mensaje de error
    	            request.setAttribute("mensajeError", "El cliente ya tiene el máximo permitido de 3 cuentas activas.");
    	            RequestDispatcher dispatcher = request.getRequestDispatcher("NuevaCuenta.jsp");
    	            dispatcher.forward(request, response);
    	            return; // Evitar continuar con la creación de la cuenta
    	        }

    	        // Validar el monto
    	        if (monto <= 0) {
    	            request.setAttribute("mensajeError", "El monto debe ser mayor que cero.");
    	            RequestDispatcher dispatcher = request.getRequestDispatcher("NuevaCuenta.jsp");
    	            dispatcher.forward(request, response);
    	            return;
    	        }

    	        // Crear el objeto Cliente y Tipo
    	        Cliente cliente = new Cliente();
    	        cliente.setId(clienteId);

    	        CuentaTipo tipo = new CuentaTipo();
    	        tipo.setId(tipoId);

    	        // Crear el objeto Cuenta
    	        Cuenta cuenta = new Cuenta();
    	        cuenta.setCliente(cliente);
    	        cuenta.setMonto(monto);
    	        cuenta.setTipo(tipo);

    	        // Llamar a la capa de negocio para insertar la cuenta
    	        boolean cuentaCreada = cuentaNeg.insertarCuenta(cuenta);

    	        if (cuentaCreada) {
    	            request.setAttribute("mensajeExito", "Cuenta creada con éxito.");
    	            request.setAttribute("cliente", cuenta.getCliente());
    	        } else {
    	            request.setAttribute("mensajeError", "Error al crear la cuenta.");
    	        }

    	    } catch (NumberFormatException e) {
    	        // Este bloque maneja errores de formato de número, como cuando no se ingresa un número válido
    	        e.printStackTrace();
    	        request.setAttribute("mensajeError", "Datos inválidos. Por favor, ingresa valores correctos.");
    	    } catch (Exception e) {
    	        // Este bloque maneja otros tipos de excepciones que no hemos anticipado
    	        e.printStackTrace();
    	        request.setAttribute("mensajeError", "Hubo un error al procesar la solicitud.");
    	    }

    	    // Redirigir nuevamente al JSP
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("NuevaCuenta.jsp");
    	    dispatcher.forward(request, response);
    }
	
}
