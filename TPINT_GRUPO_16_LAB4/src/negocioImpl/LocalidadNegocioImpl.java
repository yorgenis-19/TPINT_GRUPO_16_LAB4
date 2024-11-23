package negocioImpl;

import java.util.ArrayList;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	@Override
	public ArrayList<Localidad> ObtenerTodos() {
		ArrayList<Localidad> objs = new ArrayList<Localidad>();
		LocalidadDao dao = new LocalidadDaoImpl();
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

	public ArrayList<Localidad> ObtenerPorProvincia(int provinciaId) {
		ArrayList<Localidad> objs = new ArrayList<Localidad>();
		LocalidadDao dao = new LocalidadDaoImpl();
		try
		{
			objs = dao.ObtenerPorProvincia(provinciaId);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

}
