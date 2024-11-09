package entidad;

public class TipoMovimiento {
	private int idTipoMovimiento;
	private String nombreMovimiento;
	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public String getNombreMovimiento() {
		return nombreMovimiento;
	}
	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}
	@Override
	public String toString() {
		return "TipoMovimiento [idTipoMovimiento=" + idTipoMovimiento + ", nombreMovimiento=" + nombreMovimiento + "]";
	}
	
	
}
