package daoImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;

import dao.PrestamosDao;
import entidad.Cuenta;
import entidad.CuotaPrestamo;
import entidad.Prestamo;


public class PrestamosDaoImpl implements PrestamosDao {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	
	private static final String insert = "{CALL agregarPrestamoxAutorizar(?,?,?)}";
	private static final String readallActive = "SELECT * FROM prestamos_x_autorizar where estado=1";
	private static final String countallActive = "SELECT COUNT(codPrestamoPendinte) as total  FROM prestamos_x_autorizar where estado = 1";
	private static final String update = "UPDATE prestamos_x_autorizar SET estado = ? WHERE codPrestamoPendinte = ?";
	private static final String countAll = "SELECT COUNT(codPrestamoPendinte) as total FROM agregarPrestamoxAutorizar where estado = 1 ORDER by apellido, dni codPrestamoPendinte";
	
	@Override
	public boolean Insert(Prestamo prestamo) {
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		boolean isInsertExitoso = false;
		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}

		try {
			 CallableStatement cs = (CallableStatement) conexion.prepareCall(insert);
			cs.setInt(1, prestamo.getCuentaId());
			cs.setFloat(2, prestamo.getImporteMensualAPagar());
			cs.setInt(3, prestamo.getCantidadCuotas());

			if (cs.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	
	}
	
	public boolean EliminacionLogica(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	public Prestamo BuscarUno(int nroCuenta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Prestamo> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Prestamo> prestamoxAutorizar = new ArrayList<Prestamo>();
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
		try {
			statement = conexion.prepareStatement(readallActive);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				prestamoxAutorizar.add(getPrestamoxAutorizar(resultSet));
			
			
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return prestamoxAutorizar;
	}
	
	
	public List<Prestamo> BuscarActivos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Prestamo> prestamoxAutorizar = new ArrayList<Prestamo>();
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			statement = conexion.prepareStatement(readallActive);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				prestamoxAutorizar.add(getPrestamoxAutorizar(resultSet));
			
			
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return prestamoxAutorizar;
	}

	public boolean Update(Prestamo prestamo) {
		
		PreparedStatement statement;
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		boolean isUpdateExitoso = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
				statement = conexion.prepareStatement(update);	
				
				statement.setInt(1,prestamo.getIdEstadoPrestamo());
				statement.setFloat(2,prestamo.getMontoSolicitado());
				
				
				

			if(statement.executeUpdate() > 0){
				conexion.commit();
				isUpdateExitoso  = true;
				}
			} 
		catch (SQLException e1) {
				e1.printStackTrace();
			}


		return isUpdateExitoso;
		}
	
	private Prestamo getPrestamoxAutorizar(ResultSet resultSet) throws SQLException {

		int codPrestamoPendinte = resultSet.getInt("codPrestamoPendinte");
		int nroCuenta = resultSet.getInt("nroCuenta");
		Date fecha_creacion = resultSet.getDate("fecha_creacion");
		BigDecimal importe = resultSet.getBigDecimal("importe_pedido");
		int cantidad_cuotas = resultSet.getInt("cantidad_cuotas");
		int estado = resultSet.getInt("estado");
		return new Prestamo(nroCuenta,codPrestamoPendinte,fecha_creacion,importe,cantidad_cuotas,estado);
	}

	
	public int ContarPrestamos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		int cant = 0;
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			statement = conexion.prepareStatement(countallActive);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cant = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer leer la cantidad de Prestamos por Autorizar activos (SQL ERROR)");
		}
		System.out.print(cant);
		return cant;
	}

	public List<Prestamo> BuscarDni(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CuotaPrestamo> ObtenerCuota(int codPrestamo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	

}