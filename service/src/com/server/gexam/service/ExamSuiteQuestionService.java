package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.ExamSuiteQuestion;

public interface ExamSuiteQuestionService
		extends CoreService<ExamSuiteQuestion, Serializable> {

	public List getSuiteQuestionList(String suiteId) throws DataAccessException;

}
