package package_1;

public class EtudiantEmailIsValidValidator implements IValidator<Etudiant> {

	@Override
	public boolean validate(Etudiant student) {
		if(student.getEmail() == null || student.getEmail().length() == 0)
			return false;
		
		return true;
	}	
}
