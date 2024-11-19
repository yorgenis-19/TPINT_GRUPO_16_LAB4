package negocioImpl;

import java.util.ArrayList;

import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	@Override
	public Cliente Obtener(int id) {
		Cliente obj = new Cliente();
		ClienteDao dao = new ClienteDaoImpl();
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
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni, boolean activo) {
		ArrayList<Cliente> objs = new ArrayList<Cliente>();
		ClienteDao dao = new ClienteDaoImpl();
		try
		{
			objs = dao.Obtener(nombre, apellido, email, dni, activo);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

	@Override
	public int Guardar(Cliente obj) {
		int filas = 0;
		ClienteDao dao = new ClienteDaoImpl();
		try {
			filas = dao.Guardar(obj);
		} catch(Exception e)
		{
			e.getStackTrace();
		}
		return filas;
	}

	@Override
	public boolean ExisteMail(int id, String email) {
		boolean res = false;
		ClienteDao dao = new ClienteDaoImpl();
		try
		{
			res = dao.ExisteMail(id,email);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return res;
	}

	@Override
	public boolean ExisteDNI(int id, String dni) {
		boolean res = false;
		ClienteDao dao = new ClienteDaoImpl();
		try
		{
			res = dao.ExisteDNI(id,dni);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return res;
	}

	@Override
	public boolean ExisteCUIL(int id, String cuil) {
		boolean res = false;
		ClienteDao dao = new ClienteDaoImpl();
		try
		{
			res = dao.ExisteCUIL(id,cuil);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return res;
	}

	@Override
	public boolean ExisteUsuario(String usuario) {
		boolean res = false;
		ClienteDao dao = new ClienteDaoImpl();
		try
		{
			res = dao.ExisteUsuario(usuario);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return res;
	}
}
