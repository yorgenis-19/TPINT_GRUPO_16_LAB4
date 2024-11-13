package dao;

import entidad.Usuario;

public interface UsuarioDao {
	public Usuario Obtener(String nombre, String clave);
	public Usuario Obtener(int id);
}
