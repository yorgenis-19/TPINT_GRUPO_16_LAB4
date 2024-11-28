package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Prestamo {
	private int id;
	private int clienteId;
	private int cuentaId;
	private Date fechaAlta;
	private BigDecimal montoSolicitado;
	private int idEstadoPrestamo;
	private int cantidadCuotas;
	private BigDecimal importeMensualAPagar;
	
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
	public Integer getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(BigDecimal montoSolicitado) {
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
	public BigDecimal getImporteMensualAPagar() {
		return importeMensualAPagar;
	}
	public void setImporteMensualAPagar(BigDecimal importeMensualAPagar) {
		this.importeMensualAPagar = importeMensualAPagar;
	}
	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", clienteId=" + clienteId + ", cuentaId=" + cuentaId + ", fechaAlta=" + fechaAlta
				+ ", montoSolicitado=" + montoSolicitado + ", idEstadoPrestamo=" + idEstadoPrestamo
				+ ", cantidadCuotas=" + cantidadCuotas + ", importeMensualAPagar=" + importeMensualAPagar + "]";
	}
	public void setFechaAlta(java.sql.Date date) {
	    this.fechaAlta = date;
	}
	
	public Prestamo(int id, int clienteId, int cuentaId, Date fechaAlta, BigDecimal montoSolicitado, int estadoId, int cantidadDeCuotas, BigDecimal importeMensualAPagar) {
	    this.id = id;
	    this.clienteId = clienteId;
	    this.cuentaId = cuentaId;
	    this.fechaAlta = fechaAlta;
	    this.montoSolicitado = montoSolicitado;
	    this.idEstadoPrestamo = estadoId;
	    this.cantidadCuotas = cantidadDeCuotas;
	    this.importeMensualAPagar = importeMensualAPagar;
	}
	
}
