package entidad;

import java.sql.Date;
import java.time.LocalDate;

public class Cuenta {

	private int Id;
	private Cliente Cliente;
	private float Monto;
	private boolean Activa;
	private long CBU;
	private CuentaTipo Tipo;
	private Date FechaDeCreacion;
	
	
	public Cuenta() {
		super();
		this.Tipo = new CuentaTipo();
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	public float getMonto() {
		return Monto;
	}
	public void setMonto(float monto) {
		Monto = monto;
	}
	public boolean isActiva() {
		return Activa;
	}
	public void setActiva(boolean activa) {
		Activa = activa;
	}
	public long getCBU() {
		return CBU;
	}
	@Override
	public String toString() {
		return "Cuenta [Id=" + Id + ", Cliente=" + Cliente + ", Monto=" + Monto + ", Activa=" + Activa + ", CBU=" + CBU
				+ ", Tipo=" + Tipo + ", FechaDeCreacion=" + FechaDeCreacion + "]";
	}
	public void setCBU(long cBU) {
		CBU = cBU;
	}
	public CuentaTipo getTipo() {
		return Tipo;
	}
	public void setTipo(CuentaTipo tipo) {
		this.Tipo = tipo;
	}
	public Date getFechaDeCreacion() {
		return FechaDeCreacion;
	}
	public void setFechaDeCreacion(Date fechaDeCreacion) {
		FechaDeCreacion = fechaDeCreacion;
	}
	
	
}