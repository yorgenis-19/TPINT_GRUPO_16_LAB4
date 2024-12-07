package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import dao.ReporteDao;
import entidad.CuentaTipo;

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
	public Map<String, Object> obtenerCuentaPorTipo(String fechaInicio, String fechaFin) {
	    Conexion conexion = new Conexion();
	    Map<String, Object> resultado = new HashMap<String, Object>();

	    String query = "SELECT CuentaTipo.Descripcion, COUNT(Cuenta.Id) AS TotalCuentas, " +
                "SUM(CASE WHEN Cuenta.Activa = 1 THEN 1 ELSE 0 END) AS CuentasActivas, " +
                "SUM(CASE WHEN Cuenta.Activa = 0 THEN 1 ELSE 0 END) AS CuentasInactivas " +
                "FROM Cuenta " +
                "INNER JOIN CuentaTipo ON Cuenta.TipoId = CuentaTipo.Id " +
                "WHERE FechaDeCreacion BETWEEN ? AND ? " +
                "GROUP BY CuentaTipo.Descripcion";

	    try {
	        conexion.Open(); // Abre la conexión
	        PreparedStatement preparedStatement = (PreparedStatement) conexion.prepareStatement(query);

	        // Establece los parámetros
	        preparedStatement.setString(1, fechaInicio);
	        preparedStatement.setString(2, fechaFin);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Procesa los resultados
	        while (resultSet.next()) {
	            String descripcion = resultSet.getString("Descripcion");
	            int totalCuentas = resultSet.getInt("TotalCuentas");
	            int cuentasActivas = resultSet.getInt("CuentasActivas");
	            int cuentasInactivas = resultSet.getInt("CuentasInactivas");
	            
	            Map<String, Integer> cuentaData = new HashMap<>();
	            cuentaData.put("TotalCuentas", totalCuentas);
	            cuentaData.put("CuentasActivas", cuentasActivas);
	            cuentaData.put("CuentasInactivas", cuentasInactivas);
	  
	            resultado.put(descripcion, cuentaData);
	        }
	        resultSet.close();
	        preparedStatement.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.close(); // Cierra la conexión
	    }

	    return resultado;
	}


}
