package negocio;

import java.util.ArrayList;
import java.util.Map;

import entidad.CuentaTipo;
import entidad.ReporteClientesCuentas;

public interface ReporteNegocio {

	public double obtenerSaldoTotalCuentas(String fechaInicio, String fechaFin);
	public Map<String, Object> obtenerCuentaPorTipo(String fechaInicio, String fechaFin);
	public Map<String, Object> obtenerValoresTotal(String fechaInicio, String fechaFin);
	public ArrayList<ReporteClientesCuentas> ObtenerReporteClientesCuentas(String fechaInicio, String fechaFin);
}
