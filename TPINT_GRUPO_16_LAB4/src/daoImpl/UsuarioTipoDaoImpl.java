package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.UsuarioTipoDao;
import entidad.Cliente;
import entidad.Usuario;
import entidad.UsuarioTipo;

public class UsuarioTipoDaoImpl implements UsuarioTipoDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	@Override
	public UsuarioTipo Obtener(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		UsuarioTipo usuario = new UsuarioTipo();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM UsuarioTipo WHERE Id = " + id;
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				usuario.setId(rs.getInt("Id"));
				usuario.setDescripcion(rs.getString("Descripcion"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public ArrayList<UsuarioTipo> ObtenerTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<UsuarioTipo> objs = new ArrayList<UsuarioTipo>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM UsuarioTipo";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				UsuarioTipo obj = new UsuarioTipo();
				obj.setId(rs.getInt("Id"));
				obj.setDescripcion(rs.getString("Descripcion"));
				objs.add(obj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objs;
	}

	@Override
	public UsuarioTipo Obtener(String descripcion) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		UsuarioTipo usuario = new UsuarioTipo();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM UsuarioTipo WHERE Descripcion = '" + descripcion+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				usuario.setId(rs.getInt("Id"));
				usuario.setDescripcion(rs.getString("Descripcion"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return usuario;
	}

}
