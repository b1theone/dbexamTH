package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.ExamQuestion;

public interface ExamQuestionService
		extends CoreService<ExamQuestion, Serializable> {

	public boolean saveExamQuestion(List<ExamQuestion> entityList);

	public List getExamQuestionList(String indicatorId, String questionType, String question) throws DataAccessException;

}
