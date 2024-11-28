package negocio;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadNegocio {
	public ArrayList<Localidad> ObtenerTodos();
	public ArrayList<Localidad> ObtenerPorProvincia(int provinciaId);
	public Localidad Obtener(int id);
}
