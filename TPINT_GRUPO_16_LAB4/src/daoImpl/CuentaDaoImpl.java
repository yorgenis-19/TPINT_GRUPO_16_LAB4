package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaTipo;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaTipoNegocioImpl;

public class CuentaDaoImpl implements CuentaDao {
    private Conexion conexion;

    public CuentaDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public Cuenta Obtener(int id) {
        Cuenta obj = new Cuenta();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Cuenta WHERE Id = ?";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                CuentaTipo tipo = new CuentaTipoDaoImpl().Obtener(rs.getInt("TipoId"));
                obj.setTipo(tipo);
                Cliente cliente = new ClienteDaoImpl().Obtener(rs.getInt("ClienteId"));
                obj.setCliente(cliente);
                obj.setMonto(rs.getFloat("Monto"));
                obj.setActiva(rs.getBoolean("Activa"));
                obj.setCBU(rs.getLong("CBU"));
                obj.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
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
    public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipoId, float monto) {
        ArrayList<Cuenta> objs = new ArrayList<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Cuenta WHERE Monto = ?";

        if (clienteId > 0) {
            query += " AND ClienteId = ?";
        }
        if (tipoId > 0) {
            query += " AND TipoId = ?";
        }
        if (cbu > 0) {
            query += " AND CBU = ?";
        }

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setFloat(1, monto);

            int index = 2;
            if (clienteId > 0) {
                preparedStatement.setInt(index++, clienteId);
            }
            if (tipoId > 0) {
                preparedStatement.setInt(index++, tipoId);
            }
            if (cbu > 0) {
                preparedStatement.setInt(index, cbu);
            }

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cuenta obj = new Cuenta();
                obj.setId(rs.getInt("Id"));
                CuentaTipo tipo = new CuentaTipoDaoImpl().Obtener(rs.getInt("TipoId"));
                obj.setTipo(tipo);
                Cliente cliente = new ClienteDaoImpl().Obtener(rs.getInt("ClienteId"));
                obj.setCliente(cliente);
                obj.setMonto(rs.getFloat("Monto"));
                obj.setActiva(rs.getBoolean("Activa"));
                obj.setCBU(rs.getLong("CBU"));
                obj.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
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
    public ArrayList<Cuenta> ObtenerCuentasxClienteID(int ID) {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT c.Id AS cuenta_id, c.Monto, c.CBU, " +
                "ct.Descripcion AS tipo_descripcion, " +
                "cl.Nombre, cl.Apellido " +
                "FROM Cuenta c " +
                "INNER JOIN CuentaTipo ct ON c.TipoId = ct.Id " +
                "INNER JOIN Cliente cl ON c.ClienteId = cl.Id " +
                "WHERE c.ClienteId = ? AND c.Activa = 1";
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                Cliente cliente = new Cliente();
                CuentaTipo tipoCuenta = new CuentaTipo();

                cuenta.setId(rs.getInt("cuenta_id"));
                cuenta.setMonto(rs.getFloat("Monto"));
                cuenta.setCBU(rs.getLong("CBU"));

                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cuenta.setCliente(cliente);

                tipoCuenta.setDescripcion(rs.getString("tipo_descripcion"));
                cuenta.setTipo(tipoCuenta);

                cuentas.add(cuenta);
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
        return cuentas;
    }

    @Override
    public ArrayList<Cuenta> listarTodasLAsCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT c.Id AS cuenta_id, c.Monto, c.CBU, " +
                "ct.Descripcion AS tipo_descripcion, " +
                "cl.Nombre, cl.Apellido, cl.Dni, " +
                "c.FechaDeCreacion, c.Activa " +
                "FROM Cuenta c " +
                "INNER JOIN CuentaTipo ct ON c.TipoId = ct.Id " +
                "INNER JOIN Cliente cl ON c.ClienteId = cl.Id";
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                Cliente cliente = new Cliente();
                CuentaTipo tipoCuenta = new CuentaTipo();

                cuenta.setId(rs.getInt("cuenta_id"));
                cuenta.setMonto(rs.getFloat("Monto"));
                cuenta.setCBU(rs.getLong("CBU"));
                cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
                cuenta.setActiva(rs.getBoolean("Activa"));

                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setDni(rs.getString("Dni"));
                cuenta.setCliente(cliente);

                tipoCuenta.setDescripcion(rs.getString("tipo_descripcion"));
                cuenta.setTipo(tipoCuenta);

                cuentas.add(cuenta);
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
        return cuentas;
    }

