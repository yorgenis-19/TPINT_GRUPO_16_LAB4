package negocio;

import java.util.List;

import entidad.Prestamo;

public interface PrestamosNegocio {
	
	public boolean Insert(Prestamo prestamo);
	public boolean EliminacionLogica(Prestamo prestamo);  // Baja l�gica
	public boolean Update(Prestamo prestamo);
	public List<Prestamo> BuscarTodos();
	public List<Prestamo> BuscarAcivos();
	public Prestamo BuscarUno(int nroCuenta);
	public int ContarPrestamos();
	
}