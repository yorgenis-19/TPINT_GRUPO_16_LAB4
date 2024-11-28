package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Prestamo;

public interface PrestamosNegocio {
	
	public boolean Insert(Prestamo prestamo);
	public boolean EliminacionLogica(Prestamo prestamo);  // Baja lógica
	public boolean Update(Prestamo prestamo);
	public List<Prestamo> BuscarTodos();
	public ArrayList<Prestamo> BuscarAcivos();
	public Prestamo BuscarUno(int nroCuenta);
	public int ContarPrestamos();
	
}