package negocioImpl;

import java.util.ArrayList;

import dao.ClienteDao;
import dao.MovimientoDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.MovimientoDaoImpl;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	@Override
	public ArrayList<Movimiento> ObtenerPorCuenta(int cuentaId) {
		ArrayList<Movimiento> objs = new ArrayList<Movimiento>();
		MovimientoDao dao = new MovimientoDaoImpl();
		try
		{
			objs = dao.ObtenerPorCuenta(cuentaId);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

	public int Guardar(Movimiento mov) {
		int filas = 0;
		MovimientoDao dao = new MovimientoDaoImpl();
		try {
			filas = dao.Guardar(mov);
		} catch(Exception e)
		{
			e.getStackTrace();
		}
		return filas;
	}

}
