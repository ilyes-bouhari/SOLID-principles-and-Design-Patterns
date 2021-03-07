package package_1;

import java.sql.SQLException;

import database.MySQLConnection;
import validator.IValidator;

public class EtudiantEmailNonExistenceValidator implements IValidator<Etudiant> {

	private EtudiantRepository _studentRepository;
	
	public EtudiantEmailNonExistenceValidator() throws SQLException {
		this._studentRepository = new EtudiantRepository(new MySQLConnection());
	}
	
	@Override
	public boolean validate(Etudiant etudiant) throws SQLException {
		if (!_studentRepository.Exists(etudiant.getEmail()))
			return true;
					
		return false;
	}
}
