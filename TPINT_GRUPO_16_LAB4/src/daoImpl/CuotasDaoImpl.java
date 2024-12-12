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
	private static final String pagarCuota = "{CALL SP_PAGO_CUOTA(?,?,?,?,?,?)}";
	
	
	public boolean pagarCuota(int NroCuenta,int idCuota, BigDecimal saldo, String detalle, int idCliente) {
		
		boolean isInsertExitoso = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(host+dbName,user,pass);
			conexion.setAutoCommit(false);
			CallableStatement cs = (CallableStatement) conexion.prepareCall(pagarCuota);
			cs.setInt(1, idCuota);
			cs.setInt(2, NroCuenta);
			cs.setInt(3, 3);
			cs.setFloat(4,saldo.floatValue());
			cs.setString(5, detalle);
			cs.setInt(6, idCliente);
			if (cs.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
				System.out.println("El registro fue exitoso: " + isInsertExitoso);
			}
			else {
				conexion.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error de base de datos al ejecutar SP_PAGO_CUOTA");
		}
		return isInsertExitoso;	
	}
}
