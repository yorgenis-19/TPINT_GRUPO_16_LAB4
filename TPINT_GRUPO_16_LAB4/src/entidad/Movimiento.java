package entidad;

import java.time.LocalDate;
import java.util.Date;

public class Movimiento {
	private int Id;
	private Cuenta CuentaOrigen;
	private Cuenta CuentaDestino;
	private float Importe;
	private Date Fecha;
	private String Detalle;
	private MovimientoTipo Tipo;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public Cuenta getCuentaOrigen() {
		return CuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.CuentaOrigen = cuentaOrigen;
	}
	public Cuenta getCuentaDestino() {
		return CuentaDestino;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.CuentaDestino = cuentaDestino;
	}
	public float getImporte() {
		return Importe;
	}
	public void setImporte(float importe) {
		this.Importe = importe;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		this.Fecha = fecha;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		this.Detalle = detalle;
	}
	public MovimientoTipo getTipo() {
		return Tipo;
	}
	public void setTipo(MovimientoTipo tipo) {
		this.Tipo = tipo;
	}
}
