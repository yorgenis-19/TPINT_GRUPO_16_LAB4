package daoImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	private static final String countallActive = "SELECT COUNT(Id) as total  FROM prestamo where EstadoId = 2";
	private static final String update = "UPDATE prestamo SET estadoId = ? WHERE Id = ?";
	private static final String countAll = "SELECT COUNT(Id) as total FROM prestamo where estadoId = 1 ORDER by CuentaId, ClienteId, Id";
	private static final String readallbyid = "SELECT * FROM prestamo WHERE clienteId = ?";
	private static final String getcuotas = "SELECT * FROM cuota WHERE PrestamoId =  ? ";
	
    @Override	
	public List<Prestamo> BuscarByIdCliente(int id) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Connection conexion = null;
		System.out.println("el id exxxxxxxxxxxxxx " + id);
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			 System.out.println("el id exxxxxxxxxxxxxx " + id);
			statement = conexion.prepareStatement(readallbyid);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				prestamos.add(getPrestamoxAutorizar(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return prestamos;
	}
	
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

	public Prestamo BuscarUno(int id) {
	    Prestamo prestamo = null;
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Establecer conexión a la base de datos
	        conexion = DriverManager.getConnection(host + dbName + "?useSSL=false", user, pass);
	        
	        // Consulta SQL para buscar el préstamo asociado a la cuenta
	        String query = "SELECT p.Id, p.ClienteId, p.CuentaId, p.FechaAlta, p.MontoSolicitado, " +
	                       "p.EstadoId, p.CantidadDeCuotas, p.ImporteMensualAPagar " +
	                       "FROM Prestamo p " +
	                       "WHERE p.Id = ?";
	        
	        statement = conexion.prepareStatement(query);
	        statement.setInt(1, id);
	        resultSet = statement.executeQuery(); // Ejecutar la consulta
	        
	        // Verificar si se encontró un préstamo
	        if (resultSet.next()) {
	            prestamo = new Prestamo();
	            prestamo.setId(resultSet.getInt("Id"));
	            prestamo.setClienteId(resultSet.getInt("ClienteId"));
	            prestamo.setCuentaId(resultSet.getInt("CuentaId"));
	            prestamo.setFechaAlta(resultSet.getDate("FechaAlta"));
	            prestamo.setMontoSolicitado(resultSet.getBigDecimal("MontoSolicitado"));
	            prestamo.setIdEstadoPrestamo(resultSet.getInt("EstadoId"));
	            prestamo.setCantidadCuotas(resultSet.getInt("CantidadDeCuotas"));
	            prestamo.setImporteMensualAPagar(resultSet.getBigDecimal("ImporteMensualAPagar"));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar el préstamo: " + e.getMessage());
	    } finally {
	        // Cerrar recursos
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar la conexión: " + e.getMessage());
	        }
	    }

	    return prestamo; // Retornar el préstamo encontrado o null si no se encontró
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

	@Override
	public boolean Update(Prestamo prestamo) {
	    PreparedStatement statement = null;
	    Connection conexion = null;
	    boolean isUpdateExitoso = false;

	    try {
	        // Validar que el objeto prestamo no sea nulo y tenga los campos obligatorios
	        if (prestamo == null || prestamo.getId() == 0) {
	            throw new IllegalArgumentException("El objeto prestamo o su ID es nulo");
	        }

	        // Cargar el driver de MySQL
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establecer conexión a la base de datos
	        conexion = DriverManager.getConnection(host + dbName + "?useSSL=false", user, pass);
	        conexion.setAutoCommit(false); // Desactivar auto-commit para manejar transacciones

	        // Preparar la sentencia para actualizar el préstamo
	        statement = conexion.prepareStatement(update);
	        
	        // Establecer los parámetros para la actualización del préstamo
	        statement.setInt(1, 2); 
	        statement.setInt(2, prestamo.getId()); // ID del préstamo
	        System.out.println("no    oo  o o o o o o entrooooooooooo: " + prestamo);
	        // Ejecutar la actualización del préstamo
	        if (statement.executeUpdate() > 0) {
	        	System.out.println("entrooooooooooo: " + prestamo);
	            // Si la actualización fue exitosa, actualizar el saldo de la cuenta
	            BigDecimal montoSolicitado = prestamo.getMontoSolicitado(); // Monto solicitado (puedes ajustar según tu lógica)
	            int cuentaId = prestamo.getCuentaId(); // ID de la cuenta asociada al préstamo
	            
	            // Actualizar saldo en la tabla Cuenta
	            String updateSaldoQuery = "UPDATE Cuenta SET Monto = Monto + ? WHERE Id = ?";
	            
	            try (PreparedStatement ps = conexion.prepareStatement(updateSaldoQuery)) {
	                ps.setBigDecimal(1, montoSolicitado); // Monto a agregar a la cuenta
	                ps.setInt(2, cuentaId); // ID de la cuenta
	                ps.executeUpdate(); // Ejecutar actualización del saldo
	            }

	            conexion.commit(); // Confirmar transacción si todo fue exitoso
	            isUpdateExitoso = true; // Marcar como exitoso
	        } else {
	            conexion.rollback(); // Revertir si no se afectaron filas
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Error: No se encontró el driver de MySQL: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("Error SQL al actualizar el préstamo: " + e.getMessage());
	        if (conexion != null) {
	            try {
	                conexion.rollback(); // Revertir en caso de error
	            } catch (SQLException ex) {
	                System.err.println("Error al hacer rollback: " + ex.getMessage());
	            }
	        }
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar la conexión: " + e.getMessage());
	        }
	    }

	    return isUpdateExitoso; // Retornar resultado de la operación
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

	public List<CuotaPrestamo> ObtenerCuota(int codPrestamo){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<CuotaPrestamo> cuotas = new ArrayList<CuotaPrestamo>();
		Connection conexion = null;
		System.out.println("SHHHHHHHHHHH: " + codPrestamo);
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			statement = conexion.prepareStatement(getcuotas);
			statement.setInt(1, codPrestamo);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuotas.add(getCuota(resultSet));
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		System.out.print("ESTAS SON LAS CUOTAS: " + cuotas);
		return cuotas;
	}
	
	private CuotaPrestamo getCuota(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("Id");
		int prestamoId= resultSet.getInt("PrestamoId");
		int numeroCuota= resultSet.getInt("Numero");
		float monto = resultSet.getFloat("Monto");
		Date fechaPago = resultSet.getDate("FechaPago");
		Date fechaVencimiento = resultSet.getDate("FechaVencimiento");
		boolean estado = resultSet.getBoolean("Estado");
		return new CuotaPrestamo(id, prestamoId, numeroCuota, monto, fechaPago, fechaVencimiento, estado);
	}
	
	
	

}