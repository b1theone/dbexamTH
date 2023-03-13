package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.ExamQuestionAnswer;

public interface ExamQuestionAnswerService
		extends CoreService<ExamQuestionAnswer, Serializable> {

	public List getExamQuestionAnswerList(String indicatorId, String questionId) throws DataAccessException;

}