    @Override
    public ArrayList<Cuenta> ObtenerPorUsuario(int usuarioId) {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT cu.* FROM cuenta cu INNER JOIN Cliente cl ON cl.Id = cu.ClienteId WHERE cl.UsuarioId = ? AND cu.Activa = 1";
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, usuarioId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                Cliente cliente = new Cliente();
                CuentaTipo tipoCuenta = new CuentaTipo();

                cliente = new ClienteNegocioImpl().Obtener(rs.getInt("ClienteId"));
                cuenta.setCliente(cliente);
                cuenta.setId(rs.getInt("Id"));
                cuenta.setMonto(rs.getFloat("Monto"));
                cuenta.setCBU(rs.getLong("CBU"));
                cuenta.setActiva(rs.getBoolean("Activa"));
                cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
                tipoCuenta = new CuentaTipoNegocioImpl().Obtener(rs.getInt("TipoId"));
                cuenta.setTipo(tipoCuenta);

                cuentas.add(cuenta);
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
        return cuentas;
    }

    @Override
    public int CrearCuenta(int ID, int TipoCuenta) {
        int estado = 0;
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Cuenta (ClienteId, Activa, CBU, TipoId, FechaDeCreacion) VALUES (?, ?, ?, ?, ?)";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setBoolean(2, true);
            preparedStatement.setLong(3, generarCBU());
            preparedStatement.setInt(4, TipoCuenta);
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));

            estado = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return estado;
    }

    private long generarCBU() {
        return (long) (Math.random() * 1_000_000_000_000_000L);
    }

    @Override
    public Cuenta ObtenerPorCBU(long cbu) {
        Cuenta obj = new Cuenta();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Cuenta WHERE CBU = ?";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setLong(1, cbu);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                CuentaTipo tipo = new CuentaTipoDaoImpl().Obtener(rs.getInt("TipoId"));
                obj.setTipo(tipo);
                Cliente cliente = new ClienteDaoImpl().Obtener(rs.getInt("ClienteId"));
                obj.setCliente(cliente);
                obj.setMonto(rs.getFloat("Monto"));
                obj.setActiva(rs.getBoolean("Activa"));
                obj.setCBU(rs.getLong("CBU"));
                obj.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
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
    public void Guardar(Cuenta obj) {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Cuenta (ClienteId, Monto, Activa, CBU, TipoId, FechaDeCreacion) VALUES (?, ?, ?, ?, ?, ?)";

        if (obj.getId() > 0) {
            query = "UPDATE Cuenta SET ClienteId = ?, Monto = ?, Activa = ?, CBU = ?, TipoId = ?, FechaDeCreacion = ? WHERE Id = ?";
        }

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);

            preparedStatement.setInt(1, obj.getCliente().getId());
            preparedStatement.setFloat(2, obj.getMonto());
            preparedStatement.setBoolean(3, obj.isActiva());
            preparedStatement.setLong(4, obj.getCBU());
            preparedStatement.setInt(5, obj.getTipo().getId());
            preparedStatement.setDate(6, new java.sql.Date(obj.getFechaDeCreacion().getTime()));

            if (obj.getId() > 0) {
                preparedStatement.setInt(7, obj.getId());
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean insertarCuenta(Cuenta cuenta) {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Cuenta (ClienteId, Monto, CBU, TipoId, FechaDeCreacion) VALUES (?, ?, ?, ?, ?)";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, cuenta.getCliente().getId());
            preparedStatement.setFloat(2, cuenta.getMonto());
            preparedStatement.setLong(3, cuenta.getCBU());
            preparedStatement.setInt(4, cuenta.getTipo().getId());
            preparedStatement.setDate(5, new java.sql.Date(cuenta.getFechaDeCreacion().getTime()));

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public int ValidarCantidad(int clienteId) {
        int cantidadCuentas = 0;
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) AS Cantidadcuentas FROM Cuenta WHERE ClienteId = ? AND Activa = 1";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setInt(1, clienteId);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                cantidadCuentas = rs.getInt("Cantidadcuentas");
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
        return cantidadCuentas;
    }

    @Override
    public boolean actualizarEstadoCuenta(int cuentaId, boolean estado) {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        boolean resultado = false;
        String query = "UPDATE Cuenta SET Activa = ? WHERE Id = ?";

        try {
            cn = conexion.Open();
            preparedStatement = cn.prepareStatement(query);
            preparedStatement.setBoolean(1, estado);
            preparedStatement.setInt(2, cuentaId);

            int filasAfectadas = preparedStatement.executeUpdate();
            resultado = filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (cn != null) conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }
    
    public Cuenta ObtenerCuentaxNroCuenta(int nroCuenta) {
    	String query = "SELECT * FROM Cuenta WHERE Id = ?";
        try (Connection cn = conexion.Open();
             PreparedStatement ps = cn.prepareStatement(query)) {
            
            ps.setInt(1, nroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setId(rs.getInt("Id"));
                    cuenta.setCBU(rs.getLong("CBU"));
                    cuenta.setMonto(rs.getFloat("Monto"));
                    // Asegúrate de cargar el cliente
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("ClienteId")); // Ajusta el nombre de la columna
                    cuenta.setCliente(cliente);
                    return cuenta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
