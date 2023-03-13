package com.server.gexam.service.impl;

import java.io.Serializable;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.ConstantDao;
import com.server.gexam.entity.Constant;
import com.server.gexam.service.ConstantService;

public class ConstantServiceImpl extends CoreServiceImpl<Constant, Serializable>
		implements ConstantService {

	private ConstantDao constantDao;

	public ConstantServiceImpl(ConstantDao constantDao) {
		super(constantDao);
		this.constantDao = constantDao;
	}

}
