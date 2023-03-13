package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.ExamSuiteQuestion;

public interface ExamSuiteQuestionDao
		extends CoreDao<ExamSuiteQuestion, Serializable> {

	public List findSuiteQuestionList(String suiteId) throws DataAccessException;

}
