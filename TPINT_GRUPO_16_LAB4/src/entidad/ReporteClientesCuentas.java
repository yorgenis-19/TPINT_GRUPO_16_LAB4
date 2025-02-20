package entidad;

public class ReporteClientesCuentas {
	public ReporteClientesCuentas() {
		this.Usuario = "";
		this.Nombre = "";
		this.Apellido = "";
		this.Sexo = "";
		this.Dni = "";
		this.Email = "";
		this.DineroTotal = 0;
		this.PrestamosSolicitados = 0;
		this.DineroSolicitado = 0;
		this.DineroCuotasPagadas = 0;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public double getDineroTotal() {
		return DineroTotal;
	}
	public void setDineroTotal(double dineroTotal) {
		DineroTotal = dineroTotal;
	}
	public int getPrestamosSolicitados() {
		return PrestamosSolicitados;
	}
	public void setPrestamosSolicitados(int prestamosSolicitados) {
		PrestamosSolicitados = prestamosSolicitados;
	}
	public double getDineroSolicitado() {
		return DineroSolicitado;
	}
	public void setDineroSolicitado(double dineroSolicitado) {
		DineroSolicitado = dineroSolicitado;
	}
	public double getDineroCuotasPagadas() {
		return DineroCuotasPagadas;
	}
	public void setDineroCuotasPagadas(double dineroCuotasPagadas) {
		DineroCuotasPagadas = dineroCuotasPagadas;
	}
	private String Usuario;
	private String Nombre;
	private String Apellido;
	private String Sexo;
    private String Dni;
    private String Email;
    private double DineroTotal;
    private int PrestamosSolicitados;
    private double DineroSolicitado;
    private double DineroCuotasPagadas;
	
}
