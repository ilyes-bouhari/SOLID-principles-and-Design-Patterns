package package_1;

import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantService {
	
	private CompositeJournal _journal;
	private String message;
	
	public EtudiantService() {
		_journal = new CompositeJournal();
	}
	
	boolean inscription (int matricule, String nom, String prenom, String email,String pwd, int id_universite, IRepository<Etudiant> studentRepository, IRepository<Universite> universiteRepository) throws SQLException {
		
	    Etudiant student = new Etudiant(matricule, nom, prenom, email, pwd, id_universite);
	    Universite university = universiteRepository.GetById(id_universite);
	    	    
	    message = "LOG : Debut de l'operation d'ajout de l'etudiant avec matricule " + matricule;
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
	    
	    if (email == null || email.length() == 0) {
	    	return false;
	    }
	    
	    if (studentRepository.Exists(matricule)) {
	        return false;
	    }
	    
		if (studentRepository.Exists(email)) {
	        return false;
	    }
		
		if (university.getPack() == TypePackage.Standard) {
			student.setNbLivreMensuel_Autorise(10);
	    } else if (university.getPack() == TypePackage.Premium) {
	    	student.setNbLivreMensuel_Autorise(10*2);
	    }                           
	     
		studentRepository.add(student);
		 		
		message = "LOG : Fin de l'operation d'ajout de l'etudiant avec matricule " + matricule;
		_journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
		
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
