package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.CuentaTipoDao;
import entidad.CuentaTipo;

public class CuentaTipoDaoImpl implements CuentaTipoDao {

    private Conexion conexion;

    public CuentaTipoDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public CuentaTipo Obtener(int id) throws SQLException {
        CuentaTipo obj = new CuentaTipo();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM CuentaTipo WHERE Id = ?";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public ArrayList<CuentaTipo> ObtenerTodos() throws SQLException {
        ArrayList<CuentaTipo> objs = new ArrayList<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM CuentaTipo";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CuentaTipo obj = new CuentaTipo();
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
                objs.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return objs;
    }

    @Override
    public CuentaTipo Obtener(String descripcion) throws SQLException {
        CuentaTipo obj = new CuentaTipo();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM CuentaTipo WHERE Descripcion = ?";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setString(1, descripcion);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public ArrayList<CuentaTipo> ObtenerTodosLosTiposDeCuenta() {
        ArrayList<CuentaTipo> tiposDeCuenta = new ArrayList<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM CuentaTipo";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CuentaTipo tipo = new CuentaTipo();
                tipo.setId(rs.getInt("Id"));
                tipo.setDescripcion(rs.getString("Descripcion"));
                tiposDeCuenta.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return tiposDeCuenta;
    }
}
