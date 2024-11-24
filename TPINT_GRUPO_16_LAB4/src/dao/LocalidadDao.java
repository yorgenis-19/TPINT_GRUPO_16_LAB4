package dao;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadDao {
	public ArrayList<Localidad> ObtenerTodos();

	public ArrayList<Localidad> ObtenerPorProvincia(int provinciaId);

	public Localidad Obtener(int id);
}
