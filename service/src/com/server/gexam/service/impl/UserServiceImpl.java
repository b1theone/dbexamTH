package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.UserDao;
import com.server.gexam.entity.User;
import com.server.gexam.service.UserService;

public class UserServiceImpl extends CoreServiceImpl<User, Serializable>
		implements UserService {

	private UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		super(userDao);
		this.userDao = userDao;
	}
	
	@Override
	public List getLogin(String username, String password) throws DataAccessException {
		return userDao.findLogin(username, password);
	}

}
