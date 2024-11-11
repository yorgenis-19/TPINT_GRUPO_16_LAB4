package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.UsuarioDao;
import entidad.Usuario;

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
				usuario.setTipoUsuarioId(rs.getInt("TipoId"));
				usuario.setActivo(rs.getBoolean("Activo"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return usuario;
	}

}
