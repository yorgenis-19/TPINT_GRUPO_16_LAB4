package entidad;

import java.time.LocalDate;

public class Movimiento {
	private int Id;
	private int CuentaOrigenId;
	private int CuentaDestinoId;
	private int Importe;
	private LocalDate Fecha;
	private String Detalle;
	private int TipoId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public int getCuentaOrigenId() {
		return CuentaOrigenId;
	}
	public void setCuentaOrigenId(int cuentaOrigenId) {
		this.CuentaOrigenId = cuentaOrigenId;
	}
	public int getCuentaDestinoId() {
		return CuentaDestinoId;
	}
	public void setCuentaDestinoId(int cuentaDestinoId) {
		this.CuentaDestinoId = cuentaDestinoId;
	}
	public int getImporte() {
		return Importe;
	}
	public void setImporte(int importe) {
		this.Importe = importe;
	}
	public LocalDate getFecha() {
		return Fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.Fecha = fecha;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		this.Detalle = detalle;
	}
	public int getIdTipo() {
		return TipoId;
	}
	public void setIdTipo(int idTipo) {
		this.TipoId = idTipo;
	}
}
