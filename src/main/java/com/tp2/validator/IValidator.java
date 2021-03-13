package com.tp2.validator;

import java.sql.SQLException;

public interface IValidator<E> {
	public boolean validate(E model) throws SQLException;
}
