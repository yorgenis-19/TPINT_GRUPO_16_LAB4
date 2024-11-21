package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import dao.ClienteDao;
import dao.CuentaDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
	private CuentaDao cuentadao;
	
	public CuentaNegocioImpl() {
        cuentadao = new CuentaDaoImpl();
    }
	
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

	@Override
	public ArrayList<Cuenta> ObtenerCuentasxClienteID(int ID) {
		return cuentadao.ObtenerCuentasxClienteID(ID) ;
	}

	@Override
	public ArrayList<Cuenta> listarTodasLAsCuentas() {
		ArrayList<Cuenta> lista = null;
		lista = cuentadao.listarTodasLAsCuentas();
		return lista;
	}

	@Override
	public ArrayList<Cuenta> ObtenerPorUsuario(int usuarioId) {
		ArrayList<Cuenta> objs = new ArrayList<Cuenta>();
		CuentaDao dao = new CuentaDaoImpl();
		try
		{
			objs = dao.ObtenerPorUsuario(usuarioId);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

	@Override
	public Cuenta ObtenerPorCBU(long cbu) {
		Cuenta objs = new Cuenta();
		CuentaDao dao = new CuentaDaoImpl();
		try
		{
			objs = dao.ObtenerPorCBU(cbu);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return objs;
	}

	@Override
	public void Guardar(Cuenta obj) {
		CuentaDao dao = new CuentaDaoImpl();
		try {
			dao.Guardar(obj);
		} catch(Exception e)
		{
			e.getStackTrace();
		}
	}

	@Override
	public boolean insertarCuenta(Cuenta cuenta) {
		CuentaDao dao = new CuentaDaoImpl();
		 cuenta.setCBU(generarCBU()); // Generar el CBU único
		 cuenta.setFechaDeCreacion(new java.sql.Date(new Date().getTime())); // Fecha actual
	        return dao.insertarCuenta(cuenta);
	}
	private long generarCBU() {
        // Generar un número de 18 dígitos
        return (long) (Math.random() * 1_000_000_000_000_000_000L);
    }

}
