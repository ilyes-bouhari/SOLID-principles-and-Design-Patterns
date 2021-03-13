package com.tp2.validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompositeValidator<T> implements IValidator<T> {

	private List<IValidator<T>> _validators = new ArrayList<IValidator<T>>();
	
	public void addValidator(IValidator validator) {
		_validators.add(validator);
	}
	
	@Override
	public boolean validate(T etudiant) throws SQLException {
		for (IValidator<T> validator : _validators) {
			if (! validator.validate(etudiant)) {
				return false;
			}
		}
		
		return true;
	}
}
