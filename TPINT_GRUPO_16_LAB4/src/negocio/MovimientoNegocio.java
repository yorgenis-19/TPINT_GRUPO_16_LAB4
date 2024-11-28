package negocio;

import java.util.ArrayList;

import entidad.Movimiento;

public interface MovimientoNegocio {
	public ArrayList<Movimiento> ObtenerPorCuenta(int cuentaId);
	public int Guardar(Movimiento obj);
}
