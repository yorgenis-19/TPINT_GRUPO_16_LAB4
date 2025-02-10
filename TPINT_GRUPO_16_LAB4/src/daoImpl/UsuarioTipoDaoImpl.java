package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UsuarioTipoDao;
import entidad.UsuarioTipo;

public class UsuarioTipoDaoImpl implements UsuarioTipoDao {

    private Conexion conexion;

    public UsuarioTipoDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public UsuarioTipo Obtener(int id) throws SQLException {
        UsuarioTipo usuario = new UsuarioTipo();
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            String query = "SELECT * FROM UsuarioTipo WHERE Id = ?";
            statement = cn.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("Id"));
                usuario.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return usuario;
    }

    @Override
    public ArrayList<UsuarioTipo> ObtenerTodos() throws SQLException {
        ArrayList<UsuarioTipo> objs = new ArrayList<>();
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            String query = "SELECT * FROM UsuarioTipo";
            statement = cn.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                UsuarioTipo obj = new UsuarioTipo();
                obj.setId(rs.getInt("Id"));
                obj.setDescripcion(rs.getString("Descripcion"));
                objs.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return objs;
    }

    @Override
    public UsuarioTipo Obtener(String descripcion) throws SQLException {
        UsuarioTipo usuario = new UsuarioTipo();
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            String query = "SELECT * FROM UsuarioTipo WHERE Descripcion = ?";
            statement = cn.prepareStatement(query);
            statement.setString(1, descripcion);
            rs = statement.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("Id"));
                usuario.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return usuario;
    }
}