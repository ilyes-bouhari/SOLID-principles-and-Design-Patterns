package package_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	   
	String DB_NAME = "archi-tp-2";
	String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	String DB_USER = "root";
	String DB_PWD = "password";
	
    private Connection connection;
	private static DBConnection instance;

    public DBConnection() throws SQLException {
    	connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
	}

    public Connection getConnection() {
		return connection;
	}
    
    public static DBConnection getInstance() throws SQLException {
    	
    	if (instance == null || instance.getConnection().isClosed()) {
    		instance = new DBConnection();
    	}
    	    	
		return instance;
	}
}
