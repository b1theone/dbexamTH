package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.ExamQuestionAnswerDao;
import com.server.gexam.entity.ExamQuestionAnswer;

public class ExamQuestionAnswerDaoImpl
		extends CoreDaoImpl<ExamQuestionAnswer, Serializable>
		implements ExamQuestionAnswerDao {

	public ExamQuestionAnswerDaoImpl(Class<ExamQuestionAnswer> entityClass) {
		super(entityClass);
	}

	@Override
	public List findExamQuestionAnswerList(String indicatorId, String questionId) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExamQuestionAnswer.class);
		criteria.createAlias("examQuestion", "examQuestion");
		criteria.createAlias("examQuestion.indicator", "indicator");

		criteria.addOrder(Order.asc("indicator.id"));
		criteria.addOrder(Order.asc("examQuestion.id"));
		criteria.addOrder(Order.asc("seq"));

		if (indicatorId != null && !indicatorId.equals("")) {
			criteria.add(Restrictions.eq("indicator.id", Integer.parseInt(indicatorId)));
		}
		if (questionId != null && !questionId.equals("")) {
			criteria.add(Restrictions.eq("examQuestion.id", Integer.parseInt(questionId)));
		}

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
