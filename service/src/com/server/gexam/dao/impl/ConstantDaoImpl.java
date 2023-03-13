package com.server.gexam.dao.impl;

import java.io.Serializable;

import com.server.core.dao.impl.CoreDaoImpl;
import com.server.gexam.dao.ConstantDao;
import com.server.gexam.entity.Constant;

public class ConstantDaoImpl extends CoreDaoImpl<Constant, Serializable>
		implements ConstantDao {

	public ConstantDaoImpl(Class<Constant> entityClass) {
		super(entityClass);
	}

}
