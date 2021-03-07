package package_1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EtudiantRepository {
	
	void add(Etudiant E) throws SQLException {

		DBConnection dbConnection = new DBConnection();
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";
		
		int result = statement.executeUpdate(sql);
		
		if (result == 1){
			System.out.println("LOG : Ajout dans la BD reussi de l'etudiant  du Matricule" + E.getMatricule());
		} else if (result == 0) {
			System.out.println("LOG : Echec de l'ajout dans la BD de l'etudiant  du Matricule" + E.getMatricule());
		}
		
		connect.close();
	 }


	boolean Exists(String email) throws SQLException {
		
		DBConnection dbConnection = new DBConnection();
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		String sql = "select * from etudiant where email='"+ email+"'";
		
		ResultSet result = statement.executeQuery(sql);
		
		if (result.next()){
			System.out.println("LOG DB : email existe dans la BD " + email);
			connect.close();
			return true;
		}
		
		System.out.println("LOG DB : email n'existe pas " + email);	
		
		connect.close();
		
		return false;
	}
	
	boolean Exists(int mat) throws SQLException	{
		
		DBConnection dbConnection = new DBConnection();
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		
		String sql = "select * from etudiant where matricule="+ mat;
		
		ResultSet result = statement.executeQuery(sql);
		
		if (result.next()){
			System.out.println("LOG DB : etudiant avec ce matricule existe deja dans la BD " + mat);
			connect.close();
			return true;
		}
		
		System.out.println("LOG DB : etudiant avec ce matricule n'existe pas " + mat);	
		
		connect.close();
		
		return false;
	}

}
