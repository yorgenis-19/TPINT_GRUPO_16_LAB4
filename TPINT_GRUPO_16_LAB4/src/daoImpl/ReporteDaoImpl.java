package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import dao.ReporteDao;
import entidad.Cliente;
import entidad.CuentaTipo;
import entidad.ReporteClientesCuentas;
import entidad.Usuario;

public class ReporteDaoImpl implements ReporteDao {
	
	public Map<String, Object> obtenerValoresTotal(String fechaInicio, String fechaFin) {
		
        double saldoTotal = 0;
        Conexion conexion = new Conexion();
        Connection conn = (Connection) conexion.Open();
	    Map<String, Object> resultado = new HashMap<String, Object>();
        
        //String query = "SELECT SUM(Monto) FROM Cuenta WHERE Activa = 1 AND FechaDeCreacion BETWEEN ? AND ?";
        String query = "CALL ObtenerValoresTotales(?, ?);";
        
        try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
            stmt.setString(1, fechaInicio);
            stmt.setString(2, fechaFin);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //saldoTotal = rs.getDouble(1); // Obtiene el saldo total
            	resultado.put(rs.getString("Tipo"), rs.getString("Valor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.close(); // Cerramos la conexión
        }
        return resultado;
    }
	public Map<String, Object> obtenerCuentaPorTipo(String fechaInicio, String fechaFin) {
	    Conexion conexion = new Conexion();
	    Map<String, Object> resultado = new HashMap<String, Object>();

	    String query = "SELECT CuentaTipo.Descripcion, " +
                "COUNT(Cuenta.Id) AS TotalCuentas, " +
                "SUM(CASE WHEN Cuenta.Activa = 1 THEN 1 ELSE 0 END) AS CuentasActivas, " +
                "SUM(CASE WHEN Cuenta.Activa = 0 THEN 1 ELSE 0 END) AS CuentasInactivas, " +
                "COALESCE(SUM(CASE WHEN Prestamo.EstadoId = 2 THEN 1 ELSE 0 END), 0) AS PrestamosAprobados, " +
                "COALESCE(SUM(CASE WHEN Prestamo.EstadoId = 4 THEN 1 ELSE 0 END), 0) AS PrestamosDesaprobados, " +
                "COALESCE(SUM(CASE WHEN Prestamo.EstadoId = 1 THEN 1 ELSE 0 END), 0) AS PrestamosPendientes " +
                "FROM Cuenta " +
                "INNER JOIN CuentaTipo ON Cuenta.TipoId = CuentaTipo.Id " +
                "LEFT JOIN Prestamo ON Prestamo.CuentaId = Cuenta.Id " +
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
	            int prestamosAprobados = resultSet.getInt("PrestamosAprobados");
	            int prestamosDesaprobados = resultSet.getInt("PrestamosDesaprobados");
	            int prestamosPendientes = resultSet.getInt("PrestamosPendientes");
	            
	            Map<String, Integer> cuentaData = new HashMap<>();
	            cuentaData.put("TotalCuentas", totalCuentas);
	            cuentaData.put("CuentasActivas", cuentasActivas);
	            cuentaData.put("CuentasInactivas", cuentasInactivas);
	            cuentaData.put("PrestamosAprobados", prestamosAprobados);
	            cuentaData.put("PrestamosDesaprobados", prestamosDesaprobados);
	            cuentaData.put("PrestamosPendientes", prestamosPendientes);
	  
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
	@Override
	public double obtenerSaldoTotalCuentas(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<ReporteClientesCuentas> ObtenerReporteClientesCuentas(String fechaInicio, String fechaFin) {
		ArrayList<ReporteClientesCuentas> objs = new ArrayList<ReporteClientesCuentas>();
		Conexion conexion = new Conexion();
        Connection cn = (Connection) conexion.Open();
        try {
            Statement st = cn.createStatement();
            String query = "CALL ReporteClientesCuentas ('" + fechaInicio + "','" + fechaFin + "')";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	ReporteClientesCuentas obj = new ReporteClientesCuentas();
                obj.setUsuario(rs.getString("Usuario"));
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setSexo(rs.getString("Sexo"));
                obj.setDni(rs.getString("Dni"));
                obj.setEmail(rs.getString("Email"));
                obj.setDineroTotal(rs.getDouble("DineroTotal"));
                obj.setPrestamosSolicitados(rs.getInt("PrestamosSolicitados"));
                obj.setDineroSolicitado(rs.getDouble("DineroSolicitado"));
                obj.setDineroCuotasPagadas(rs.getDouble("DineroCuotasPagadas"));
                objs.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return objs;
	}


}
