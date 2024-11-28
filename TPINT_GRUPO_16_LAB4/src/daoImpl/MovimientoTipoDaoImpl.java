package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.MovimientoDao;
import dao.MovimientoTipoDao;
import dao.UsuarioTipoDao;
import entidad.Cliente;
import entidad.MovimientoTipo;
import entidad.Usuario;
import entidad.UsuarioTipo;

public class MovimientoTipoDaoImpl implements MovimientoTipoDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	@Override
	public MovimientoTipo Obtener(int id) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		MovimientoTipo obj = new MovimientoTipo();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM MovimientoTipo WHERE Id = " + id;
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
	public ArrayList<MovimientoTipo> ObtenerTodos() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<MovimientoTipo> objs = new ArrayList<MovimientoTipo>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM UsuarioTipo";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				MovimientoTipo obj = new MovimientoTipo();
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
	public MovimientoTipo Obtener(String descripcion) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		MovimientoTipo obj = new MovimientoTipo();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM MovimientoTipo WHERE Descripcion = '" + descripcion+"'";
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

}
