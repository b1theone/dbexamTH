package com.server.gexam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.UserDao;
import com.server.gexam.entity.User;

public class UserDaoImpl extends CoreDaoImpl<User, Serializable>
		implements UserDao {

	public UserDaoImpl(Class<User> entityClass) {
		super(entityClass);
	}

	@Override
	public List findLogin(String username, String password) throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);

		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));

		List objectList = getHibernateTemplate().findByCriteria(criteria);

		return objectList;
	}

}
