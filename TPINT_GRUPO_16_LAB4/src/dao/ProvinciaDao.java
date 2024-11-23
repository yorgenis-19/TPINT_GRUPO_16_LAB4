package dao;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaDao {
	public ArrayList<Provincia> ObtenerTodos();

	public Provincia Obtener(int provinciaId);
}
