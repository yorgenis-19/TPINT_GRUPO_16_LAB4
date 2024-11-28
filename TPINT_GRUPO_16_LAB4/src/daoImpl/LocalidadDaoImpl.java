package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dao.LocalidadDao;
import entidad.Localidad;

public class LocalidadDaoImpl implements LocalidadDao {

	@Override
	public ArrayList<Localidad> ObtenerTodos() {
		ArrayList<Localidad> resultados = new ArrayList<Localidad>();
	    String query = "SELECT * FROM Localidad";
	    Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        cn = new Conexion();
	        cn.Open();
	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Localidad obj = new Localidad();
	            obj.setId(rs.getInt("Id"));
	            obj.setNombre(rs.getString("Nombre"));
	            obj.setProvinciaId(rs.getInt("ProvinciaId"));
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
	public ArrayList<Localidad> ObtenerPorProvincia(int provinciaId) {
		ArrayList<Localidad> resultados = new ArrayList<Localidad>();
	    String query = "SELECT * FROM Localidad WHERE ProvinciaId = " + provinciaId;
	    Conexion cn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	    	cn = new Conexion();
	        cn.Open();
	        preparedStatement = (PreparedStatement) cn.prepareStatement(query);
	        
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Localidad obj = new Localidad();
	            obj.setId(rs.getInt("Id"));
	            obj.setNombre(rs.getString("Nombre"));
	            obj.setProvinciaId(rs.getInt("ProvinciaId"));
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
	public Localidad Obtener(int id) {
		Localidad obj = new Localidad();
	    String query = "SELECT * FROM Localidad WHERE Id = " + id;
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
	            obj.setProvinciaId(rs.getInt("ProvinciaId"));
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
