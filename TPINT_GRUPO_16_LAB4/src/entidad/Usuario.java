package entidad;


public class Usuario {
	private int Id;
    private String Nombre;
    private String Clave;
    private int TipoUsuarioId;
    private boolean Activo;


 
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String usuario) {
		this.Nombre = usuario;
	}

	public String getClave() {
		return Clave;
	}
	public void setClave(String contrasena) {
		this.Clave = contrasena;
	}

	public int getTipoUsuarioId() {
		return TipoUsuarioId;
	}
	public void setTipoUsuarioId(int tipoUsuarioId) {
		this.TipoUsuarioId = tipoUsuarioId;
	}
	public boolean getActivo() {
		return Activo;
	}
	public void setActivo(boolean activo) {
		this.Activo = activo;
	}
       
    

}
