package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Prestamo {
	private int id;
	private int clienteId;
	private int cuentaId;
	private LocalDate fechaAlta;
	private float montoSolicitado;
	private int idEstadoPrestamo;
	private int cantidadCuotas;
	private float importeMensualAPagar;
	public Prestamo(int nroCuenta, int codPrestamoPendinte, Date fecha_creacion, BigDecimal importe,
			int cantidad_cuotas, int estado) {
		// TODO Auto-generated constructor stub
	}
	public Prestamo() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public int getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public float getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(float montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public int getIdEstadoPrestamo() {
		return idEstadoPrestamo;
	}
	public void setIdEstadoPrestamo(int idEstadoPrestamo) {
		this.idEstadoPrestamo = idEstadoPrestamo;
	}
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	public float getImporteMensualAPagar() {
		return importeMensualAPagar;
	}
	public void setImporteMensualAPagar(float importeMensualAPagar) {
		this.importeMensualAPagar = importeMensualAPagar;
	}
	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", clienteId=" + clienteId + ", cuentaId=" + cuentaId + ", fechaAlta=" + fechaAlta
				+ ", montoSolicitado=" + montoSolicitado + ", idEstadoPrestamo=" + idEstadoPrestamo
				+ ", cantidadCuotas=" + cantidadCuotas + ", importeMensualAPagar=" + importeMensualAPagar + "]";
	}
	
	
	
}
