package entidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Cliente {
	public Cliente() {
		this.Usuario = new Usuario();
		this.Nombre = "";
		this.Apellido = "";
		this.Sexo = "";
		this.Dni = "";
		this.Cuil = "";
		this.Telefono = "";
		this.Email = "";
		this.FechaNacimiento = new Date();
		this.Direccion = "";
	}
	private int Id;
	private Usuario Usuario;
	private String Nombre;
	private String Apellido;
	private String Sexo;
    private String Dni;
    private String Cuil;
    private String Telefono;
    private String Email;
    private Date FechaNacimiento;
    private String Direccion;
    private int LocalidadId;
    private int ProvinciaId;
	
    public Usuario getUsuario() {
		return Usuario;
	}
    
	public void setUsuario(Usuario usuario) {
		this.Usuario = usuario;
	}
	
	public String getDireccion() {
		return Direccion;
	}
	
	public void setDireccion(String direccion) {
		this.Direccion = direccion;
	}

    
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		this.Dni = dni;
	}
	public String getCuil() {
		return Cuil;
	}
	public void setCuil(String cuil) {
		this.Cuil = cuil;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		this.Sexo = sexo;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public int getLocalidadId() {
		return LocalidadId;
	}
	public void setLocalidadId(int id) {
		this.LocalidadId = id;
	}
	public int getProvinciaId() {
		return ProvinciaId;
	}
	public void setProvinciaId(int id) {
		ProvinciaId = id;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.FechaNacimiento = fechaNacimiento;
	}
	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}
	public String getNombreCompleto() {
		return this.Apellido + ", "+ this.Nombre;
	}
	 /*public String getFechaNacimiento() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
	        return this.FechaNacimiento.format(formatter);
	    }
	 
	

	  public void setFechaNacimiento(String fechaNacimiento) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
	        this.FechaNacimiento = LocalDate.parse(fechaNacimiento, formatter);
	    }
*/
}
