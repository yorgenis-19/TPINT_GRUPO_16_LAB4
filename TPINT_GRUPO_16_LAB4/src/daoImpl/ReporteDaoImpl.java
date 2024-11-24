package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.ReporteDao;

public class ReporteDaoImpl implements ReporteDao {
	public double obtenerSaldoTotalCuentas(String fechaInicio, String fechaFin) {
		
        double saldoTotal = 0;
        Conexion conexion = new Conexion();
        Connection conn = (Connection) conexion.Open();
        
        String query = "SELECT SUM(Monto) FROM Cuenta WHERE FechaDeCreacion BETWEEN ? AND ?";
        
        try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
            stmt.setString(1, fechaInicio);
            stmt.setString(2, fechaFin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                saldoTotal = rs.getDouble(1); // Obtiene el saldo total
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.close(); // Cerramos la conexión
        }
        return saldoTotal;
    }
}
