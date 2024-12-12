package entidad;

import java.time.LocalDate;
import java.util.Date;

public class CuotaPrestamo {
	private int id;
	private int prestamoId;
	private int numeroCuota;
	private float monto;
	private Date fechaPago;
	private Date fechaVencimiento;
	private boolean estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CuotaPrestamo(int id, int prestamoId, int numeroCuota, float monto, Date fechaPago,
		Date fechaVencimiento, boolean estado) {
		this.id = id;
		this.prestamoId = prestamoId;
		this.numeroCuota = numeroCuota;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.fechaVencimiento = fechaVencimiento;
		this.estado = estado;
	}

	public int getPrestamoId() {
		return prestamoId;
	}
	public void setPrestamoId(int prestamoId) {
		this.prestamoId = prestamoId;
	}
	public int getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	@Override
	public String toString() {
		return "CuotaPrestamo [id=" + id + ", prestamoId=" + prestamoId + ", numeroCuota=" + numeroCuota + ", monto="
				+ monto + ", fechaPago=" + fechaPago + ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
