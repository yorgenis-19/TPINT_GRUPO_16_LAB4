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
	
	private static final String insert = "INSERT INTO Prestamo (ClienteId, CuentaId, FechaAlta, MontoSolicitado, " +
            "EstadoId, CantidadDeCuotas, ImporteMensualAPagar) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String readallActive = "SELECT * FROM prestamo";
	private static final String countallActive = "SELECT COUNT(codPrestamoPendinte) as total  FROM prestamos_x_autorizar where estado = 1";
	private static final String update = "UPDATE prestamos_x_autorizar SET estado = ? WHERE codPrestamoPendinte = ?";
	private static final String countAll = "SELECT COUNT(codPrestamoPendinte) as total FROM agregarPrestamoxAutorizar where estado = 1 ORDER by apellido, dni codPrestamoPendinte";
	
	@Override
	public boolean Insert(Prestamo prestamo) {
	    Connection conexion = null;
	    CallableStatement cs = null;
	    boolean isInsertExitoso = false;

	    try {
	        if (prestamo == null || prestamo.getCuentaId() == null || prestamo.getFechaAlta() == null) {
	            throw new IllegalArgumentException("El objeto prestamo o alguno de sus campos obligatorios es nulo");
	        }

	        // Carga el driver de MySQL
	        Class.forName("com.mysql.jdbc.Driver");

	        // Agrega `useSSL=false` si no usas SSL
	        String url = host + dbName + "?useSSL=false";
	        conexion = DriverManager.getConnection(url, user, pass);

	        conexion.setAutoCommit(false);

	        cs = conexion.prepareCall(insert);
	        cs.setInt(1, prestamo.getClienteId());
	        cs.setInt(2, prestamo.getCuentaId());
	        cs.setDate(3, new java.sql.Date(prestamo.getFechaAlta().getTime()));
	        cs.setBigDecimal(4, prestamo.getMontoSolicitado());
	        cs.setInt(5, prestamo.getIdEstadoPrestamo());
	        cs.setInt(6, prestamo.getCantidadCuotas());
	        cs.setBigDecimal(7, prestamo.getImporteMensualAPagar());

	        if (cs.executeUpdate() > 0) {
	            conexion.commit();
	            isInsertExitoso = true;
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Error: No se encontró el driver de MySQL: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("Error SQL al insertar el préstamo: " + e.getMessage());
	        if (conexion != null) {
	            try {
	                conexion.rollback();
	            } catch (SQLException ex) {
	                System.err.println("Error al hacer rollback: " + ex.getMessage());
	            }
	        }
	    } finally {
	        try {
	            if (cs != null) cs.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar la conexión: " + e.getMessage());
	        }
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
	
	
	public ArrayList<Prestamo> BuscarActivos() {
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null; 
	    ArrayList<Prestamo> prestamoxAutorizar = new ArrayList<Prestamo>();
	    System.out.println("[BuscarActivos] Conexión establecida exitosamente.");
	    try {
	        // Carga el driver de MySQL
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        // Agrega `useSSL=false` si no usas SSL
	        String url = host + dbName + "?useSSL=false";
	        
	        // Establecer conexión
	        conexion = DriverManager.getConnection(url, user, pass);
	        conexion.setAutoCommit(false);
	        
	        // Log de inicio de conexión
	        System.out.println("[BuscarActivos] Conexión establecida exitosamente.");
	        
	        // Preparar y ejecutar consulta
	        statement = conexion.prepareStatement(readallActive);
	        resultSet = statement.executeQuery();
	        
	        // Log de inicio de mapeo
	        System.out.println("[BuscarActivos] Preparando consulta: " + readallActive);
	        
	        int contadorRegistros = 0;
	        while (resultSet.next()) {
	            Prestamo prestamo = getPrestamoxAutorizar(resultSet);
	            prestamoxAutorizar.add(prestamo);
	            contadorRegistros++;
	            
	            // Log de cada préstamo mapeado
	            System.out.println("[BuscarActivos] Préstamo mapeado: " + prestamo);
	        }
	        
	        // Commit de la transacción
	        conexion.commit();
	        
	        // Log de resumen
	        System.out.println("[BuscarActivos] Total de préstamos mapeados: " + contadorRegistros);
	        
	    } catch (ClassNotFoundException e) {
	        System.err.println("Error: No se encontró el driver de MySQL: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("[BuscarActivos] Error al obtener registros: " + e.getMessage());
	        
	        // Rollback en caso de error
	        if (conexion != null) {
	            try {
	                conexion.rollback();
	            } catch (SQLException ex) {
	                System.err.println("Error al hacer rollback: " + ex.getMessage());
	            }
	        }
	        
	        e.printStackTrace();
	    } finally {
	        // Cierre de recursos
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (conexion != null) conexion.close();
	            System.out.println("[BuscarActivos] Recursos cerrados correctamente.");
	        } catch (SQLException e) {
	            System.err.println("[BuscarActivos] Error al cerrar recursos: " + e.getMessage());
	            e.printStackTrace();
	        }
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
				statement.setBigDecimal(2,prestamo.getMontoSolicitado());
				
				
				

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
	    int id = resultSet.getInt("Id");
	    int clienteId = resultSet.getInt("ClienteId");
	    int cuentaId = resultSet.getInt("CuentaId");
	    Date fechaAlta = resultSet.getDate("FechaAlta");
	    BigDecimal montoSolicitado = resultSet.getBigDecimal("MontoSolicitado");
	    int estadoId = resultSet.getInt("EstadoId");
	    int cantidadDeCuotas = resultSet.getInt("CantidadDeCuotas");
	    BigDecimal importeMensualAPagar = resultSet.getBigDecimal("ImporteMensualAPagar");

	    return new Prestamo(id, clienteId, cuentaId, fechaAlta, montoSolicitado, estadoId, cantidadDeCuotas, importeMensualAPagar);
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