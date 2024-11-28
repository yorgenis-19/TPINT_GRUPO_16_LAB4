package negocioImpl;

import java.util.ArrayList;

import dao.MovimientoTipoDao;
import daoImpl.MovimientoTipoDaoImpl;
import entidad.MovimientoTipo;
import negocio.MovimientoTipoNegocio;

public class MovimientoTipoNegocioImpl implements MovimientoTipoNegocio {

	@Override
	public MovimientoTipo Obtener(int id) {
		MovimientoTipo obj = new MovimientoTipo();
		MovimientoTipoDao dao = new MovimientoTipoDaoImpl();
		try
		{
			obj = dao.Obtener(id);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return obj;
	}

	@Override
	public ArrayList<MovimientoTipo> ObtenerTodos() {
		ArrayList<MovimientoTipo> objs = new ArrayList<MovimientoTipo>();
		MovimientoTipoDao dao = new MovimientoTipoDaoImpl();
		try
		{
			objs = dao.ObtenerTodos();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

	@Override
	public MovimientoTipo Obtener(String descripcion) {
		MovimientoTipo obj = new MovimientoTipo();
		MovimientoTipoDao dao = new MovimientoTipoDaoImpl();
		try
		{
			obj = dao.Obtener(descripcion);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return obj;
	}

}
