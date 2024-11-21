package negocio;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioNegocio {
	public Usuario Obtener(String nombre, String clave);
	public Usuario Obtener(int id);
	public int Guardar(Usuario obj);
	public ArrayList<Usuario> Obtener(String nombre, int tipoId, String activo);
}
