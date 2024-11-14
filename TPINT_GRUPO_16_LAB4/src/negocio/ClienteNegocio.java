package negocio;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteNegocio {
	public Cliente Obtener(int id);
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni);
	public int Guardar(Cliente obj);
}
