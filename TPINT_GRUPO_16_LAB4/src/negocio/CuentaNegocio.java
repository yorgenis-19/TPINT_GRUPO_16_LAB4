package negocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {
	public Cuenta Obtener(int id);
	public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipo, float monto);
	public ArrayList<Cuenta> ObtenerCuentasxClienteID (int ID);
}
