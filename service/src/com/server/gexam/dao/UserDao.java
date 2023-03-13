package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.User;

public interface UserDao extends CoreDao<User, Serializable> {

	public List findLogin(String username, String password) throws DataAccessException;

}
