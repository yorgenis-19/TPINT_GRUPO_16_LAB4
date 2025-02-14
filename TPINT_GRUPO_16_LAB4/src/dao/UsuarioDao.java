package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioDao {
	public Usuario Obtener(String nombre, String clave) throws SQLException;
	public Usuario Obtener(int id);
	public int Guardar(Usuario obj);
	public ArrayList<Usuario> Obtener(String nombre, int tipoId, String activo);
}
