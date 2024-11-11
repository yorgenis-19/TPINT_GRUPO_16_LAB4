package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Override
	public Usuario Obtener(String nombre, String clave) {
		Usuario obj = new Usuario();
		UsuarioDao dao = new UsuarioDaoImpl();
		try
		{
			obj = dao.Obtener(nombre, clave);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return obj;
	}

}
