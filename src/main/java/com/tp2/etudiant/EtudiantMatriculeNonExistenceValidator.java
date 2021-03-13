package com.tp2.etudiant;

import java.sql.SQLException;

import com.tp2.validator.IValidator;

public class EtudiantMatriculeNonExistenceValidator implements IValidator<Etudiant> {

	private EtudiantRepository etudiantRepository;
	
	public EtudiantMatriculeNonExistenceValidator(EtudiantRepository etudiantRepository) {
		this.etudiantRepository = etudiantRepository;
	}
		
	@Override
	public boolean validate(Etudiant student) throws SQLException {				
		if (! etudiantRepository.Exists(student.getId()) )
			return true;
		
		return false;
	}
}
