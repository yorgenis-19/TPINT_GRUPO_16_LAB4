package dao;

import java.util.ArrayList;

import entidad.Movimiento;


public interface MovimientoDao {
	public ArrayList<Movimiento> ObtenerPorCuenta(int cuentaId);

	public int Guardar(Movimiento mov);
}
