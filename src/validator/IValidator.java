package validator;

import java.sql.SQLException;

public interface IValidator<E> {
	public boolean validate(E model) throws SQLException;
}
