package com.server.core.service;

import java.io.Serializable;
import java.util.List;

public interface CoreService<T, PK extends Serializable> {
	public void saveItem(T entity);

	public void updateItem(T entity);

	public T mergeItem(T entity);

	public void saveOrUpdateItem(T entity);

	public void saveOrUpdateItems(List<T> entityList);

	public void removeItem(PK pk);

	public void removeItem(T entity);

	public void removeItems(List<T> entityList);

	public List<T> getAllItems();

	public T getItem(PK pk);

	public List<T> getItemsByCondition(String namedQuery, String[] params, Object[] conditions);

	public void saveItems(List<T> entityList);

	public int getSqlQuary(String sql);
}
