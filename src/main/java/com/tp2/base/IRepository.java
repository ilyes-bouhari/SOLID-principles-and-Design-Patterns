package com.tp2.base;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public abstract class IRepository<T extends Model> {
	abstract public T GetById(int id) throws SQLException;
	abstract public void add(T model) throws SQLException;
	abstract public void update(T model) throws SQLException;
	abstract public boolean Exists(String field) throws SQLException;
	abstract public boolean Exists(int field) throws SQLException;
}