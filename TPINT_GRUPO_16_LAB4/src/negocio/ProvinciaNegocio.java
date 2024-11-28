package negocio;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaNegocio {
	public ArrayList<Provincia> ObtenerTodos();
	public Provincia Obtener(int id);
}
