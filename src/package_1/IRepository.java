package package_1;

import java.sql.SQLException;

public interface IRepository<T extends Model> {
	T GetById(int id) throws SQLException;
	void add(T model) throws SQLException;
	boolean Exists(String field) throws SQLException;
	boolean Exists(int field) throws SQLException;
}