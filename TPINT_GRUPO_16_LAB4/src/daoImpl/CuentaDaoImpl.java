package daoImpl;

import java.sql.Connection;
import java.sql.Date;
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
import dao.CuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaTipo;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaTipoNegocioImpl;

public class CuentaDaoImpl implements CuentaDao {
	private Conexion conexion = new Conexion();

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
		
		Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    String query = "SELECT * FROM Cuenta WHERE Id ="+ id;
	    Cuenta obj = new Cuenta();
		try
		{
			cn = new Conexion();
	        cn.Open();

	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        rs = preparedStatement.executeQuery();
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
		} finally {
	        // Cerrar recursos en el orden inverso en que fueron abiertos
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (cn != null) cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
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
	public ArrayList<Cuenta> ObtenerCuentasxClienteID(int ID) {
		 ArrayList<Cuenta> cuentas = new ArrayList<>();
		    String query = "SELECT c.Id AS cuenta_id, c.Monto, c.CBU, " +
	                   "ct.Descripcion AS tipo_descripcion, " +
	                   "cl.Nombre, cl.Apellido " +
	                   "FROM Cuenta c " +
	                   "INNER JOIN CuentaTipo ct ON c.TipoId = ct.Id " +
	                   "INNER JOIN Cliente cl ON c.ClienteId = cl.Id " +
	                   "WHERE c.ClienteId = ?";
		    Conexion cn = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet rs = null;

		    try {
		        cn = new Conexion();
		        cn.Open();
		        System.out.println("CONEXI�N ABIERTA - OBTENER CUENTAS POR CLIENTE ID");

		        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
		        preparedStatement.setInt(1, ID);
		        rs = preparedStatement.executeQuery();

		        while (rs.next()) {
		            // Crear objetos necesarios
		            Cuenta cuenta = new Cuenta();
		            Cliente cliente = new Cliente();
		            CuentaTipo tipoCuenta = new CuentaTipo();

		            // Mapear datos de la cuenta
		            cuenta.setId(rs.getInt("cuenta_id"));
		            cuenta.setMonto(rs.getFloat("Monto"));
		            cuenta.setCBU(rs.getLong("CBU"));

		            // Mapear datos del cliente
		            cliente.setNombre(rs.getString("Nombre"));
		            cliente.setApellido(rs.getString("Apellido"));

		            // Asignar el cliente al objeto Cuenta
		            cuenta.setCliente(cliente);

		            // Mapear datos del tipo de cuenta
		            tipoCuenta.setDescripcion(rs.getString("tipo_descripcion"));

		            // Asignar el tipo de cuenta al objeto Cuenta
		            cuenta.setTipo(tipoCuenta);

		            // Agregar la cuenta a la lista
		            cuentas.add(cuenta);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        // Cerrar recursos en el orden inverso en que fueron abiertos
		        try {
		            if (rs != null) rs.close();
		            if (preparedStatement != null) preparedStatement.close();
		            if (cn != null) cn.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }

		    return cuentas;
	}

	@Override
	public ArrayList<Cuenta> listarTodasLAsCuentas() {
		ArrayList<Cuenta> cuentas = new ArrayList<>();
	    String query = "SELECT c.Id AS cuenta_id, c.Monto, c.CBU, " +
                   "ct.Descripcion AS tipo_descripcion, " +
                   "cl.Nombre, cl.Apellido, cl.Dni, " +
                   "c.FechaDeCreacion, c.Activa, " +
                   "c.FechaDeCreacion " +
                   "FROM Cuenta c " +
                   "INNER JOIN CuentaTipo ct ON c.TipoId = ct.Id " +
                   "INNER JOIN Cliente cl ON c.ClienteId = cl.Id ";
	    Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        cn = new Conexion();
	        cn.Open();
	        System.out.println("CONEXIONN ABIERTA - LISTAR TODAS LAS CUENTAS");

	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Crear objetos necesarios
	            Cuenta cuenta = new Cuenta();
	            Cliente cliente = new Cliente();
	            CuentaTipo tipoCuenta = new CuentaTipo();

	            // datos de la cuenta
	            cuenta.setId(rs.getInt("cuenta_id"));
	            cuenta.setMonto(rs.getFloat("Monto"));
	            cuenta.setCBU(rs.getLong("CBU"));
	            cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
	            cuenta.setActiva(rs.getBoolean("Activa"));

	            //  datos del cliente
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));
	            cliente.setDni(rs.getString("Dni"));

	            // Asignar el cliente al objeto Cuenta
	            cuenta.setCliente(cliente);

	            //  datos del tipo de cuenta
	            tipoCuenta.setDescripcion(rs.getString("tipo_descripcion"));

	            // Asignar el tipo de cuenta al objeto Cuenta
	            cuenta.setTipo(tipoCuenta);

	            // Agregar la cuenta a la lista
	            cuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar recursos en el orden inverso en que fueron abiertos
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (cn != null) cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return cuentas;
	}

	@Override
	public ArrayList<Cuenta> ObtenerPorUsuario(int usuarioId) {
		ArrayList<Cuenta> cuentas = new ArrayList<>();
	    String query = "select cu.* from cuenta cu INNER JOIN Cliente cl ON cl.Id = cu.ClienteId WHERE cl.UsuarioId = " + usuarioId;
	    Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        cn = new Conexion();
	        cn.Open();

	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();
	            Cliente cliente = new Cliente();
	            CuentaTipo tipoCuenta = new CuentaTipo();
	            cliente = new ClienteNegocioImpl().Obtener(rs.getInt("ClienteId"));
	            cuenta.setCliente(cliente);
	            cuenta.setId(rs.getInt("Id"));
	            cuenta.setMonto(rs.getFloat("Monto"));
	            cuenta.setCBU(rs.getLong("CBU"));
	            cuenta.setActiva(rs.getBoolean("Activa"));
	            cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
	            tipoCuenta = new CuentaTipoNegocioImpl().Obtener(rs.getInt("TipoId"));
	            cuenta.setTipo(tipoCuenta);
	            cuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar recursos en el orden inverso en que fueron abiertos
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (cn != null) cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return cuentas;
	}
	
	public int CrearCuenta(int ID, int TipoCuenta) {
		 int estado = 0;

		    Conexion cn = new Conexion();
		    String query = "INSERT INTO Cuenta (ClienteId, Activa, CBU, TipoId, FechaDeCreacion) " +
		                   "VALUES (?, ?, ?, ?, ?, ?)";

		    try {
		        cn.Open();
		        PreparedStatement preparedStatement = (PreparedStatement) cn.prepareStatement(query);

		        // Asignar los valores al PreparedStatement
		        preparedStatement.setInt(1, ID); // ClienteId
		       // preparedStatement.setFloat(2, montoInicial); // Monto
		        preparedStatement.setBoolean(3, true); // Activa (por defecto true)
		        preparedStatement.setLong(4, generarCBU()); // CBU (se genera autom�ticamente)
		        preparedStatement.setInt(5, TipoCuenta); // TipoId
		        preparedStatement.setDate(6, Date.valueOf(LocalDate.now())); // FechaDeCreacion

		        // Ejecutar la consulta
		        estado = preparedStatement.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }

		    return estado;
	}

	private long generarCBU() {
	    // Generar un n�mero aleatorio de 16 d�gitos
	    return (long) (Math.random() * 1_000_000_000_000_000L);
	}

	@Override
	public Cuenta ObtenerPorCBU(long cbu) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Cuenta obj = new Cuenta();
		Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
		try
		{
	        cn = new Conexion();
	        cn.Open();
			String query = "SELECT * FROM Cuenta WHERE CBU = "+ cbu;
			preparedStatement = (PreparedStatement) cn.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);
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
		} finally {
	        // Cerrar recursos en el orden inverso en que fueron abiertos
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (cn != null) cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
		return obj;
	}

