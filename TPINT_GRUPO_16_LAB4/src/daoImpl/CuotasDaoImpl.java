package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import dao.CuotasDao;
import entidad.Movimiento;

public class CuotasDaoImpl implements CuotasDao {
    private Conexion conexion;
    private static final String pagarCuota = "{CALL SP_PAGO_CUOTA(?,?,?,?,?,?)}";

    public CuotasDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean pagarCuota(int NroCuenta, int idCuota, BigDecimal saldo, String detalle, int idCliente) {
        boolean isInsertExitoso = false;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            cn = conexion.Open();
            cn.setAutoCommit(false); // Desactivar el autocommit para manejar transacciones manualmente

            cs = (CallableStatement) cn.prepareCall(pagarCuota);
            cs.setInt(1, idCuota);
            cs.setInt(2, NroCuenta);
            cs.setInt(3, 3); // Asumo que este valor es fijo o tiene un propósito específico
            cs.setFloat(4, saldo.floatValue());
            cs.setString(5, detalle);
            cs.setInt(6, idCliente);

            if (cs.executeUpdate() > 0) {
                cn.commit(); // Confirmar la transacción si la ejecución fue exitosa
                isInsertExitoso = true;
                System.out.println("El registro fue exitoso: " + isInsertExitoso);
            } else {
                cn.rollback(); // Revertir la transacción si la ejecución falló
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de base de datos al ejecutar SP_PAGO_CUOTA");
            if (cn != null) {
                try {
                    cn.rollback(); // Revertir la transacción en caso de excepción
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Cerrar recursos en el orden inverso en que fueron abiertos
            try {
                if (cs != null) cs.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isInsertExitoso;
    }
}