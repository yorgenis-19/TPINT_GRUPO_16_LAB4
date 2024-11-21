package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dao.CuentaTipoDao;
import entidad.CuentaTipo;

public class CuentaTipoDaoImpl implements CuentaTipoDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	@Override
	public CuentaTipo Obtener(int id) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CuentaTipo obj = new CuentaTipo();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM CuentaTipo WHERE Id = " + id;
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				obj.setId(rs.getInt("Id"));
				obj.setDescripcion(rs.getString("Descripcion"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			 if(cn!=null) {
				 cn.close();			 
			 }
			}
		return obj;
	}

	@Override
	public ArrayList<CuentaTipo> ObtenerTodos() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<CuentaTipo> objs = new ArrayList<CuentaTipo>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM CuentaTipo";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				CuentaTipo obj = new CuentaTipo();
				obj.setId(rs.getInt("Id"));
				obj.setDescripcion(rs.getString("Descripcion"));
				objs.add(obj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			 if(cn!=null) {
				 cn.close();			 
			 }
			}
		return objs;
	}

	@Override
	public CuentaTipo Obtener(String descripcion) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CuentaTipo obj = new CuentaTipo();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM CuentaTipo WHERE Descripcion = '" + descripcion+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				obj.setId(rs.getInt("Id"));
				obj.setDescripcion(rs.getString("Descripcion"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			 if(cn!=null) {
				 cn.close();			 
			 }
			}
		return obj;
	}

	@Override
	public ArrayList<CuentaTipo> ObtenerTodosLosTiposDeCuenta() {
		ArrayList<CuentaTipo> tiposDeCuenta = new ArrayList<>();
	    Conexion conexion = new Conexion();
	    Connection cn = null;

	    try {
	        // Abre la conexión
	        cn = conexion.Open();

	        if (cn != null) {
	            System.out.println("Conexión establecida correctamente para todas la cuentas.");
	        } else {
	            System.out.println("Error al establecer la conexión.");
	            return tiposDeCuenta;
	        }

	        // Consulta SQL
	        String query = "SELECT * FROM CuentaTipo";
	        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        // Mapear resultados
	        while (rs.next()) {
	            CuentaTipo tipo = new CuentaTipo();
	            tipo.setId(rs.getInt("Id"));
	            tipo.setDescripcion(rs.getString("Descripcion"));
	            tiposDeCuenta.add(tipo);
	        }

	        // Imprimir lo que se ha obtenido
	        if (tiposDeCuenta.isEmpty()) {
	            System.out.println("No se han encontrado tipos de cuenta.");
	        } else {
	            System.out.println("Tipos de cuenta obtenidos");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión
	        if (cn != null) {
	            conexion.close();
	        }
	    }

	    return tiposDeCuenta;
	}

}
