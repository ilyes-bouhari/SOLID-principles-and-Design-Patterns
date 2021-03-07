package package_1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.IDBConnection;
import journal.*;

public class EtudiantRepository implements IRepository<Etudiant> {
	
	private IDBConnection dbConnection;
	private CompositeJournal _journal;
	private String message;
	
	public EtudiantRepository(IDBConnection dbConnection) {
		this.dbConnection = dbConnection;
		this._journal = new CompositeJournal();
	}
	
	@Override
	public void add(Etudiant E) throws SQLException {

		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		String sql = "INSERT into etudiant values (" + E.getId() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";
		
		int result = statement.executeUpdate(sql);
		
		if (result == 1){
			
			message = "LOG : Ajout dans la BD reussi de l'etudiant du Matricule " + E.getId();
		    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
		    _journal.addJournal(new MetaJournal(new FileJournal()));
		    _journal.outPut_Msg(message);
			
		} else if (result == 0) {
			
			message = "LOG : Echec de l'ajout dans la BD de l'etudiant du Matricule " + E.getId();
		    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
		    _journal.addJournal(new MetaJournal(new FileJournal()));
		    _journal.outPut_Msg(message);
		}
		
		connect.close();
	}
	
	@Override
	public void update(Etudiant student) throws SQLException {

		Connection connect = dbConnection.getConnection();

		Statement statement = connect.createStatement();
		String sql 	= "UPDATE etudiant SET matricule = " + student.getId() 
					+ ", nom = '" + student.getNom() 
					+ "', prenom = '" + student.getPrenom()
					+ "', email = '" + student.getEmail()
					+ "', nbLivreMensuel_Autorise = " + student.getNbLivreMensuel_Autorise()
					+ ", nbLivreEmprunte = " + student.getNbLivreEmprunte()
					+ ", id_universite = " + student.getId_universite()
					+ " WHERE matricule = " + student.getId();
		
		int result = statement.executeUpdate(sql);

		if (result == 1){
			
			message = "LOG : Mise a jour dans la BD reussi de l'etudiant du Matricule " + student.getId();
		    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
		    _journal.addJournal(new MetaJournal(new FileJournal()));
		    _journal.outPut_Msg(message);

		} else if (result == 0){
			
			message = "LOG : Echec de la mise a jour dans la BD de l'etudiant du Matricule " + student.getId();
		    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
		    _journal.addJournal(new MetaJournal(new FileJournal()));
		    _journal.outPut_Msg(message);
		    
		}
		
		connect.close();
	}

	@Override
	public boolean Exists(String email) throws SQLException {
		
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		String sql = "select * from etudiant where email='"+ email+"'";
		
		ResultSet result = statement.executeQuery(sql);
		
		if (result.next()){
			
			message = "LOG DB : email existe dans la BD " + email;
		    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
		    _journal.addJournal(new MetaJournal(new FileJournal()));
		    _journal.outPut_Msg(message);
			
			connect.close();
			return true;
		}
				
		message = "LOG DB : email n'existe pas " + email;
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
		
		connect.close();
		
		return false;
	}
	
	@Override
	public boolean Exists(int mat) throws SQLException	{
		
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		
		String sql = "select * from etudiant where matricule="+ mat;
		
		ResultSet result = statement.executeQuery(sql);
		
		if (result.next()){
			
			message = "LOG DB : etudiant avec ce matricule existe deja dans la BD  " + mat;
		    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
		    _journal.addJournal(new MetaJournal(new FileJournal()));
		    _journal.outPut_Msg(message);
			
			connect.close();
			return true;
		}
				
		message = "LOG DB : etudiant avec ce matricule n'existe pas " + mat;
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
		
		connect.close();
		
		return false;
	}

	@Override
	public Etudiant GetById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Etudiant getByMatricule(int matricule) throws SQLException {
		
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
		
		String sql = "select * from etudiant where matricule='"+ matricule +"'";
		
		ResultSet result = statement.executeQuery(sql);
		
		result.next();
				
		Etudiant student = new Etudiant(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(8));
		student.setNbLivreEmprunte(result.getInt(7));
		student.setNbLivreMensuel_Autorise(result.getInt(6));
		
		return student;
	}
}
