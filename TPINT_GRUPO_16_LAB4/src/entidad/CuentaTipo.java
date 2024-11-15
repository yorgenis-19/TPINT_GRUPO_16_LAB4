package entidad;

public class CuentaTipo {

	private int Id;
	private String Descripcion;
	
	public CuentaTipo(int id, String descripcion) {
		super();
		this.Id = id;
		this.Descripcion = descripcion;
	}
	
	public CuentaTipo() {
	
		this.Id = 0;
		this.Descripcion = "";
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	
	

}
