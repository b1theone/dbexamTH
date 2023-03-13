package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.ExamSuiteDao;
import com.server.gexam.entity.ExamSuite;
import com.server.gexam.service.ExamSuiteService;

public class ExamSuiteServiceImpl extends
		CoreServiceImpl<ExamSuite, Serializable> implements ExamSuiteService {

	private ExamSuiteDao examSuiteDao;

	public ExamSuiteServiceImpl(ExamSuiteDao examSuiteDao) {
		super(examSuiteDao);
		this.examSuiteDao = examSuiteDao;
	}
	
	@Override
	public List getSuiteList(String name) throws DataAccessException {
		return examSuiteDao.findSuiteList(name);
	}

}
