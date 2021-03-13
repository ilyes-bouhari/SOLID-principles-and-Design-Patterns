package universite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.IRepository;
import database.IDBConnection;
import etudiant.Etudiant;
import factory.AbstractFactory;
import factory.FactoryProvider;
import forfait.*;
import journal.*;

public class UniversiteRepository extends IRepository<Universite> {
	
	IDBConnection dbConnection;
	private CompositeJournal _journal;
	private String message;
	
	public UniversiteRepository(IDBConnection dbConnection) {
		this.dbConnection = dbConnection;
		this._journal = new CompositeJournal();
	}
	
	@Override
	public Universite GetById(int universityId) throws SQLException {
		
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
				
		message = "LOG DB : debut recherche de id universite dans la base de donnee";
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
		
		String sql = "select * from universite where id_universite="+ universityId;

		ResultSet result = statement.executeQuery(sql);

		result.next();
		
		TypePackage typePackage = TypePackage.valueOf(result.getString(3));
		Universite university = new Universite (result.getInt(1), result.getString(2), typePackage);
			
		message = "LOG DB : universite recuperee";
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
	    
		connect.close();
		
		return university;		
	}

	@Override
	public boolean Exists(String field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Exists(int field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Universite model) throws SQLException {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void update(Universite model) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public Etudiant setNbLivreMensuel_Autorise(Etudiant student) throws SQLException {
		
		TypePackage university_pack = this.GetById(student.getId_universite()).getPack();
		
		@SuppressWarnings("unchecked")
		AbstractFactory<Forfait<Etudiant>> forfaitFactory = (AbstractFactory<Forfait<Etudiant>>) FactoryProvider.getFactory("Forfait");
		
		Forfait<Etudiant> forfait = forfaitFactory.create(university_pack.toString());
		
		student.setNbLivreMensuel_Autorise(forfait.getNbrLivreMensuelAutorise());
		
		return student;
	}
	
	public Etudiant increase_NbLivreMensuel_Autorise(Etudiant student) throws SQLException {
		
		TypePackage university_pack = this.GetById(student.getId_universite()).getPack();

		@SuppressWarnings("unchecked")
		AbstractFactory<Forfait<Etudiant>> forfaitFactory = (AbstractFactory<Forfait<Etudiant>>) FactoryProvider.getFactory("Forfait");
		
		Forfait<Etudiant> forfait = forfaitFactory.create(university_pack.toString());

		if (forfait instanceof AugmentableForfait)
			forfait.increaseNbrLivreMensuelAutorise(student);
		
		return student;
	}
}
