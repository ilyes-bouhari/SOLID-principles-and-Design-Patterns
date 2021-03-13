package com.tp2.etudiant;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tp2.base.Model;
import com.tp2.journal.*;
import com.tp2.universite.UniversiteRepository;
import com.tp2.validator.CompositeValidator;
import com.tp2.validator.IValidator;

@Component
public class EtudiantService {
		
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private UniversiteRepository universiteRepository;
	
	@Autowired
	private CompositeJournal compositeJournal;
	
	private String message;
	
	public boolean inscription (int matricule, String nom, String prenom, String email,String pwd, int id_universite) throws SQLException {
		
	    Etudiant student = new Etudiant(matricule, nom, prenom, email, pwd, id_universite);
	    	    
	    message = "LOG : Debut de l'operation d'ajout de l'etudiant avec matricule " + matricule;
	    compositeJournal.addJournal(new MetaJournal(new ConsoleJournal()));
	    compositeJournal.addJournal(new MetaJournal(new FileJournal()));
	    compositeJournal.outPut_Msg(message);
	    
	    CompositeValidator<Model> validator = new CompositeValidator<Model>();
	    validator.addValidator(new EtudiantEmailIsValidValidator());
	    validator.addValidator(new EtudiantMatriculeNonExistenceValidator(etudiantRepository));
	    validator.addValidator(new EtudiantEmailNonExistenceValidator(etudiantRepository));
	    
	    if (! validator.validate(student)) return false;
	    
	    System.out.println("There");
	    
		universiteRepository.setNbLivreMensuel_Autorise(student);                                       
	     
		etudiantRepository.add(student);
		 		
		message = "LOG : Fin de l'operation d'ajout de l'etudiant avec matricule " + matricule;
		compositeJournal.addJournal(new MetaJournal(new ConsoleJournal()));
		compositeJournal.addJournal(new MetaJournal(new FileJournal()));
		compositeJournal.outPut_Msg(message);
		
		return true;	
	}
	
	public boolean increase_NbLivreMensuel_Autorise(int matricule) throws SQLException {
		
		IValidator<Etudiant> validator = new EtudiantMatriculeExistenceValidator(etudiantRepository);
		if (! validator.validate(new Etudiant(matricule))) return false;

		Etudiant student = etudiantRepository.getByMatricule(matricule);
				
	    student = universiteRepository.increase_NbLivreMensuel_Autorise(student);
		
	    etudiantRepository.update(student);
	    
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
