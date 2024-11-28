package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.CuentaTipo;

public interface CuentaTipoDao {
	public CuentaTipo Obtener(int id) throws SQLException;
	public CuentaTipo Obtener(String descripcion) throws SQLException;
	public ArrayList<CuentaTipo> ObtenerTodos() throws SQLException;
	public ArrayList<CuentaTipo> ObtenerTodosLosTiposDeCuenta();
}
