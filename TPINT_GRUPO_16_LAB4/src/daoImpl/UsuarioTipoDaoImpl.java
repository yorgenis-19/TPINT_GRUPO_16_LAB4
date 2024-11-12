package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.UsuarioTipoDao;
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

}
