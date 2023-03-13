package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.ExamSuiteQuestionDao;
import com.server.gexam.entity.ExamSuiteQuestion;
import com.server.gexam.service.ExamSuiteQuestionService;

public class ExamSuiteQuestionServiceImpl
		extends CoreServiceImpl<ExamSuiteQuestion, Serializable>
		implements ExamSuiteQuestionService {

	private ExamSuiteQuestionDao examSuiteQuestionDao;

	public ExamSuiteQuestionServiceImpl(
			ExamSuiteQuestionDao examSuiteQuestionDao) {
		super(examSuiteQuestionDao);
		this.examSuiteQuestionDao = examSuiteQuestionDao;
	}
	
	@Override
	public List getSuiteQuestionList(String suiteId) throws DataAccessException {
		return examSuiteQuestionDao.findSuiteQuestionList(suiteId);
	}

}
