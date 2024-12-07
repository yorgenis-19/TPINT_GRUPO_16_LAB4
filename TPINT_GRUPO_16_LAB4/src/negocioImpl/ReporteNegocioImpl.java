package negocioImpl;

import java.util.ArrayList;
import java.util.Map;

import dao.ReporteDao;
import daoImpl.ReporteDaoImpl;
import entidad.CuentaTipo;
import negocio.ReporteNegocio;

public class ReporteNegocioImpl implements ReporteNegocio{
	private ReporteDao reportedao;
	
	public ReporteNegocioImpl() {
        reportedao = new ReporteDaoImpl(); // Inicializo el DAO en el constructor
    }
	@Override
	public double obtenerSaldoTotalCuentas(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub		
		return reportedao.obtenerSaldoTotalCuentas(fechaInicio, fechaFin);
	}
	 
	@Override
	public Map<String, Object> obtenerCuentaPorTipo(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		return reportedao.obtenerCuentaPorTipo(fechaInicio, fechaFin);
	}

}
