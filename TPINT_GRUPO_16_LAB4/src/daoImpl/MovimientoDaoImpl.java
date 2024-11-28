package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.MovimientoDao;
import dao.UsuarioDao;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.MovimientoTipo;
import entidad.Usuario;
import entidad.UsuarioTipo;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoTipoNegocioImpl;

public class MovimientoDaoImpl implements MovimientoDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user ="root";
    private String pass = "root";
	private String dbName = "BancoTP";

	@Override
	public ArrayList<Movimiento> ObtenerPorCuenta(int cuentaId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM Movimiento WHERE CuentaOrigenId = " + cuentaId + " OR CuentaDestinoId = " + cuentaId;
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{	
				Movimiento obj = new Movimiento();
				obj.setId(rs.getInt("Id"));
				Cuenta origen = new CuentaNegocioImpl().Obtener(rs.getInt("CuentaOrigenId"));
				obj.setCuentaOrigen(origen);
				Cuenta destino = new CuentaNegocioImpl().Obtener(rs.getInt("CuentaDestinoId"));
				obj.setCuentaDestino(destino);
				obj.setImporte(rs.getFloat("Importe"));
				obj.setFecha(rs.getDate("Fecha"));
				obj.setDetalle(rs.getString("Detalle"));
				MovimientoTipo tipo = new MovimientoTipoNegocioImpl().Obtener(rs.getInt("TipoId"));
				obj.setTipo(tipo);
				
				movimientos.add(obj);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return movimientos;
	}

	@Override
	public int Guardar(Movimiento mov) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String query = "insert into Movimiento (`CuentaOrigenId`,`CuentaDestinoId`, `Importe`, `Fecha`, `Detalle`, `TipoId`) values ("+mov.getCuentaOrigen().getId()+","+mov.getCuentaDestino().getId()+","+mov.getImporte()+",'"+new SimpleDateFormat("yyyyMMdd").format(mov.getFecha())+"','"+mov.getDetalle()+"',"+mov.getTipo().getId()+")";
		//if(obj.getId() > 0)
		//{
		//	query = "UPDATE Usuario SET Nombre = '"+obj.getNombre()+"', Clave = '"+obj.getClave()+"', TipoId = "+obj.getTipo().getId()+", Activo = "+obj.getActivo()+" WHERE Id = " + obj.getId();
		//}
		
		Connection cn = null;
		int filas = 0;
			
		try {
			cn = DriverManager.getConnection(host+dbName, user, pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filas;
	}


}
