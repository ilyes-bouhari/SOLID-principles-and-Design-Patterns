package com.tp2.etudiant;

import java.sql.SQLException;

import com.tp2.validator.IValidator;

public class EtudiantEmailNonExistenceValidator implements IValidator<Etudiant> {

	private EtudiantRepository etudiantRepository;
	
	public EtudiantEmailNonExistenceValidator(EtudiantRepository etudiantRepository) {
		this.etudiantRepository = etudiantRepository;
	}
	
	@Override
	public boolean validate(Etudiant etudiant) throws SQLException {
		if (! etudiantRepository.Exists(etudiant.getEmail()) )
			return true;
					
		return false;
	}
}
