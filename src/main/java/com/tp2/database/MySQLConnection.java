package com.tp2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class MySQLConnection implements IDBConnection {

	String DB_NAME = "archi-tp-2";
	String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	String DB_USER = "root";
	String DB_PWD = "password";
	
	private Connection connection;
	
	public MySQLConnection() throws SQLException {
		init();
	}

	@Override
	public void init() throws SQLException {
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (connection.isClosed()) {
			init();
		}
		
		return connection;
	}
}
