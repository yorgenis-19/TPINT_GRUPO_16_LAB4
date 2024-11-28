package entidad;

public class Localidad {
	
	private int Id;
	private String Nombre;
	private int ProvinciaId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getProvinciaId() {
		return ProvinciaId;
	}
	public void setProvinciaId(int provinciaId) {
		ProvinciaId = provinciaId;
	}
	
	
}
