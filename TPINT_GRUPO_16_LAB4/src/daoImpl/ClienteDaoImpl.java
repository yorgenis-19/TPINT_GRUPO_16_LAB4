package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

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
		finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	@Override
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni, boolean activo) {
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
			String query = "SELECT * FROM Cliente c INNER JOIN Usuario u ON u.Id = UsuarioId WHERE u.Activo = "+activo+" AND (c.Nombre LIKE '%" + nombre + "%' ) AND (c.Apellido LIKE '%" + apellido + "%' ) AND (c.Email LIKE '%" + email + "%' ) AND (c.DNI LIKE '%" + dni + "%' )";
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
		finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return objs;
	}

	@Override
	public int Guardar(Cliente obj) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String query = "INSERT INTO `Cliente`(`UsuarioId`,`Nombre`,`Apellido`,`Sexo`,`DNI`,`CUIL`,`Telefono`,`Email`,`FechaNacimiento`,`DireccionId`,`LocalidadId`,`ProvinciaId`)VALUES("+obj.getUsuario().getId()+",'"+obj.getNombre()+"','"+obj.getApellido()+"','"+obj.getSexo()+"','"+obj.getDni()+"','"+obj.getCuil()+"','"+obj.getTelefono()+"','"+obj.getEmail()+"','"+new SimpleDateFormat("yyyyMMdd").format(obj.getFechaNacimiento())+"',"+obj.getDireccionId()+","+obj.getLocalidadId()+","+obj.getProvinciaId()+")";
		if(obj.getId() > 0)
		{
			query = "UPDATE Cliente SET UsuarioId = "+obj.getUsuario().getId()+", Nombre = '"+obj.getNombre()+"', Apellido = '"+obj.getApellido()+"', Sexo = '"+obj.getSexo()+"', DNI = '"+obj.getDni()+"', CUIL = '"+obj.getCuil()+"', Telefono = '"+obj.getTelefono()+"', Email = '"+obj.getEmail()+"', FechaNacimiento = '"+new SimpleDateFormat("yyyyMMdd").format(obj.getFechaNacimiento())+"', DireccionId = "+obj.getDireccionId()+", LocalidadId = "+obj.getLocalidadId()+", ProvinciaId = "+obj.getProvinciaId()+" WHERE ID = " + obj.getId();
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
	public boolean ExisteMail(int id, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		boolean res = false;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT Id FROM Cliente WHERE Id != "+ id + " AND Email = '" + email + "' limit 1";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				res = rs.getInt("Id") > 0;
			}
		}
		catch(Exception e)
		{
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
		return res;
	}

	@Override
	public boolean ExisteDNI(int id, String dni) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		boolean res = false;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT Id FROM Cliente WHERE Id != "+ id + " AND DNI = '" + dni + "' limit 1";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				res = rs.getInt("Id") > 0;
			}
		}
		catch(Exception e)
		{
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
		return res;
	}

	@Override
	public boolean ExisteCUIL(int id, String cuil) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		boolean res = false;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT Id FROM Cliente WHERE Id != "+ id + " AND CUIL = '" + cuil + "' limit 1";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				res = rs.getInt("Id") > 0;
			}
		}
		catch(Exception e)
		{
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
		return res;
	}

	@Override
	public boolean ExisteUsuario(String usuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		boolean res = false;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT Id FROM Usuario WHERE Nombre = '" + usuario + "' limit 1";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				res = rs.getInt("Id") > 0;
			}
		}
		catch(Exception e)
		{
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
		return res;
	}

	@Override
	public Cliente ObtenerPorUsuario(int usuarioId) {
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
			String query = "SELECT * FROM Cliente WHERE UsuarioId ="+ usuarioId;
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
		finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	@Override
	public Cliente ObtenerClientePorId(int id) {
		Cliente cliente = null;
	    Conexion conexion = new Conexion();
	    Connection cn = null;

	    try {
	        // Abre la conexión usando la clase Conexion
	        cn = conexion.Open();

	        // Consulta SQL
	        String query = "SELECT * FROM Cliente WHERE Id = ?";
	        PreparedStatement ps = (PreparedStatement) conexion.prepareStatement(query);
	        ps.setInt(1, id);
	        System.out.println("Consulta preparada: " + ps.toString());

	        ResultSet rs = ps.executeQuery();
	        System.out.println("Consulta ejecutada.");

	        // Mapear resultados
	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setId(rs.getInt("Id"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));
	            cliente.setSexo(rs.getString("Sexo"));
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setCuil(rs.getString("CUIL"));
	            cliente.setTelefono(rs.getString("Telefono"));
	            cliente.setEmail(rs.getString("Email"));
	            cliente.setFechaNacimiento(rs.getDate("FechaDeNacimiento"));
	            cliente.setDireccionId(rs.getInt("Direccion"));
	            cliente.setLocalidadId(rs.getInt("Localidad"));
	            cliente.setProvinciaId(rs.getInt("Provincia"));

	            System.out.println("Datos del cliente obtenidos: " + cliente);
	        } else {
	            System.out.println("No se encontró un cliente con el ID: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la consulta SQL:");
	        e.printStackTrace();
	    } finally {
	        // Cerrar conexión
	        if (cn != null) {
	            try {
	                conexion.close();
	                System.out.println("Conexión cerrada.");
	            } catch (Exception e) {
	                System.out.println("Error al cerrar la conexión:");
	                e.printStackTrace();
	            }
	        }
	    }
	    return cliente;
	}

}
