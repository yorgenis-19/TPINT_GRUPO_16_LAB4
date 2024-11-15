package negocioImpl;

import java.util.ArrayList;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

	@Override
	public Cuenta Obtener(int id) {
		Cuenta obj = new Cuenta();
		CuentaDao dao = new CuentaDaoImpl();
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
	public ArrayList<Cuenta> Obtener(int clienteId, int cbu, int tipo, float monto) {
		ArrayList<Cuenta> objs = new ArrayList<Cuenta>();
		CuentaDao dao = new CuentaDaoImpl();
		try
		{
			objs = dao.Obtener(clienteId, cbu, tipo, monto);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

}