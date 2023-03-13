package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.User;

public interface UserService extends CoreService<User, Serializable> {

	public List getLogin(String username, String password) throws DataAccessException;

}
