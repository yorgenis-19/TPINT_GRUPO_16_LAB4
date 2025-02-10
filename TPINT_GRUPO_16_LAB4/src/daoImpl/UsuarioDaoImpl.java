package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.UsuarioDao;
import entidad.Usuario;
import entidad.UsuarioTipo;

public class UsuarioDaoImpl implements UsuarioDao {

    private Conexion conexion;

    public UsuarioDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public Usuario Obtener(String nombre, String clave) throws SQLException {
        Usuario usuario = new Usuario();
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            String query = "SELECT * FROM Usuario WHERE Nombre = ? AND Clave = ?";
            statement = cn.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, clave);
            rs = statement.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("Id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setClave(rs.getString("Clave"));
                UsuarioTipo tipo = new UsuarioTipoDaoImpl().Obtener(rs.getInt("TipoId"));
                usuario.setTipo(tipo);
                usuario.setActivo(rs.getBoolean("Activo"));
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
    public Usuario Obtener(int id) {
        Usuario usuario = new Usuario();
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            String query = "SELECT * FROM Usuario WHERE Id = ?";
            statement = cn.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("Id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setClave(rs.getString("Clave"));
                UsuarioTipo tipo = new UsuarioTipoDaoImpl().Obtener(rs.getInt("TipoId"));
                usuario.setTipo(tipo);
                usuario.setActivo(rs.getBoolean("Activo"));
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
    public int Guardar(Usuario obj) {
        Connection cn = null;
        PreparedStatement statement = null;
        int filas = 0;

        String query = "INSERT INTO Usuario (Nombre, Clave, TipoId, Activo) VALUES (?, ?, ?, ?)";
        if (obj.getId() > 0) {
            query = "UPDATE Usuario SET Nombre = ?, Clave = ?, TipoId = ?, Activo = ? WHERE Id = ?";
        }

        try {
            cn = conexion.Open();
            statement = cn.prepareStatement(query);
            statement.setString(1, obj.getNombre());
            statement.setString(2, obj.getClave());
            statement.setInt(3, obj.getTipo().getId());
            statement.setBoolean(4, obj.getActivo());

            if (obj.getId() > 0) {
                statement.setInt(5, obj.getId());
            }

            filas = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return filas;
    }

    @Override
    public ArrayList<Usuario> Obtener(String nombre, int tipoId, String activo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            StringBuilder query = new StringBuilder("SELECT * FROM Usuario WHERE Nombre LIKE ?");
            if (tipoId != 0) {
                query.append(" AND TipoId = ?");
            }
            if (activo.equals("ACTIVO")) {
                query.append(" AND Activo = true");
            } else if (activo.equals("BAJA")) {
                query.append(" AND Activo = false");
            }

            statement = cn.prepareStatement(query.toString());
            statement.setString(1, "%" + nombre + "%");
            if (tipoId != 0) {
                statement.setInt(2, tipoId);
            }

            rs = statement.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("Id"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setClave(rs.getString("Clave"));
                UsuarioTipo tipo = new UsuarioTipoDaoImpl().Obtener(rs.getInt("TipoId"));
                usuario.setTipo(tipo);
                usuario.setActivo(rs.getBoolean("Activo"));
                usuarios.add(usuario);
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
        return usuarios;
    }
}
