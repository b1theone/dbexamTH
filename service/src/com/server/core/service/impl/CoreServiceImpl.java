package com.server.core.service.impl;

import java.io.Serializable;
import java.util.List;

import com.server.core.dao.CoreDao;
import com.server.core.service.CoreService;

public class CoreServiceImpl<T, PK extends Serializable> implements CoreService<T, PK> {
	private CoreDao<T, PK> coreDao;

	public CoreServiceImpl(CoreDao<T, PK> coreDao) {
		this.coreDao = coreDao;
	}

	public void saveItem(T entity) {
		coreDao.insert(entity);
	}

	public void updateItem(T entity) {
		coreDao.update(entity);
	}

	public T mergeItem(T entity) {
		return coreDao.merge(entity);
	}

	public void saveOrUpdateItem(T entity) {
		coreDao.insertOrUpdate(entity);
	}

	public void saveOrUpdateItems(List<T> entityList) {
		coreDao.insertOrUpdate(entityList);
	}

	public void removeItem(PK pk) {
		coreDao.delete(pk);
	}

	public void removeItem(T entity) {
		coreDao.delete(entity);
	}

	public void removeItems(List<T> entityList) {
		coreDao.delete(entityList);
	}

	public List<T> getAllItems() {
		return coreDao.findAll();
	}

	public T getItem(PK pk) {
		return (T) coreDao.findByPrimaryKey(pk);
	}

	public List<T> getItemsByCondition(String namedQuery, String[] params, Object[] conditions) {
		return coreDao.findByQueryParam(namedQuery, params, conditions);
	}

	public void saveItems(List<T> entityList) {
		for (T item : entityList) {
			coreDao.insert(item);
		}
	}

	public int getSqlQuary(String sql) {
		return coreDao.sqlQuary(sql);
	}
}
