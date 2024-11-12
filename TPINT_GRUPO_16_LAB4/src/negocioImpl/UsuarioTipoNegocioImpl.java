package negocioImpl;

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

}
