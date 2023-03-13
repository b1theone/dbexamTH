package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.ExamQuestionDao;
import com.server.gexam.entity.ExamQuestion;

public class ExamQuestionDaoImpl extends CoreDaoImpl<ExamQuestion, Serializable>
		implements ExamQuestionDao {

	public ExamQuestionDaoImpl(Class<ExamQuestion> entityClass) {
		super(entityClass);
	}

	@Override
	public List findExamQuestionList(String indicatorId, String questionType, String question) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExamQuestion.class);
		criteria.createAlias("indicator", "indicator");

		criteria.addOrder(Order.asc("indicator.id"));
		criteria.addOrder(Order.asc("id"));

		if (indicatorId != null && !indicatorId.equals("")) {
			criteria.add(Restrictions.eq("indicator.id", Integer.parseInt(indicatorId)));
		}
		if (questionType != null && !questionType.equals("")) {
			criteria.add(Restrictions.eq("questionType", questionType));
		}
		if (question != null && !question.equals("")) {
			criteria.add(Restrictions.like("question", "%" + question + "%"));
		}

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
