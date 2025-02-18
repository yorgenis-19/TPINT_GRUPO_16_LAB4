
package servlets;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Prestamo;
import entidad.CuotaPrestamo;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotasNegocioImpl;
import negocioImpl.PrestamosNegocioImpl;

/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
*/


/**
 * Servlet implementation class ServletCuota
 */
@WebServlet("/ServletCuota")
public class ServletCuota extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletCuota() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("OP");
        System.out.println("Operación recibida: " + op);
        
        if ("VER_PRESTAMOS".equals(op)) {
            int nroCuenta = -1;
            int idPrestamo = -1;
            
            // Validación de los parámetros NroCuenta e idPrestamo
            String nroCuentaStr = request.getParameter("NroCuenta");
            String idPrestamoStr = request.getParameter("idPrestamo");
            
            if (nroCuentaStr != null && !nroCuentaStr.isEmpty()) {
                try {
                    nroCuenta = Integer.parseInt(nroCuentaStr);
                    System.out.println("Número de cuenta seleccionada: " + nroCuenta);
                } catch (NumberFormatException e) {
                    response.sendRedirect("error.jsp?error=Cuenta inválida");
                    return;
                }
            } else {
                response.sendRedirect("error.jsp?error=Falta el número de cuenta");
                return;
            }
            
            if (idPrestamoStr != null && !idPrestamoStr.isEmpty()) {
                try {
                    idPrestamo = Integer.parseInt(idPrestamoStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect("error.jsp?error=ID de préstamo inválido");
                    return;
                }
            } else {
                response.sendRedirect("error.jsp?error=Falta el ID de préstamo");
                return;
            }

            // Obtener datos necesarios
            CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
            PrestamosNegocioImpl prestamoNegocio = new PrestamosNegocioImpl();
            
            // Obtener cuenta y cliente
            Cuenta cuenta = cuentaNegocio.ObtenerCuentaxNroCuenta(nroCuenta);
            if (cuenta == null) {
                response.sendRedirect("error.jsp?error=Cuenta no encontrada");
                return;
            }

            // Verificar que la cuenta tenga un cliente asociado
            if (cuenta.getCliente() == null) {
                response.sendRedirect("error.jsp?error=Cliente no asociado a la cuenta");
                return;
            }
            
            int clienteId = cuenta.getCliente().getId();
            
            // Obtener préstamos y cuotas
            List<Prestamo> prestamos = prestamoNegocio.BuscarByIdCliente(clienteId);
            List<CuotaPrestamo> cuotas = prestamoNegocio.ObtenerCuota(idPrestamo);
            
            // Obtener cuentas para el dropdown
            List<Cuenta> cuentas = cuentaNegocio.ObtenerCuentasxClienteID(clienteId);
            
            // Setear atributos en el request
            request.setAttribute("Prestamos", prestamos);
            request.setAttribute("Cuotas", cuotas);
            request.setAttribute("NroCuenta", nroCuenta);
            request.setAttribute("Saldo", BigDecimal.valueOf(cuenta.getMonto()));
            request.getSession().setAttribute("cuentasDDL", cuentas);
            request.setAttribute("cuentaSeleccionada", cuenta);
            
            // Redirigir a la JSP
            RequestDispatcher rd = request.getRequestDispatcher("PagarPrestamo.jsp");
            rd.forward(request, response);
            return;
        }
        
        // Si no es VER_PRESTAMOS, muestra el mensaje por defecto
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		System.out.println("Valor de OPPAGARCUOTA: " + request.getParameter("OPPAGARCUOTA"));
		if(request.getParameter("OPPAGARCUOTA")!=null) {
			System.out.println("entra al OPPAGARCUOTA:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx xx x x xxxxxxxxxxxxxxxxxx************************ ");
			if(pagarCuota(request, response)) {
				int nroCta = Integer.parseInt(request.getParameter("NroCuenta"));
				CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
				int clienteId = Integer.parseInt(request.getParameter("clienteId"));
				
				List<Cuenta> cuentasActualizadas = cuentaNegocio.ObtenerCuentasxClienteID(clienteId);
			    request.getSession().setAttribute("cuentas", cuentasActualizadas);
				request.setAttribute("CuotaPaga", true);
				}
			else
				request.setAttribute("CuotaPaga", false);
			rd = request.getRequestDispatcher("/Cliente.jsp");
			rd.forward(request, response);
		}
	}
	
	protected boolean pagarCuota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Logs para verificar los valores recibidos
	    System.out.println("=== Iniciando método pagarCuota ===");
	    System.out.println("IdCuotaAPagar: " + request.getParameter("IdCuotaAPagar"));
	    System.out.println("NroCuenta: " + request.getParameter("NroCuenta"));
	    System.out.println("clienteId: " + request.getParameter("clienteId"));
	    System.out.println("impCuota: " + request.getParameter("impCuota"));

	    try {
	        int idCuota = Integer.parseInt(request.getParameter("IdCuotaAPagar"));
	        int nroCuenta = Integer.parseInt(request.getParameter("NroCuenta"));
	        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
	        BigDecimal importeBig = new BigDecimal(request.getParameter("impCuota"));

	        System.out.println("Valores convertidos con éxito:");
	        System.out.println("idCuota: " + idCuota);
	        System.out.println("nroCuenta: " + nroCuenta);
	        System.out.println("clienteId: " + clienteId);
	        System.out.println("importeBig: " + importeBig);

	        CuotasNegocioImpl cuotasneg = new CuotasNegocioImpl();
	        boolean resultado = cuotasneg.PagarCuota(nroCuenta, idCuota, importeBig, "ok", clienteId);

	        System.out.println("Resultado de PagarCuota: " + resultado);
	        return resultado;
	    } catch (NumberFormatException e) {
	        System.err.println("Error de formato numérico en pagarCuota: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Excepción inesperada en pagarCuota: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}
}