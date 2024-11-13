package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import dao.ClienteDao;
import entidad.Cliente;
import entidad.Usuario;

public class ClienteDaoImpl implements ClienteDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	@Override
	public Cliente Obtener(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Cliente obj = new Cliente();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Cliente WHERE Id ="+ id;
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				obj.setId(rs.getInt("Id"));
				Usuario usuario = new UsuarioDaoImpl().Obtener(rs.getInt("UsuarioId"));
				obj.setUsuario(usuario);
				obj.setNombre(rs.getString("Nombre"));
				obj.setApellido(rs.getString("Apellido"));
				obj.setSexo(rs.getString("Sexo"));
				obj.setDni(rs.getString("Dni"));
				obj.setCuil(rs.getString("Cuil"));
				obj.setTelefono(rs.getString("Telefono"));
				obj.setEmail(rs.getString("Email"));
				//obj.setFechaNacimiento(rs.getDate("FechaNacimiento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				obj.setFechaNacimiento(rs.getDate("FechaNacimiento"));
				obj.setDireccionId(rs.getInt("DireccionId"));
				obj.setLocalidadId(rs.getInt("LocalidadId"));
				obj.setProvinciaId(rs.getInt("ProvinciaId"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Cliente> objs = new ArrayList<Cliente>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Cliente WHERE (Nombre LIKE '%" + nombre + "%' ) AND (Apellido LIKE '%" + apellido + "%' ) AND (Email LIKE '%" + email + "%' ) AND (DNI LIKE '%" + dni + "%' )";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Cliente obj = new Cliente();
				obj.setId(rs.getInt("Id"));
				Usuario usuario = new UsuarioDaoImpl().Obtener(rs.getInt("UsuarioId"));
				obj.setUsuario(usuario);
				obj.setNombre(rs.getString("Nombre"));
				obj.setApellido(rs.getString("Apellido"));
				obj.setSexo(rs.getString("Sexo"));
				obj.setDni(rs.getString("Dni"));
				obj.setCuil(rs.getString("Cuil"));
				obj.setTelefono(rs.getString("Telefono"));
				obj.setEmail(rs.getString("Email"));
				//obj.setFechaNacimiento(rs.getDate("FechaNacimiento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				obj.setFechaNacimiento(rs.getDate("FechaNacimiento"));
				obj.setDireccionId(rs.getInt("DireccionId"));
				obj.setLocalidadId(rs.getInt("LocalidadId"));
				obj.setProvinciaId(rs.getInt("ProvinciaId"));
				objs.add(obj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objs;
	}

}
