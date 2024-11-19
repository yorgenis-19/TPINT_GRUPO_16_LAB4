package negocioImpl;

import java.util.ArrayList;

import dao.ClienteDao;
import dao.UsuarioDao;
import daoImpl.ClienteDaoImpl;
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

	@Override
	public Usuario Obtener(int id) {
		Usuario obj = new Usuario();
		UsuarioDao dao = new UsuarioDaoImpl();
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
	public int Guardar(Usuario obj) {
		int filas = 0;
		UsuarioDao dao = new UsuarioDaoImpl();
		try {
			filas = dao.Guardar(obj);
		} catch(Exception e)
		{
			e.getStackTrace();
		}
		return filas;
	}

	@Override
	public ArrayList<Usuario> Obtener(String nombre, int tipoId, String activo) {
		ArrayList<Usuario> obj = new ArrayList<Usuario>();
		UsuarioDao dao = new UsuarioDaoImpl();
		try
		{
			obj = dao.Obtener(nombre,tipoId,activo);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return obj;
	}
}
