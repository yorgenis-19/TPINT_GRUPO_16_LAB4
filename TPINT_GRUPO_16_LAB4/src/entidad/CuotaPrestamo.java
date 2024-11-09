package entidad;

import java.time.LocalDate;

public class CuotaPrestamo {
	private int id;
	private int prestamoId;
	private int numeroCuota;
	private float monto;
	private LocalDate fechaPago;
	private LocalDate fechaVencimiento;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public LocalDate getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	@Override
	public String toString() {
		return "CuotaPrestamo [id=" + id + ", prestamoId=" + prestamoId + ", numeroCuota=" + numeroCuota + ", monto="
				+ monto + ", fechaPago=" + fechaPago + ", fechaVencimiento=" + fechaVencimiento + "]";
	}
	
	
}
