package dao;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteDao {
	public Cliente Obtener(int id);
	public ArrayList<Cliente> Obtener(String nombre, String apellido, String Email, String dni);
}
