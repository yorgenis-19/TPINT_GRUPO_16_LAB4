package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.MovimientoTipoDao;
import entidad.MovimientoTipo;

public class MovimientoTipoDaoImpl implements MovimientoTipoDao {

    private Conexion conexion;

    public MovimientoTipoDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public MovimientoTipo Obtener(int id) throws SQLException {
        MovimientoTipo obj = new MovimientoTipo();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM MovimientoTipo WHERE Id = " + id;

        try {
            cn = conexion.Open();
            st = cn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
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
        return obj;
    }

    @Override
    public ArrayList<MovimientoTipo> ObtenerTodos() throws SQLException {
        ArrayList<MovimientoTipo> objs = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM MovimientoTipo";

        try {
            cn = conexion.Open();
            st = cn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                MovimientoTipo obj = new MovimientoTipo();
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
                objs.add(obj);
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
        return objs;
    }

    @Override
    public MovimientoTipo Obtener(String descripcion) throws SQLException {
        MovimientoTipo obj = new MovimientoTipo();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM MovimientoTipo WHERE Descripcion = '" + descripcion + "'";

        try {
            cn = conexion.Open();
            st = cn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
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
        return obj;
    }
}