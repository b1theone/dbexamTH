package com.server.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.server.core.dao.CoreDao;

public class CoreDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements CoreDao<T, PK> {
	private Class<T> entityClass;

	public CoreDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void insert(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		return (T) getHibernateTemplate().merge(entity);
	}

	public void insertOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void insertOrUpdate(List<T> entityList) {
		getHibernateTemplate().saveOrUpdateAll(entityList);
	}

	public void delete(PK pk) {
		getHibernateTemplate().delete(findByPrimaryKey(pk));
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void delete(List<T> entityList) {
		getHibernateTemplate().deleteAll(entityList);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(PK pk) {
		T item = (T) getHibernateTemplate().get(entityClass, pk);
		if (item == null) {
			throw new ObjectRetrievalFailureException(entityClass, pk);
		}
		return item;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByQueryParam(String namedQuery, String[] params, Object[] condition) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(namedQuery, params, condition);
	}

	public int sqlQuary(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}
}
