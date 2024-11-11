package entidad;

public class EstadoPrestamo {
	private int idEstadiPrestamo;
	private String nombreEstadoPrestamo;
	public int getIdEstadiPrestamo() {
		return idEstadiPrestamo;
	}
	public void setIdEstadiPrestamo(int idEstadiPrestamo) {
		this.idEstadiPrestamo = idEstadiPrestamo;
	}
	public String getNombreEstadoPrestamo() {
		return nombreEstadoPrestamo;
	}
	public void setNombreEstadoPrestamo(String nombreEstadoPrestamo) {
		this.nombreEstadoPrestamo = nombreEstadoPrestamo;
	}
	@Override
	public String toString() {
		return "EstadoPrestamo [idEstadiPrestamo=" + idEstadiPrestamo + ", nombreEstadoPrestamo=" + nombreEstadoPrestamo
				+ "]";
	}
	
	
}
