package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Localidad;
import entidad.Usuario;
import jdk.nashorn.internal.parser.JSONParser;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;

/**
 * Servlet implementation class ServletLocalidades
 */
@WebServlet("/ServletLocalidades")
public class ServletLocalidades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLocalidades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		if(request.getParameter("provinciaId") != null)
		{
			//int provinciaId = Integer.parseInt(request.getParameter("provinciaId"));
		 	String provinciaIdStr = request.getParameter("provinciaId");
	        int provinciaId = Integer.parseInt(provinciaIdStr);
			ArrayList<Localidad> localidades = new LocalidadNegocioImpl().ObtenerPorProvincia(provinciaId);
			
			//request.setAttribute("Cuentas", cuentas);
			//RequestDispatcher rd = request.getRequestDispatcher("/ClienteCuentas.jsp");
			//rd.forward(request, response);
		    
	        String json = LocalidadesToJson(localidades);

	        // Configurar respuesta
	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
	}
	public static String LocalidadesToJson(ArrayList<Localidad> localidades) {
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < localidades.size(); i++) {
            Localidad localidad = localidades.get(i);
            json.append("{\"Id\":").append(localidad.getId())
                    .append(",\"Nombre\":\"").append(localidad.getNombre()).append("\"")
                    .append(",\"ProvinciaId\":").append(localidad.getProvinciaId()).append("}");

            if (i < localidades.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");
        return json.toString();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
