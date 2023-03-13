package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.ExamSuiteDao;
import com.server.gexam.entity.ExamSuite;

public class ExamSuiteDaoImpl extends CoreDaoImpl<ExamSuite, Serializable>
		implements ExamSuiteDao {

	public ExamSuiteDaoImpl(Class<ExamSuite> entityClass) {
		super(entityClass);
	}

	@Override
	public List findSuiteList(String name) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExamSuite.class);
		criteria.addOrder(Order.asc("name"));

		if (name != null && !name.equals(""))
			criteria.add(Restrictions.like("name", "%" + name + "%"));

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
