package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletListarCuentas
 */
@WebServlet("/ServletListarCuentas")
public class ServletListarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
private CuentaNegocio cuentaNeg;
	
    public ServletListarCuentas() {
        super();
        cuentaNeg = new CuentaNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener todas las cuentas
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
    	listaCuentas = cuentaNeg.listarTodasLAsCuentas();
    	request.setAttribute("listaCuentas", listaCuentas);

    	if(listaCuentas != null) {
        	request.setAttribute("listaCuentas", listaCuentas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarCuentas.jsp");
            dispatcher.forward(request, response);
            
        	}
        	 else {
    			 System.out.println("LISTA CUENTAS NULA");
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
