package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.UsuarioDao;
import entidad.Usuario;
import entidad.UsuarioTipo;

public class UsuarioDaoImpl implements UsuarioDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	@Override
	public Usuario Obtener(String nombre, String clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Usuario usuario = new Usuario();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Usuario WHERE Nombre = '" + nombre + "' AND Clave = '" + clave + "'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				usuario.setId(rs.getInt("Id"));
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setClave(rs.getString("Clave"));
				UsuarioTipo tipo = new UsuarioTipo();
				tipo = new UsuarioTipoDaoImpl().Obtener(rs.getInt("TipoId"));
				usuario.setTipo(tipo);
				usuario.setActivo(rs.getBoolean("Activo"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return usuario;
	}


	@Override
	public Usuario Obtener(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Usuario usuario = new Usuario();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Usuario WHERE Id = " + id;
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				usuario.setId(rs.getInt("Id"));
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setClave(rs.getString("Clave"));
				UsuarioTipo tipo = new UsuarioTipo();
				tipo = new UsuarioTipoDaoImpl().Obtener(rs.getInt("TipoId"));
				usuario.setTipo(tipo);
				usuario.setActivo(rs.getBoolean("Activo"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return usuario;
	}


	@Override
	public int Guardar(Usuario obj) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String query = "INSERT INTO `Usuario`(`Nombre`,`Clave`,`TipoId`,`Activo`)VALUES('"+obj.getNombre()+"','"+obj.getClave()+"',"+obj.getTipo().getId()+","+obj.getActivo()+")";
		if(obj.getId() > 0)
		{
			query = "UPDATE Usuario SET Nombre = '"+obj.getNombre()+"', Clave = '"+obj.getClave()+"', TipoId = "+obj.getTipo().getId()+", Activo = "+obj.getActivo()+" WHERE Id = " + obj.getId();
		}
		
		Connection cn = null;
		int filas = 0;
			
		try {
			cn = DriverManager.getConnection(host+dbName, user, pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filas;
	}


	@Override
	public ArrayList<Usuario> Obtener(String nombre, int tipoId, String activo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "select * from usuario WHERE Nombre LIKE '%"+nombre+"%'";
			if(tipoId != 0)
			{
				query += " AND TipoId = " +tipoId;
			}
			if(activo.equals("ACTIVO"))
			{
				query += " AND Activo = true";
			}
			else if (activo.equals("BAJA"))
			{
				query += " AND Activo = false";
			}
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("Id"));
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setClave(rs.getString("Clave"));
				UsuarioTipo tipo = new UsuarioTipo();
				tipo = new UsuarioTipoDaoImpl().Obtener(rs.getInt("TipoId"));
				usuario.setTipo(tipo);
				usuario.setActivo(rs.getBoolean("Activo"));
				usuarios.add(usuario);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return usuarios;
	}
}
