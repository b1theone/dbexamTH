package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.ExamSuite;

public interface ExamSuiteDao extends CoreDao<ExamSuite, Serializable> {

	public List findSuiteList(String name) throws DataAccessException;

}
