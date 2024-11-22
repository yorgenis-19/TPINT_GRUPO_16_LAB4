package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaDao {
	public Cuenta Obtener(int id);
	public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipo, float monto);
	//public int Guardar(Cuenta obj);
	public ArrayList<Cuenta> ObtenerCuentasxClienteID (int ID);
	public ArrayList<Cuenta> listarTodasLAsCuentas();
	public ArrayList<Cuenta> ObtenerPorUsuario(int usuarioId);
	public int CrearCuenta (int ID, int TipoCuenta);
	public Cuenta ObtenerPorCBU(long cbu) throws SQLException;
	public void Guardar(Cuenta obj);
	boolean insertarCuenta(Cuenta cuenta);
	public int ValidarCantidad(int clienteId);
}
