package entidad;

public class Localidad {
	
	private int idLocalidad;
	private String nombreLocalidad;
	private int idProvincia;
	private boolean estado;
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", nombreLocalidad=" + nombreLocalidad + ", idProvincia="
				+ idProvincia + ", estado=" + estado + "]";
	}
	
	
}
