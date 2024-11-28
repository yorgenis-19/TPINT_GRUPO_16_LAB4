package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.UsuarioTipo;

public interface UsuarioTipoDao {
	public UsuarioTipo Obtener(int id) throws SQLException;
	public UsuarioTipo Obtener(String descripcion) throws SQLException;
	public ArrayList<UsuarioTipo> ObtenerTodos() throws SQLException;
}
