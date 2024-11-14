package entidad;


public class Usuario {
	
	public Usuario() {
		this.Tipo = new UsuarioTipo();
		this.Activo = true;
	}
	
	private int Id;
    private String Nombre;
    private String Clave;
    private UsuarioTipo Tipo;
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

	public UsuarioTipo getTipo() {
		return Tipo;
	}
	public void setTipo(UsuarioTipo tipo) {
		this.Tipo = tipo;
	}
	public boolean getActivo() {
		return Activo;
	}
	public void setActivo(boolean activo) {
		this.Activo = activo;
	}
       
    

}
