package package_1;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnection {
		
	void init() throws SQLException;
	
	public Connection getConnection() throws SQLException;	
}
