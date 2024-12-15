package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.CuotaPrestamo;
import entidad.Prestamo;

public interface PrestamosDao {
	
	public boolean Insert(Prestamo prestamo);
	public boolean Update(Prestamo prestamo);
	public boolean EliminacionLogica(Prestamo prestamo); // Baja lógica
	public List<Prestamo> BuscarTodos();
	public Prestamo BuscarUno(int nroCuenta);
	public ArrayList<Prestamo> BuscarActivos();
	public int ContarPrestamos();
	public List<Prestamo> BuscarByIdCliente(int id);
	public List<CuotaPrestamo> ObtenerCuota(int codPrestamo);
	public boolean Update(Prestamo prestamo, int nuevoEstado);
}