package package_1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompositeValidator implements IValidator<Model> {

	private List<IValidator<Model>> _validators = new ArrayList<IValidator<Model>>();
	
	public void addValidator(IValidator validator) {
		_validators.add(validator);
	}
	
	@Override
	public boolean validate(Model etudiant) throws SQLException {
		for (IValidator<Model> validator : _validators) {
			if (! validator.validate(etudiant)) {
				return false;
			}
		}
		
		return true;
	}
}
