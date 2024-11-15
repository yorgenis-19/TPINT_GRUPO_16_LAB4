package negocio;

import java.util.ArrayList;

import entidad.CuentaTipo;

public interface CuentaTipoNegocio {
	public CuentaTipo Obtener(int id);
	public CuentaTipo Obtener(String descripcion);
	public ArrayList<CuentaTipo> ObtenerTodos();
}
