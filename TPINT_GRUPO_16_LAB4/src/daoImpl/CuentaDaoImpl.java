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

import dao.ClienteDao;
import dao.CuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaTipo;
import entidad.Usuario;

public class CuentaDaoImpl implements CuentaDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	@Override
	public Cuenta Obtener(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Cuenta obj = new Cuenta();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Cuenta WHERE Id ="+ id;
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				obj.setId(rs.getInt("Id"));
				CuentaTipo tipo = new CuentaTipoDaoImpl().Obtener(rs.getInt("TipoId"));
				obj.setTipo(tipo);
				Cliente cliente = new ClienteDaoImpl().Obtener(rs.getInt("ClienteId"));
				obj.setCliente(cliente);
				obj.setMonto(rs.getFloat("Monto"));
				obj.setActiva(rs.getBoolean("Activa"));
				obj.setCBU(rs.getInt("CBU"));
				obj.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipoId, float monto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Cuenta> objs = new ArrayList<Cuenta>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Cuenta WHERE Monto = " + monto;
			if(clienteId > 0) {
				query += "ClienteId = " + clienteId;
			}
			if(tipoId > 0 ) {
				query += "TipoId = " + tipoId;
			}
			if(cbu > 0) {
				query += "CBU = " + cbu;
			}
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Cuenta obj = new Cuenta();
				obj.setId(rs.getInt("Id"));
				CuentaTipo tipo = new CuentaTipoDaoImpl().Obtener(rs.getInt("TipoId"));
				obj.setTipo(tipo);
				Cliente cliente = new ClienteDaoImpl().Obtener(rs.getInt("ClienteId"));
				obj.setCliente(cliente);
				obj.setMonto(rs.getFloat("Monto"));
				obj.setActiva(rs.getBoolean("Activa"));
				obj.setCBU(rs.getInt("CBU"));
				obj.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
				objs.add(obj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objs;
	}
/*
	@Override
	public int Guardar(Cuenta obj) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String query = "INSERT INTO `Cuenta`(`UsuarioId`,`Nombre`,`Apellido`,`Sexo`,`DNI`,`CUIL`,`Telefono`,`Email`,`FechaNacimiento`,`DireccionId`,`LocalidadId`,`ProvinciaId`)VALUES("+obj.getUsuario().getId()+",'"+obj.getNombre()+"','"+obj.getApellido()+"','"+obj.getSexo()+"','"+obj.getDni()+"','"+obj.getCuil()+"','"+obj.getTelefono()+"','"+obj.getEmail()+"','"+new SimpleDateFormat("yyyyMMdd").format(obj.getFechaNacimiento())+"',"+obj.getDireccionId()+","+obj.getLocalidadId()+","+obj.getProvinciaId()+")";
		if(obj.getId() > 0)
		{
			query = "UPDATE Cuenta SET UsuarioId = "+obj.getUsuario().getId()+", Nombre = '"+obj.getNombre()+"', Apellido = '"+obj.getApellido()+"', Sexo = '"+obj.getSexo()+"', DNI = '"+obj.getDni()+"', CUIL = '"+obj.getCuil()+"', Telefono = '"+obj.getTelefono()+"', Email = '"+obj.getEmail()+"', FechaNacimiento = '"+new SimpleDateFormat("yyyyMMdd").format(obj.getFechaNacimiento())+"', DireccionId = "+obj.getDireccionId()+", LocalidadId = "+obj.getLocalidadId()+", ProvinciaId = "+obj.getProvinciaId()+" WHERE ID = " + obj.getId();
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
*/
}