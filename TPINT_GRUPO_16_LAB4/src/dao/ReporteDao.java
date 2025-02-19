package dao;

import java.util.ArrayList;
import java.util.Map;

import entidad.CuentaTipo;

public interface ReporteDao {
	public double obtenerSaldoTotalCuentas(String fechaInicio, String fechaFin);
	public Map<String, Object> obtenerCuentaPorTipo(String fechaInicio, String fechaFin);
	public Map<String, Object> obtenerValoresTotal(String fechaInicio, String fechaFin);
}
