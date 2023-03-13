package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.TemplateImageDao;
import com.server.gexam.entity.TemplateImage;

public class TemplateImageDaoImpl extends CoreDaoImpl<TemplateImage, Serializable> implements TemplateImageDao {

	public TemplateImageDaoImpl(Class<TemplateImage> entityClass) {
		super(entityClass);
	}

	@Override
	public List findTemplateImageList(String questionId, String imageId) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(TemplateImage.class);
		criteria.createAlias("templateQuestion", "templateQuestion");
		criteria.addOrder(Order.asc("templateQuestion.id"));
		criteria.addOrder(Order.asc("id"));
		
		//criteria.add(Restrictions.eq("templateQuestion.templateType", "1"));

		if (questionId != null && !questionId.equals("")) {
			criteria.add(Restrictions.eq("templateQuestion.id", Integer.parseInt(questionId)));
		}
		if (imageId != null && !imageId.equals("")) {
			criteria.add(Restrictions.eq("imageId", imageId));
		}

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
