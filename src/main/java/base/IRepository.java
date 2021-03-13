package base;

import java.sql.SQLException;

public abstract class IRepository<T extends Model> {
	abstract public T GetById(int id) throws SQLException;
	abstract public void add(T model) throws SQLException;
	abstract public void update(T model) throws SQLException;
	abstract public boolean Exists(String field) throws SQLException;
	abstract public boolean Exists(int field) throws SQLException;
}