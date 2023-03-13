package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.ExamQuestionImageDao;
import com.server.gexam.entity.ExamQuestionImage;

public class ExamQuestionImageDaoImpl
		extends CoreDaoImpl<ExamQuestionImage, Serializable>
		implements ExamQuestionImageDao {

	public ExamQuestionImageDaoImpl(Class<ExamQuestionImage> entityClass) {
		super(entityClass);
	}

	@Override
	public List findExamQuestionImageList(String indicatorId, String questionId) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExamQuestionImage.class);
		criteria.createAlias("examQuestion", "examQuestion");
		criteria.createAlias("examQuestion.indicator", "indicator");

		criteria.addOrder(Order.asc("indicator.id"));
		criteria.addOrder(Order.asc("examQuestion.id"));
		criteria.addOrder(Order.asc("id"));

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
