package com.tp2.etudiant;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.tp2.validator.IValidator;

public class EtudiantMatriculeExistenceValidator implements IValidator<Etudiant> {

//	@Autowired
	private EtudiantRepository etudiantRepository;
	
	public EtudiantMatriculeExistenceValidator(EtudiantRepository etudiantRepository) {
		this.etudiantRepository = etudiantRepository;
	}
	
	@Override
	public boolean validate(Etudiant student) throws SQLException {
		if (etudiantRepository.Exists(student.getId()))
			return true;
		
		return false;
	}
}
