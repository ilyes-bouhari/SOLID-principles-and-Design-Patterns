package com.tp2.universite;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp2.base.IRepository;
import com.tp2.database.IDBConnection;
import com.tp2.etudiant.Etudiant;
import com.tp2.factory.AbstractFactory;
import com.tp2.factory.FactoryProvider;
import com.tp2.forfait.*;
import com.tp2.journal.*;

@Service
public class UniversiteRepository extends IRepository<Universite> {
	
	@Autowired
	private IDBConnection mySQLConnection;
	
	@Autowired
	private CompositeJournal compositeJournal;
	
	private String message;
		
	@Override
	public Universite GetById(int universityId) throws SQLException {
		
		Connection connect = mySQLConnection.getConnection();
		
		Statement statement = connect.createStatement();
				
		message = "LOG DB : debut recherche de id universite dans la base de donnee";
		compositeJournal.addJournal(new MetaJournal(new ConsoleJournal()));
		compositeJournal.addJournal(new MetaJournal(new FileJournal()));
	    compositeJournal.outPut_Msg(message);
		
		String sql = "select * from universite where id_universite="+ universityId;

		ResultSet result = statement.executeQuery(sql);

		result.next();
		
		TypePackage typePackage = TypePackage.valueOf(result.getString(3));
		Universite university = new Universite (result.getInt(1), result.getString(2), typePackage);
			
		message = "LOG DB : universite recuperee";
		compositeJournal.addJournal(new MetaJournal(new ConsoleJournal()));
		compositeJournal.addJournal(new MetaJournal(new FileJournal()));
	    compositeJournal.outPut_Msg(message);
	    
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
