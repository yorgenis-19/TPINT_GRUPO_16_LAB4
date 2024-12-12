package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.CuotaPrestamo;
import entidad.Prestamo;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.PrestamosNegocioImpl;





@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletPrestamos() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("pagoPrestamos")!=null) {
			obtenerPrestamos(request, response);
	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void obtenerPrestamos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		Usuario usuario = new Usuario();
		Cliente cliente = new Cliente();
		int nroCuenta = Integer.parseInt(request.getParameter("pagoPrestamos"));
		ArrayList<Prestamo> prestamosList = new ArrayList<Prestamo>();
		ArrayList<CuotaPrestamo> cuotaList = new ArrayList<CuotaPrestamo>();
		usuario = (Usuario)request.getSession().getAttribute("UsuarioActual");
		
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		PrestamosNegocioImpl prestamos = new PrestamosNegocioImpl();
		cliente = clienteNegocio.ObtenerPorUsuario(usuario.getId());
		prestamosList=(ArrayList<Prestamo>) prestamos.BuscarByIdCliente(cliente.getId());	
		
		ArrayList<Prestamo> prestamosActivos  = new ArrayList<Prestamo>();
		ListIterator<Prestamo> it = prestamosList.listIterator();
		BigDecimal csaldo = new BigDecimal(request.getParameter("getSaldo"));
		
		/*Levanto solo los prestamos activos*/
		while (it.hasNext()) {
			Prestamo p = it.next();
			if(p.getIdEstadoPrestamo() == 2) {
				prestamosActivos.add(p);
				cuotaList.addAll((ArrayList<CuotaPrestamo>)prestamos.ObtenerCuota(p.getId()));
			}
		} 
		
		if (prestamosActivos != null && cuotaList != null) {
			request.setAttribute("Prestamos", prestamosActivos);
			request.setAttribute("NroCuenta", nroCuenta);	
			request.setAttribute("Cuotas", cuotaList);	
			request.setAttribute("Saldo", csaldo);
		}
		else {
			request.setAttribute("SinPrestamos", true);
		}
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/PagarPrestamo.jsp");
		rd.forward(request, response);
		
	}

}
