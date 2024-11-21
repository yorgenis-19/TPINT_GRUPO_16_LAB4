package negocio;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteNegocio {
	public Cliente Obtener(int id);
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String email, String dni, boolean activo);
	public int Guardar(Cliente obj);
	public boolean ExisteMail(int id, String email);
	public boolean ExisteDNI(int id, String dni);
	public boolean ExisteCUIL(int id, String cuil);
	public boolean ExisteUsuario(String usuario);
	public Cliente ObtenerPorUsuario(int usuarioId);
}
