package negocioImpl;

import java.util.ArrayList;

import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	@Override
	public ArrayList<Provincia> ObtenerTodos() {
		ArrayList<Provincia> objs = new ArrayList<Provincia>();
		ProvinciaDao dao = new ProvinciaDaoImpl();
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

	public Provincia Obtener(int provinciaId) {
		Provincia objs = new Provincia();
		ProvinciaDao dao = new ProvinciaDaoImpl();
		try
		{
			objs = dao.Obtener(provinciaId);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

}
