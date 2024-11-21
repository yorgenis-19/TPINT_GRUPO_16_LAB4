package dao;

import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaDao {
	public Cuenta Obtener(int id);
	public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipo, float monto);
	//public int Guardar(Cuenta obj);
	public ArrayList<Cuenta> ObtenerCuentasxClienteID (int ID);
	public ArrayList<Cuenta> listarTodasLAsCuentas();
}
