package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.TemplateAnswerDao;
import com.server.gexam.entity.TemplateAnswer;

public class TemplateAnswerDaoImpl extends CoreDaoImpl<TemplateAnswer, Serializable> implements TemplateAnswerDao {

	public TemplateAnswerDaoImpl(Class<TemplateAnswer> entityClass) {
		super(entityClass);
	}

	@Override
	public List findTemplateAnswerList(String questionId, String conditionType) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(TemplateAnswer.class);
		criteria.createAlias("templateQuestion", "templateQuestion");

		if (questionId != null && !questionId.equals("")) {
			criteria.add(Restrictions.eq("templateQuestion.id", Integer.parseInt(questionId)));
		}
		if (conditionType != null && !conditionType.equals("")) {
			criteria.add(Restrictions.eq("conditionType", conditionType));
		}

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
