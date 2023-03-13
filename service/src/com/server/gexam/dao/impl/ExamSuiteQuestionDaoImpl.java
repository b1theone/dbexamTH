package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.ExamSuiteQuestionDao;
import com.server.gexam.entity.ExamSuiteQuestion;

public class ExamSuiteQuestionDaoImpl
		extends CoreDaoImpl<ExamSuiteQuestion, Serializable>
		implements ExamSuiteQuestionDao {

	public ExamSuiteQuestionDaoImpl(Class<ExamSuiteQuestion> entityClass) {
		super(entityClass);
	}

	@Override
	public List findSuiteQuestionList(String suiteId) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExamSuiteQuestion.class);
		criteria.createAlias("examSuite", "examSuite");
		criteria.createAlias("examQuestion", "examQuestion");

		criteria.addOrder(Order.asc("id"));

		if (suiteId != null && !suiteId.equals(""))
			criteria.add(Restrictions.eq("examSuite.id", Integer.parseInt(suiteId)));

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}
}
