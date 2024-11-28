package negocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {
	public Cuenta Obtener(int id);
	public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipo, float monto);
	public ArrayList<Cuenta> ObtenerCuentasxClienteID (int ID);
	public ArrayList<Cuenta> listarTodasLAsCuentas();
	public ArrayList<Cuenta> ObtenerPorUsuario(int usuarioId);
	public Cuenta ObtenerPorCBU(long cbu);
	public void Guardar(Cuenta obj);
	boolean insertarCuenta(Cuenta cuenta);
	public boolean ValidarCantidad(int clienteId);
	public boolean actualizarEstadoCuenta(int cuentaId, boolean estado);
}
