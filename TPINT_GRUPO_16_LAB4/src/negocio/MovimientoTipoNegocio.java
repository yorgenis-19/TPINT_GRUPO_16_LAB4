package negocio;

import java.util.ArrayList;

import entidad.MovimientoTipo;

public interface MovimientoTipoNegocio {
	public MovimientoTipo Obtener(int id);
	public MovimientoTipo Obtener(String descripcion);
	public ArrayList<MovimientoTipo> ObtenerTodos();
}
