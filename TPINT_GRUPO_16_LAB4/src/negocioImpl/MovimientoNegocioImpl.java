package negocioImpl;

import java.util.ArrayList;

import dao.MovimientoDao;
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

}
