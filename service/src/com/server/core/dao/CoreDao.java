package com.server.core.dao;

import java.io.Serializable;
import java.util.List;

public interface CoreDao<T, PK extends Serializable> {

	public void insert(T entity);

	public void update(T entity);

	public T merge(T entity);

	public void insertOrUpdate(T entity);

	public void insertOrUpdate(List<T> entityList);

	public void delete(PK pk);

	public void delete(T entity);

	public void delete(List<T> entityList);

	public List<T> findAll();

	public T findByPrimaryKey(PK pk);

	public List<T> findByQueryParam(String namedQuery, String[] params,
			Object[] conditions);
	
	public int sqlQuary(String sql);
}
