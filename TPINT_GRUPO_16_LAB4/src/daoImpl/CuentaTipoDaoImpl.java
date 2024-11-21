package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

}
