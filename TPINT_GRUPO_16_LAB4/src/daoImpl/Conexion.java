package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private static final String host = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String pass = "root";    
    private String dbName = "BancoTP";

	protected Connection connection;
	
	public Connection Open()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(host+dbName, user, pass);
			System.out.println("Conexi�n Abierta en grupo16");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this.connection;
	}
	
	public ResultSet query(String query)
	{
		Statement st;
		ResultSet rs=null;
		try
		{
			st= connection.createStatement();
			rs= st.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		try {
			st = connection.createStatement();
		    st.executeUpdate(query);
			System.out.println("Datos insertados");
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		return save;
	}
	
	public boolean close()
	{
		boolean ok=true;
		try {
			connection.close();
		}
		catch(Exception e)
		{
			ok= false;
			e.printStackTrace();
		}
		return ok;
	}

	
	
    public PreparedStatement prepareStatement(String query) throws SQLException {
        if (connection != null) {
            return connection.prepareStatement(query);
        } else {
            throw new SQLException("Connection is not established.");
        }
    }
    
    
    
    
    
    

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        if (connection != null) {
        	connection.setAutoCommit(autoCommit);
        }
    }
    
    public void commit() throws SQLException {
        if (connection != null) {
        	connection.commit();
        }
    }
    
    public void rollback() throws SQLException {
        if (connection != null) {
        	connection.rollback();
        }
    }

    
    
    
    
    
	public PreparedStatement prepareStatement(String query, int returnGeneratedKeys) {
		
		PreparedStatement preparedStatement = null;

	    try {
	    	connection = Open();
	        if (returnGeneratedKeys == Statement.RETURN_GENERATED_KEYS) {
	            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        } else {
	            preparedStatement = connection.prepareStatement(query);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	         // Re-lanza la excepci�n para que sea manejada por el m�todo llamador
	    } finally {
	        // No cierres la conexi�n aqu�, debe ser cerrada despu�s de usar el PreparedStatement
	    }

	    return preparedStatement;
	}

	public Connection getConexion() {
		return this.connection;
	}
}
