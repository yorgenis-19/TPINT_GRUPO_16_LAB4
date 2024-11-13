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
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni) {
		ArrayList<Cliente> objs = new ArrayList<Cliente>();
		ClienteDao dao = new ClienteDaoImpl();
		try
		{
			objs = dao.Obtener(nombre, apellido, email, dni);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}
}
