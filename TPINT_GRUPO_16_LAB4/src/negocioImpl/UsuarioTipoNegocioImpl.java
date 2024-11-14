package negocioImpl;

import java.util.ArrayList;

import dao.UsuarioTipoDao;
import daoImpl.UsuarioTipoDaoImpl;
import entidad.UsuarioTipo;
import negocio.UsuarioTipoNegocio;

public class UsuarioTipoNegocioImpl implements UsuarioTipoNegocio {

	@Override
	public UsuarioTipo Obtener(int id) {
		UsuarioTipo obj = new UsuarioTipo();
		UsuarioTipoDao dao = new UsuarioTipoDaoImpl();
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
	public ArrayList<UsuarioTipo> ObtenerTodos() {
		ArrayList<UsuarioTipo> objs = new ArrayList<UsuarioTipo>();
		UsuarioTipoDao dao = new UsuarioTipoDaoImpl();
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
	public UsuarioTipo Obtener(String descripcion) {
		UsuarioTipo obj = new UsuarioTipo();
		UsuarioTipoDao dao = new UsuarioTipoDaoImpl();
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
