package negocio;

import java.util.ArrayList;
import java.util.Map;

import entidad.CuentaTipo;

public interface ReporteNegocio {

	public double obtenerSaldoTotalCuentas(String fechaInicio, String fechaFin);
	public Map<String, Integer> obtenerCuentaPorTipo(String fechaInicio, String fechaFin);
}
