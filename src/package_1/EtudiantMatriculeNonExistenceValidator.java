package package_1;

import java.sql.SQLException;

import database.MySQLConnection;
import validator.IValidator;

public class EtudiantMatriculeNonExistenceValidator implements IValidator<Etudiant> {

	private EtudiantRepository _studentRepository;
	
	public EtudiantMatriculeNonExistenceValidator() throws SQLException {
		this._studentRepository = new EtudiantRepository(new MySQLConnection());
	}
	
	@Override
	public boolean validate(Etudiant student) throws SQLException {
		if (!_studentRepository.Exists(student.getId()))
			return true;
		
		return false;
	}
}
