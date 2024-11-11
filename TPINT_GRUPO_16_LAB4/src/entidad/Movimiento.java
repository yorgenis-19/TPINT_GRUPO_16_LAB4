package entidad;

import java.time.LocalDate;

public class Movimiento {
	private int id;
	private int cuentaOrigenId;
	private int cuentaDestinoId;
	private int importe;
	private LocalDate fecha;
	private String detalle;
	private int idTipo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCuentaOrigenId() {
		return cuentaOrigenId;
	}
	public void setCuentaOrigenId(int cuentaOrigenId) {
		this.cuentaOrigenId = cuentaOrigenId;
	}
	public int getCuentaDestinoId() {
		return cuentaDestinoId;
	}
	public void setCuentaDestinoId(int cuentaDestinoId) {
		this.cuentaDestinoId = cuentaDestinoId;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", cuentaOrigenId=" + cuentaOrigenId + ", cuentaDestinoId=" + cuentaDestinoId
				+ ", importe=" + importe + ", fecha=" + fecha + ", detalle=" + detalle + ", idTipo=" + idTipo + "]";
	}
	
	
	
}
