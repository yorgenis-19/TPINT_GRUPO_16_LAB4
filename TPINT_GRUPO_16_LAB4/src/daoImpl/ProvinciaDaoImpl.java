package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dao.ProvinciaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaTipo;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

	@Override
	public ArrayList<Provincia> ObtenerTodos() {
		ArrayList<Provincia> resultados = new ArrayList<Provincia>();
	    String query = "SELECT * FROM Provincia";
	    Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        cn = new Conexion();
	        cn.Open();
	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Provincia obj = new Provincia();
	            obj.setId(rs.getInt("Id"));
	            obj.setNombre(rs.getString("Nombre"));
	            resultados.add(obj);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (cn != null) cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return resultados;
	}

	@Override
	public Provincia Obtener(int provinciaId) {
		Provincia obj = new Provincia();
	    String query = "SELECT * FROM Provincia WHERE Id = " + provinciaId;
	    Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        cn = new Conexion();
	        cn.Open();
	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            obj.setId(rs.getInt("Id"));
	            obj.setNombre(rs.getString("Nombre"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (cn != null) cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return obj;
	}

}
