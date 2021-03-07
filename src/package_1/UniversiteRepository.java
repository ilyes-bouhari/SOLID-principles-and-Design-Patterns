package package_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UniversiteRepository {
	
	Universite GetById(int universityId) throws SQLException {
		
		DBConnection dbConnection = DBConnection.getInstance();
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		
		System.out.println("LOG DB : debut recherche de id universite dans la base de donnee");
		
		String sql = "select * from universite where id_universite="+ universityId;

		ResultSet result = statement.executeQuery(sql);

		result.next();
		
		TypePackage typePackage = TypePackage.valueOf(result.getString(3));
		Universite university = new Universite (result.getInt(1), result.getString(2), typePackage);
			
		System.out.println("LOG DB : universite recuperee");
		
		connect.close();
		
		return university;		
	}
}
