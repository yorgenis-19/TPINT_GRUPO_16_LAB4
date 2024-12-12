package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import dao.CuotasDao;
import entidad.Movimiento;

public class CuotasDaoImpl implements CuotasDao{
	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";
	private static final String pagarCuota = "{CALL SP_PAGO_CUOTA(?,?,?,?,?)}";
	
	
	public boolean pagarCuota(int NroCuenta, int idCuota, BigDecimal saldo, String detalle) {
	    boolean isInsertExitoso = false;
	    Connection conexion = null;
	    CallableStatement cs = null;
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conexion = DriverManager.getConnection(host + dbName, user, pass);
	        conexion.setAutoCommit(false);  // Gestionar transacciones explícitamente
	        
	        cs = (CallableStatement) conexion.prepareCall(pagarCuota);
	        cs.setInt(1, idCuota);
	        cs.setInt(2, NroCuenta);
	        cs.setInt(3, 3);
	        cs.setFloat(4, saldo.floatValue());
	        cs.setString(5, detalle);
	        
	        int rowsAffected = cs.executeUpdate();
	        if (rowsAffected > 0) {
	            conexion.commit();
	            isInsertExitoso = true;
	        } else {
	            conexion.rollback();
	        }
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver JDBC no encontrado: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("Error de base de datos: " + e.getMessage());
	        if (conexion != null) {
	            try {
	                conexion.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    } finally {
	        // Cerrar recursos
	        try {
	            if (cs != null) cs.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return isInsertExitoso;    
	}
}
