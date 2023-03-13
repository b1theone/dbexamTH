package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.IndicatorDao;
import com.server.gexam.entity.Indicator;

public class IndicatorDaoImpl extends CoreDaoImpl<Indicator, Serializable>
		implements IndicatorDao {

	public IndicatorDaoImpl(Class<Indicator> entityClass) {
		super(entityClass);
	}

	@Override
	public List findAll() throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Indicator.class);
		criteria.addOrder(Order.asc("id"));

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
