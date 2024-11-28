package negocioImpl;

import java.util.ArrayList;

import dao.CuentaTipoDao;
import dao.UsuarioTipoDao;
import daoImpl.CuentaTipoDaoImpl;
import daoImpl.UsuarioTipoDaoImpl;
import entidad.CuentaTipo;
import entidad.UsuarioTipo;
import negocio.CuentaTipoNegocio;
import negocio.UsuarioTipoNegocio;

public class CuentaTipoNegocioImpl implements CuentaTipoNegocio {

	@Override
	public CuentaTipo Obtener(int id) {
		CuentaTipo obj = new CuentaTipo();
		CuentaTipoDao dao = new CuentaTipoDaoImpl();
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
	public ArrayList<CuentaTipo> ObtenerTodos() {
		ArrayList<CuentaTipo> objs = new ArrayList<CuentaTipo>();
		CuentaTipoDao dao = new CuentaTipoDaoImpl();
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
	public CuentaTipo Obtener(String descripcion) {
		CuentaTipo obj = new CuentaTipo();
		CuentaTipoDao dao = new CuentaTipoDaoImpl();
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

	@Override
	public ArrayList<CuentaTipo> ObtenerTodosLosTiposDeCuenta() {
		ArrayList<CuentaTipo> objs = new ArrayList<CuentaTipo>();
		CuentaTipoDao dao = new CuentaTipoDaoImpl();
		try
		{
			objs = dao.ObtenerTodosLosTiposDeCuenta();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

}
