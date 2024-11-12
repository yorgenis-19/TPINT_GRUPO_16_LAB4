package entidad;

public class UsuarioTipo {
	
	private int Id;
	private String Descripcion;
	
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
	@Override
	public String toString() {
		return "TipoUsuario [idTipoUsuario=" + Id + ", tipo=" + Descripcion + "]";
	}
	
	
	

}
