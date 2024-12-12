package servlets;

import java.io.IOException;



import entidad.Cliente;
import entidad.Prestamo;
import entidad.Prestamo;

import negocio.PrestamosNegocio;
import negocioImpl.PrestamosNegocioImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/
/**
 * Servlet implementation class ServletPrestamosxAutorizar
 */
@WebServlet("/ServletPrestamosxAutorizar")
public class ServletPrestamosxAutorizar extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamosxAutorizar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	if (request.getParameter("btnRealizarSolicitudPrestamo")!=null) {
			btnRealizarSolicitudPrestamo(request, response);
		}
		if (request.getParameter("getPrestamos") != null) {
			cargarPrestamos(request, response);
		}
		if (request.getParameter("btnAutorizar") != null) {
			updatePrestamo(request, response,2);
		}
		if (request.getParameter("btnRechazar") != null) {
			updatePrestamo(request, response,4);
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/AltaPrestamo.jsp");
	    //rd.forward(request, response);
		
	}

	private void updatePrestamo(HttpServletRequest request, HttpServletResponse response, int i) throws ServletException, IOException {
		RequestDispatcher rd;
		PrestamosNegocio pdxaNeg = new PrestamosNegocioImpl();
		
		boolean solicitado = false;
		String resString="";
		Prestamo pxa = new Prestamo();
		
		try
		{
	    pxa = pdxaNeg.BuscarUno(Integer.parseInt(request.getParameter("codPrestamo")));
		solicitado = pdxaNeg.Update(pxa);

		if(solicitado)
			resString="Solicitud Procesada Satisfactoriamente";
		else
			resString="Solicitud no pudo ser agregada Satisfactoriamente";
		}
		catch(Exception e)
		{
			resString="Solicitud no pudo ser agregada Satisfactoriamente";
			request.setAttribute("codPrestamo",null);
		}
		ArrayList<Prestamo> lPrestamos = (ArrayList<Prestamo>)pdxaNeg.BuscarAcivos() ;
		
		
		
		
		//PAGINADO
		int cantTotal = (int) pdxaNeg.ContarPrestamos();  //Cantidad de registros activos en la BD

		int pag = 1;
		if(request.getParameter("pag") != null) {
			pag = Integer.parseInt(request.getParameter("pag"));	
		}
		
		int limit = 10;                      		
		int offset = 0;
		if(pag > 1) offset = limit * (pag - 1);	 	
		int cantPag = (cantTotal / limit) + 1 ; 	
		int resto = offset + limit;
		int index = 0;
		
		ListIterator<Prestamo> itLista = lPrestamos.listIterator();
		while (itLista.hasNext()) {
			Prestamo pres = itLista.next();
			index += 1;
	       
			if(index < offset + 1 || index > offset + limit ) {
				itLista.remove();
			}
		}

		request.setAttribute("pag", pag);
		request.setAttribute("cantPag", cantPag);
		
		
		
		request.setAttribute("codPrestamo", pxa.getIdEstadoPrestamo());
		request.setAttribute("resBoolean", solicitado);
		request.setAttribute("resString", resString);
		request.setAttribute("Prestamos", lPrestamos);
		rd = request.getRequestDispatcher("AltaPrestamo.jsp");
		///rd = request.getRequestDispatcher("/AltaPrestamo.jsp?getPrestamos");
		rd.forward(request, response);
	}

	private void cargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd;
	    boolean solicitado = false;
	    String resString = "";
	    PrestamosNegocio pdxaNeg = new PrestamosNegocioImpl();
	    
	    // Obtener lista de préstamos activos
	    ArrayList<Prestamo> lPrestamos = (ArrayList<Prestamo>) pdxaNeg.BuscarAcivos();
	    
	    // Imprimir el número de préstamos recuperados
	    if (lPrestamos != null) {
	        System.out.println("Número de préstamos recuperados: " + lPrestamos.size());

	        // Imprime los detalles de cada préstamo
	        for (Prestamo prestamo : lPrestamos) {
	            System.out.println("Préstamo - ID: " + prestamo.getIdEstadoPrestamo() +
	                               ", Monto: " + prestamo.getMontoSolicitado() +
	                               ", Cuotas: " + prestamo.getCantidadCuotas());
	        }

	        // Verificar si hay préstamos activos y establecer el estado de solicitud
	        if (!lPrestamos.isEmpty()) {
	            solicitado = true;
	        } else {
	            resString = "No hay préstamos pendientes de aprobación";
	        }
	    } else {
	        resString = "Error al recuperar los préstamos.";
	    }

	    // Establecer atributos para el JSP
	    request.setAttribute("resString", resString);
	    request.setAttribute("resBoolean", solicitado);
	    request.setAttribute("Prestamos", lPrestamos);

	    // Redirigir a la página AltaPrestamo.jsp
	    rd = request.getRequestDispatcher("AltaPrestamo.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void btnRealizarSolicitudPrestamo(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    RequestDispatcher rd;
	    PrestamosNegocio pdxaNeg = new PrestamosNegocioImpl();
	    String resString = "";
	    boolean solicitado = false;

	    try {
	        // Obtener datos de la solicitud
	        String montoSolicitadoStr = request.getParameter("txtMonto");
	        String cuotasStr = request.getParameter("txtCuotas");
	        String cuentaIdStr = request.getParameter("getCuenta");
	        String clienteIdStr = request.getParameter("getCliente"); // Nuevo parámetro clienteId

	        // Validaciones iniciales
	        if (montoSolicitadoStr == null || montoSolicitadoStr.isEmpty() || 
	            cuotasStr == null || cuotasStr.isEmpty() || 
	            cuentaIdStr == null || cuentaIdStr.isEmpty() || 
	            clienteIdStr == null || clienteIdStr.isEmpty()) { // Verifica que clienteId no sea nulo o vacío
	            resString = "Error: Todos los campos son obligatorios.";
	        } else {
	            // Verificar que los valores sean números válidos
	            float montoSolicitado = 0;
	            int cuotas = 0;
	            int cuentaId = 0;
	            int clienteId = 0;

	            try {
	                montoSolicitado = Float.parseFloat(montoSolicitadoStr);
	                cuotas = Integer.parseInt(cuotasStr);
	                cuentaId = Integer.parseInt(cuentaIdStr);
	                clienteId = Integer.parseInt(clienteIdStr); // Convertir clienteId
	            } catch (NumberFormatException e) {
	                resString = "Error: Verifica que los datos ingresados sean válidos.";
	                e.printStackTrace();
	                request.setAttribute("resBoolean", solicitado);
	                request.setAttribute("resString", resString);
	                rd = request.getRequestDispatcher("Cliente.jsp");
	                rd.forward(request, response);
	                return;
	            }

	            // Validación de valores lógicos
	            if (montoSolicitado <= 0) {
	                resString = "Error: El monto solicitado debe ser mayor a 0.";
	            } else if (cuotas <= 0) {
	                resString = "Error: La cantidad de cuotas debe ser mayor a 0.";
	            } else {
	                // Calcular importe mensual directamente aquí
	                float importeMensual = montoSolicitado / cuotas; // Realiza la división del monto entre las cuotas

	                // Crear objeto Prestamo
	                Prestamo nuevoPrestamo = new Prestamo();
	                BigDecimal montoSolicitadoBD = BigDecimal.valueOf(montoSolicitado);
	                BigDecimal importeMensualBD = BigDecimal.valueOf(importeMensual);
	                nuevoPrestamo.setMontoSolicitado(montoSolicitadoBD);
	                nuevoPrestamo.setCantidadCuotas(cuotas);
	                nuevoPrestamo.setCuentaId(cuentaId);
	                nuevoPrestamo.setClienteId(clienteId); // Establecer clienteId
	                nuevoPrestamo.setIdEstadoPrestamo(1); // Estado inicial, por ejemplo "pendiente"
	                nuevoPrestamo.setFechaAlta(new Date(System.currentTimeMillis()));
	                nuevoPrestamo.setImporteMensualAPagar(importeMensualBD); // Establecer importe mensual

	                // Insertar el préstamo a través de la capa de negocio
	                solicitado = pdxaNeg.Insert(nuevoPrestamo);

	                if (solicitado) {
	                    resString = "¡Solicitud de préstamo realizada con éxito!";
	                } else {
	                    resString = "Error al procesar la solicitud de préstamo.";
	                }
	            }
	        }
	    } catch (Exception e) {
	        resString = "Error inesperado: " + e.getMessage();
	        e.printStackTrace();
	    }

	    // Establecer los mensajes y redirigir a la vista
	    request.setAttribute("resBoolean", solicitado);
	    request.setAttribute("resString", resString);
	    rd = request.getRequestDispatcher("SolicitudPrestamo.jsp");
	    rd.forward(request, response);
	}
}