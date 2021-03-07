package package_1;

import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQLConnection;
import journal.*;
import validator.CompositeValidator;
import validator.IValidator;

public class EtudiantService {
	
	private CompositeJournal _journal;
	private String message;
	
	public EtudiantService() {
		_journal = new CompositeJournal();
	}
	
	boolean inscription (int matricule, String nom, String prenom, String email,String pwd, int id_universite, IRepository<Etudiant> studentRepository, UniversiteRepository universiteRepository) throws SQLException {
		
	    Etudiant student = new Etudiant(matricule, nom, prenom, email, pwd, id_universite);
	    	    
	    message = "LOG : Debut de l'operation d'ajout de l'etudiant avec matricule " + matricule;
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
	    
	    CompositeValidator<Model> validator = new CompositeValidator<Model>();
	    validator.addValidator(new EtudiantEmailIsValidValidator());
	    validator.addValidator(new EtudiantMatriculeNonExistenceValidator());
	    validator.addValidator(new EtudiantEmailNonExistenceValidator());
	    
	    if (! validator.validate(student)) return false;
	    
		universiteRepository.setNbLivreMensuel_Autorise(student);                                       
	     
		studentRepository.add(student);
		 		
		message = "LOG : Fin de l'operation d'ajout de l'etudiant avec matricule " + matricule;
		_journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
		
		return true;	
	}
	
	public boolean increase_NbLivreMensuel_Autorise(int matricule) throws SQLException {
		
		IValidator<Etudiant> validator = new EtudiantMatriculeExistenceValidator();
		if (! validator.validate(new Etudiant(matricule))) return false;

		EtudiantRepository studentRepository = new EtudiantRepository(new MySQLConnection());
		Etudiant student = studentRepository.getByMatricule(matricule);
				
	    UniversiteRepository universiteRepository = new UniversiteRepository(new MySQLConnection());
	    student = universiteRepository.increase_NbLivreMensuel_Autorise(student);
		
	    studentRepository.update(student);
	    
		return true;
	}
	
	public ArrayList<Etudiant> GetEtudiantParUniversitye() {
	    //...
		return new ArrayList<>(4);
	}

	public ArrayList<Etudiant> GetEtudiatparLivreEmprunte() {
	    //...
		return new ArrayList<>(4);
		
	}
}
