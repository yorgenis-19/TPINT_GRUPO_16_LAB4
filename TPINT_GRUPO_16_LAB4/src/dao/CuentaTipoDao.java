package dao;

import java.util.ArrayList;

import entidad.CuentaTipo;

public interface CuentaTipoDao {
	public CuentaTipo Obtener(int id);
	public CuentaTipo Obtener(String descripcion);
	public ArrayList<CuentaTipo> ObtenerTodos();
}
