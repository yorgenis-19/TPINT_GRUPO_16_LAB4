package negocio;

import entidad.Usuario;

public interface UsuarioNegocio {
	public Usuario Obtener(String nombre, String clave);
	public Usuario Obtener(int id);
}