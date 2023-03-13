package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.TemplateQuestionDao;
import com.server.gexam.entity.TemplateQuestion;

public class TemplateQuestionDaoImpl
		extends CoreDaoImpl<TemplateQuestion, Serializable>
		implements TemplateQuestionDao {

	public TemplateQuestionDaoImpl(Class<TemplateQuestion> entityClass) {
		super(entityClass);
	}

	@Override
	public List findTemplateQuestionList(String indicatorId, String conditionType, String templateType) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(TemplateQuestion.class);
		criteria.createAlias("indicator", "indicator");

		criteria.addOrder(Order.asc("indicator.id"));
		criteria.addOrder(Order.asc("id"));

		if (indicatorId != null && !indicatorId.equals("")) {
			criteria.add(Restrictions.eq("indicator.id", Integer.parseInt(indicatorId)));
		}
		if (conditionType != null && !conditionType.equals("")) {
			criteria.add(Restrictions.eq("conditionType", conditionType));
		}
		if (templateType != null && !templateType.equals("")) {
			criteria.add(Restrictions.eq("templateType", templateType));
		}

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
