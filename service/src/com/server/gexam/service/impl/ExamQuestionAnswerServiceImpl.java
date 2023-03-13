package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.ExamQuestionAnswerDao;
import com.server.gexam.entity.ExamQuestionAnswer;
import com.server.gexam.service.ExamQuestionAnswerService;

public class ExamQuestionAnswerServiceImpl
		extends CoreServiceImpl<ExamQuestionAnswer, Serializable>
		implements ExamQuestionAnswerService {

	private ExamQuestionAnswerDao examQuestionAnswerDao;

	public ExamQuestionAnswerServiceImpl(
			ExamQuestionAnswerDao examQuestionAnswerDao) {
		super(examQuestionAnswerDao);
		this.examQuestionAnswerDao = examQuestionAnswerDao;
	}

	@Override
	public List getExamQuestionAnswerList(String indicatorId, String questionId) throws DataAccessException {
		return examQuestionAnswerDao.findExamQuestionAnswerList(indicatorId, questionId);
	}
}
