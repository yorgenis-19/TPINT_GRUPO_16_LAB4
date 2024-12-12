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
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotasNegocioImpl;

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
		// TODO Auto-generated method stub
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
		int idCuota =  Integer.parseInt(request.getParameter("IdCuotaAPagar"));
		int NroCuenta = Integer.parseInt(request.getParameter("NroCuenta"));
		int clienteId = Integer.parseInt(request.getParameter("clienteId"));
		System.out.println("CLIENTEEEEEEEEEEEEEEEEEEEEEEEEE IDIDIDIDIDIDIDIDI" + clienteId);
		String importe = request.getParameter("impCuota");
		BigDecimal importeBig = new BigDecimal(importe);
		CuotasNegocioImpl cuotasneg =  new CuotasNegocioImpl();
		if(cuotasneg.PagarCuota(NroCuenta, idCuota, importeBig, "ok", clienteId))
			return true;
		else
			return false;
	}

}
