package package_1;

import java.sql.SQLException;

public class EtudiantMatriculeExistenceValidator implements IValidator<Etudiant> {

	private EtudiantRepository _studentRepository;
	
	public EtudiantMatriculeExistenceValidator() throws SQLException {
		this._studentRepository = new EtudiantRepository(new MySQLConnection());
	}
	
	@Override
	public boolean validate(Etudiant student) throws SQLException {
		if (_studentRepository.Exists(student.getId()))
			return true;
		
		return false;
	}
}
