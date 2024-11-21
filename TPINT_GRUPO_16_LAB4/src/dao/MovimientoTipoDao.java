package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.MovimientoTipo;

public interface MovimientoTipoDao {
	public MovimientoTipo Obtener(int id) throws SQLException;
	public MovimientoTipo Obtener(String descripcion) throws SQLException;
	public ArrayList<MovimientoTipo> ObtenerTodos() throws SQLException;
}
