package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.ClienteDao;
import entidad.Cliente;
import entidad.Usuario;

public class ClienteDaoImpl implements ClienteDao {

    private Conexion conexion;

    public ClienteDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public Cliente Obtener(int id) {
        Cliente obj = new Cliente();
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT * FROM Cliente WHERE Id =" + id;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                Usuario usuario = new UsuarioDaoImpl().Obtener(rs.getInt("UsuarioId"));
                obj.setUsuario(usuario);
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setSexo(rs.getString("Sexo"));
                obj.setDni(rs.getString("Dni"));
                obj.setCuil(rs.getString("Cuil"));
                obj.setTelefono(rs.getString("Telefono"));
                obj.setEmail(rs.getString("Email"));
                obj.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                obj.setDireccion(rs.getString("Direccion"));
                obj.setLocalidadId(rs.getInt("LocalidadId"));
                obj.setProvinciaId(rs.getInt("ProvinciaId"));
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
        return obj;
    }

    @Override
    public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni, boolean activo) {
        ArrayList<Cliente> objs = new ArrayList<Cliente>();
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT * FROM Cliente c INNER JOIN Usuario u ON u.Id = UsuarioId WHERE u.Activo = " + activo + " AND (c.Nombre LIKE '%" + nombre + "%' ) AND (c.Apellido LIKE '%" + apellido + "%' ) AND (c.Email LIKE '%" + email + "%' ) AND (c.DNI LIKE '%" + dni + "%' )";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("Id"));
                Usuario usuario = new UsuarioDaoImpl().Obtener(rs.getInt("UsuarioId"));
                obj.setUsuario(usuario);
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setSexo(rs.getString("Sexo"));
                obj.setDni(rs.getString("Dni"));
                obj.setCuil(rs.getString("Cuil"));
                obj.setTelefono(rs.getString("Telefono"));
                obj.setEmail(rs.getString("Email"));
                obj.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                obj.setDireccion(rs.getString("Direccion"));
                obj.setLocalidadId(rs.getInt("LocalidadId"));
                obj.setProvinciaId(rs.getInt("ProvinciaId"));
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

    @Override
    public int Guardar(Cliente obj) {
        String query = "INSERT INTO Cliente(UsuarioId,Nombre,Apellido,Sexo,DNI,CUIL,Telefono,Email,FechaNacimiento,Direccion,LocalidadId,ProvinciaId)VALUES(" + obj.getUsuario().getId() + ",'" + obj.getNombre() + "','" + obj.getApellido() + "','" + obj.getSexo() + "','" + obj.getDni() + "','" + obj.getCuil() + "','" + obj.getTelefono() + "','" + obj.getEmail() + "','" + new SimpleDateFormat("yyyyMMdd").format(obj.getFechaNacimiento()) + "','" + obj.getDireccion() + "'," + obj.getLocalidadId() + "," + obj.getProvinciaId() + ")";
        if (obj.getId() > 0) {
            query = "UPDATE Cliente SET UsuarioId = " + obj.getUsuario().getId() + ", Nombre = '" + obj.getNombre() + "', Apellido = '" + obj.getApellido() + "', Sexo = '" + obj.getSexo() + "', DNI = '" + obj.getDni() + "', CUIL = '" + obj.getCuil() + "', Telefono = '" + obj.getTelefono() + "', Email = '" + obj.getEmail() + "', FechaNacimiento = '" + new SimpleDateFormat("yyyyMMdd").format(obj.getFechaNacimiento()) + "', Direccion = '" + obj.getDireccion() + "', LocalidadId = " + obj.getLocalidadId() + ", ProvinciaId = " + obj.getProvinciaId() + " WHERE ID = " + obj.getId();
        }

        Connection cn = null;
        int filas = 0;

        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            filas = st.executeUpdate(query);
        } catch (SQLException e) {
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
        return filas;
    }

    @Override
    public boolean ExisteMail(int id, String email) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT Id FROM Cliente WHERE Id != " + id + " AND Email = '" + email + "' limit 1";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getInt("Id") > 0;
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
        return res;
    }

    @Override
    public boolean ExisteDNI(int id, String dni) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT Id FROM Cliente WHERE Id != " + id + " AND DNI = '" + dni + "' limit 1";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getInt("Id") > 0;
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
        return res;
    }

    @Override
    public boolean ExisteCUIL(int id, String cuil) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT Id FROM Cliente WHERE Id != " + id + " AND CUIL = '" + cuil + "' limit 1";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getInt("Id") > 0;
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
        return res;
    }

    @Override
    public boolean ExisteUsuario(String usuario) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT Id FROM Usuario WHERE Nombre = '" + usuario + "' limit 1";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getInt("Id") > 0;
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
        return res;
    }

    @Override
    public Cliente ObtenerPorUsuario(int usuarioId) {
        Cliente obj = new Cliente();
        Connection cn = null;
        try {
            cn = conexion.Open();
            Statement st = cn.createStatement();
            String query = "SELECT * FROM Cliente WHERE UsuarioId =" + usuarioId;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                Usuario usuario = new UsuarioDaoImpl().Obtener(rs.getInt("UsuarioId"));
                obj.setUsuario(usuario);
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setSexo(rs.getString("Sexo"));
                obj.setDni(rs.getString("Dni"));
                obj.setCuil(rs.getString("Cuil"));
                obj.setTelefono(rs.getString("Telefono"));
                obj.setEmail(rs.getString("Email"));
                obj.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                obj.setDireccion(rs.getString("Direccion"));
                obj.setLocalidadId(rs.getInt("LocalidadId"));
                obj.setProvinciaId(rs.getInt("ProvinciaId"));
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
        return obj;
    }

    @Override
    public Cliente ObtenerClientePorId(int id) {
        Cliente cliente = null;
        Connection cn = null;

        try {
            cn = conexion.Open();
            String query = "SELECT c.*, u.Activo " +
                    "FROM Cliente c " +
                    "JOIN Usuario u ON c.UsuarioId = u.Id " +
                    "WHERE c.DNI = ? AND u.Activo = 1";
            PreparedStatement ps = (PreparedStatement) conexion.prepareStatement(query);
            ps.setInt(1, id);
            System.out.println("Consulta preparada: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta ejecutada.");

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("Id"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setSexo(rs.getString("Sexo"));
                cliente.setDni(rs.getString("DNI"));
                cliente.setCuil(rs.getString("CUIL"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                System.out.println("Datos del cliente obtenidos: " + cliente);
            } else {
                System.out.println("No se encontró un cliente con el ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL:");
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    conexion.close();
                    System.out.println("Conexión cerrada.");
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión:");
                    e.printStackTrace();
                }
            }
        }
        return cliente;
    }
}