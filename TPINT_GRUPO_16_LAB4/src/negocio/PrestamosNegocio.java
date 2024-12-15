package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.CuotaPrestamo;
import entidad.Prestamo;

public interface PrestamosNegocio {
	
	public boolean Insert(Prestamo prestamo);
	public boolean EliminacionLogica(Prestamo prestamo);  // Baja lógica
	public boolean Update(Prestamo prestamo);
	public List<Prestamo> BuscarTodos();
	public ArrayList<Prestamo> BuscarAcivos();
	public Prestamo BuscarUno(int nroCuenta);
	public int ContarPrestamos();
	public List<Prestamo> BuscarByIdCliente(int id);
	public List<CuotaPrestamo> ObtenerCuota(int codPrestamo);
	public boolean Update(Prestamo prestamo, int nuevoEstado);
	
}