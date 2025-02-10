package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.MovimientoTipo;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoTipoNegocioImpl;

public class MovimientoDaoImpl implements MovimientoDao {

    private Conexion conexion;

    public MovimientoDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public ArrayList<Movimiento> ObtenerPorCuenta(int cuentaId) {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Movimiento WHERE CuentaOrigenId = " + cuentaId + " OR CuentaDestinoId = " + cuentaId;

        try {
            cn = conexion.Open();
            st = cn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Movimiento obj = new Movimiento();
                obj.setId(rs.getInt("Id"));
                Cuenta origen = new CuentaNegocioImpl().Obtener(rs.getInt("CuentaOrigenId"));
                obj.setCuentaOrigen(origen);
                Cuenta destino = new CuentaNegocioImpl().Obtener(rs.getInt("CuentaDestinoId"));
                obj.setCuentaDestino(destino);
                obj.setImporte(rs.getFloat("Importe"));
                obj.setFecha(rs.getDate("Fecha"));
                obj.setDetalle(rs.getString("Detalle"));
                MovimientoTipo tipo = new MovimientoTipoNegocioImpl().Obtener(rs.getInt("TipoId"));
                obj.setTipo(tipo);

                movimientos.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return movimientos;
    }

    @Override
    public int Guardar(Movimiento mov) {
        Connection cn = null;
        Statement st = null;
        int filas = 0;
        String query = "INSERT INTO Movimiento (`CuentaOrigenId`, `CuentaDestinoId`, `Importe`, `Fecha`, `Detalle`, `TipoId`) VALUES (" +
                mov.getCuentaOrigen().getId() + ", " +
                mov.getCuentaDestino().getId() + ", " +
                mov.getImporte() + ", '" +
                new SimpleDateFormat("yyyyMMdd").format(mov.getFecha()) + "', '" +
                mov.getDetalle() + "', " +
                mov.getTipo().getId() + ")";

        try {
            cn = conexion.Open();
            st = cn.createStatement();
            filas = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return filas;
    }
}
