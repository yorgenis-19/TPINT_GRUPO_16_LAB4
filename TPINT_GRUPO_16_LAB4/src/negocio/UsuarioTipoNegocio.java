package negocio;

import java.util.ArrayList;

import entidad.UsuarioTipo;

public interface UsuarioTipoNegocio {
	public UsuarioTipo Obtener(int id);
	public UsuarioTipo Obtener(String descripcion);
	public ArrayList<UsuarioTipo> ObtenerTodos();
}