	@Override
	public void Guardar(Cuenta obj) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String query = "INSERT INTO `Cuenta`(`ClienteId`,`Monto`,`Activa`,`CBU`,`TipoId`,`FechaDeCreacion`) VALUES ("+obj.getCliente().getId()+","+obj.getMonto()+","+obj.isActiva()+","+obj.getCBU()+","+obj.getTipo().getId()+",'"+new SimpleDateFormat("yyyyMMdd").format(obj.getFechaDeCreacion())+"')";
		if(obj.getId() > 0)
		{
			query = "UPDATE Cuenta SET ClienteId = "+obj.getCliente().getId()+", Monto = "+obj.getMonto()+", Activa = "+obj.isActiva()+", CBU = "+obj.getCBU()+", TipoId = "+obj.getTipo().getId()+", FechaDeCreacion = '"+new SimpleDateFormat("yyyyMMdd").format(obj.getFechaDeCreacion()) +"' WHERE Id = " + obj.getId();
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
		
	}

	@Override
	public boolean insertarCuenta(Cuenta cuenta) {
		
		String query = "INSERT INTO Cuenta (ClienteId, Monto, CBU, TipoId, FechaDeCreacion) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = (PreparedStatement) conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cuenta.getCliente().getId());
            ps.setFloat(2, cuenta.getMonto());
            ps.setLong(3, cuenta.getCBU()); // Generado previamente
            ps.setInt(4, cuenta.getTipo().getId());
            ps.setDate(5, new java.sql.Date(cuenta.getFechaDeCreacion().getTime()));

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conexion.close();
        }
    }

	@Override
	public int ValidarCantidad(int clienteId) {
		int cantidadCuentas = 0;
		Conexion cn = new Conexion();
		
		String query = "SELECT COUNT(*) AS Cantidadcuentas FROM CUENTA where ClienteId = ? AND Activa = 1";
		try {
			cn.Open();
			 PreparedStatement preparedStatement = (PreparedStatement) cn.prepareStatement(query);
		     preparedStatement.setInt(1, clienteId);
		     
		     ResultSet rs = preparedStatement.executeQuery();
		     
		     
		     if (rs.next()) {
		            cantidadCuentas = rs.getInt("Cantidadcuentas");
		      }


		        rs.close();
		        preparedStatement.close();
			
			
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		finally
		{
			cn.close();
		}
		
		 System.out.println("CUENTA DAO CANTIDAD CUENTAS : " + cantidadCuentas);
		return cantidadCuentas;
		
	}

	@Override
	public boolean actualizarEstadoCuenta(int cuentaId, boolean estado) {
		Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = false;
        
        try {
            conn = conexion.Open();
            String query = "UPDATE cuenta SET Activa = ? WHERE id = ?";
            stmt = (PreparedStatement) conn.prepareStatement(query);
            stmt.setBoolean(1, estado);
            stmt.setInt(2, cuentaId);
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return resultado;
	}


		
	}

