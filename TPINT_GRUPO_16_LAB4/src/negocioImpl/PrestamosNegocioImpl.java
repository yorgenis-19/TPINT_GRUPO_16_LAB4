package negocioImpl;

import java.util.List;

import dao.PrestamosDao;
import daoImpl.PrestamosDaoImpl;
import entidad.CuotaPrestamo;
import entidad.Prestamo;
import negocio.PrestamosNegocio;

public class PrestamosNegocioImpl implements PrestamosNegocio {
	
	PrestamosDao pxaDao = new PrestamosDaoImpl();
	
	@Override
	public boolean Insert(Prestamo prestamo) {

		boolean estado = false;
		estado = pxaDao.Insert(prestamo);
		return estado;
	}
	

	@Override
	public boolean EliminacionLogica(Prestamo prestamo) {
		boolean estado=false;
		if( prestamo.getCuentaId()  > 0 )
		{
			estado=pxaDao.EliminacionLogica(prestamo);
		}
		return estado;
	}
	
	
	
	@Override
	public List<Prestamo> BuscarTodos() {
		List<Prestamo> prestamo;
		prestamo = pxaDao.BuscarTodos();
		return prestamo;
	
	}

	@Override
	public Prestamo BuscarUno(int nroPrestamo) {
		Prestamo prestamo = new Prestamo();
		prestamo = pxaDao.BuscarUno(nroPrestamo);
		return prestamo; 
	}

	@Override
	public boolean Update(Prestamo prestamo) {
		boolean estado=false;
		if( prestamo.getIdEstadoPrestamo()  > 0 )
		{
			estado=pxaDao.Update(prestamo);
			
		}
		return estado;
	
	}
	public int ContarPrestamos() {

		int cant;
		cant = pxaDao.ContarPrestamos();
		return cant;
	}

	@Override
	public List<Prestamo> BuscarAcivos() {
		List<Prestamo> prestamo;
		prestamo = pxaDao.BuscarActivos();
		return prestamo;
	
	}

	PrestamosDaoImpl prestamos = new PrestamosDaoImpl();
	public List<Prestamo> LeerDni(String dni){
		return prestamos.BuscarDni(dni);
	}
	
	public List<CuotaPrestamo> ObtenerCuota(int codPrestamo){
		return prestamos.ObtenerCuota(codPrestamo);
	}
	
	
}