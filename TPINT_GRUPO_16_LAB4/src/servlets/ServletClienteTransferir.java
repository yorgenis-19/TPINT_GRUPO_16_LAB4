package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.MovimientoTipo;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.MovimientoTipoNegocioImpl;

/**
 * Servlet implementation class ServletClienteTransferir
 */
@WebServlet("/ServletClienteTransferir")
public class ServletClienteTransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienteTransferir() {
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
		if(request.getParameter("btnTransferir") != null) {
			CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
			Cuenta cuentaOrigen = cuentaNeg.Obtener(Integer.parseInt(request.getParameter("cmbCuenta")));
			Cuenta cuentaDestino = cuentaNeg.ObtenerPorCBU(Long.parseLong(request.getParameter("txtCBUDestino")));
			float importe = Float.parseFloat(request.getParameter("txtImporte"));
			String detalle = request.getParameter("txtDetalle");
			CuentaNegocio negCuenta = new CuentaNegocioImpl();
			
			boolean error = false;
			if(cuentaDestino == null || cuentaDestino.getId() == 0 || cuentaDestino.getCBU() == cuentaOrigen.getCBU())
			{
				error = true;
				request.setAttribute("ERROR_CuentaDestino", true);
			}
			if(importe > cuentaOrigen.getMonto())
			{
				error = true;
				request.setAttribute("ERROR_Importe", true);
			}
			if(error) 
			{
				int usuarioId = ((Usuario)request.getSession().getAttribute("UsuarioActual")).getId();
				ArrayList<Cuenta> cuentas = negCuenta.ObtenerPorUsuario(usuarioId);
				request.setAttribute("Lista_Cuentas", cuentas);
				ArrayList<MovimientoTipo> tipos = new MovimientoTipoNegocioImpl().ObtenerTodos();
				request.setAttribute("Lista_MovimientoTipos", tipos);
				RequestDispatcher rd = request.getRequestDispatcher("/ClienteTransferencia.jsp");
				rd.forward(request, response);
			}
			else
			{
				Movimiento mov = new Movimiento();
				mov.setCuentaOrigen(cuentaOrigen);
				mov.setCuentaDestino(cuentaDestino);
				mov.setImporte(importe);
				mov.setDetalle(detalle);
				MovimientoTipo tipo = new MovimientoTipoNegocioImpl().Obtener("Transferencia");
				mov.setTipo(tipo);
				int filas = new MovimientoNegocioImpl().Guardar(mov);
				
				cuentaOrigen.setMonto(cuentaOrigen.getMonto() - importe);
				cuentaNeg.Guardar(cuentaOrigen);
				cuentaDestino.setMonto(cuentaDestino.getMonto() + importe);
				cuentaNeg.Guardar(cuentaDestino);

				ArrayList<Movimiento> movimientos = new MovimientoNegocioImpl().ObtenerPorCuenta(cuentaOrigen.getId());
				request.setAttribute("Movimientos", movimientos);
				request.setAttribute("CuentaSeleccionada", cuentaOrigen);

				RequestDispatcher rd = request.getRequestDispatcher("/ClienteCuenta.jsp");
				rd.forward(request, response);
			}
		}
	}

}
