package package_1;

import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantService {
	
	boolean inscription (int matricule, String nom, String prenom, String email,String pwd, int id_universite) throws SQLException {
		
		EtudiantRepository studentRepository = new EtudiantRepository();
	    UniversiteRepository universiteRepository = new UniversiteRepository();
	    Etudiant student = new Etudiant(matricule, nom, prenom, email, pwd, id_universite);
	    Universite university = universiteRepository.GetById(id_universite);
	    
	    System.out.println("LOG: debut de l'operation d'ajout de l'etudiant avec matricule "+matricule);
	    
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
		 
		System.out.println("LOG : Fin de l'operation d'ajout de l'etudiant avec matricule "+matricule);
		
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
