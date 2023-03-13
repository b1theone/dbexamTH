package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.ExamQuestionImageDao;
import com.server.gexam.entity.ExamQuestionImage;
import com.server.gexam.service.ExamQuestionImageService;

public class ExamQuestionImageServiceImpl extends CoreServiceImpl<ExamQuestionImage, Serializable>
		implements ExamQuestionImageService {

	private ExamQuestionImageDao examQuestionImageDao;

	public ExamQuestionImageServiceImpl(ExamQuestionImageDao examQuestionImageDao) {
		super(examQuestionImageDao);
		this.examQuestionImageDao = examQuestionImageDao;
	}

	@Override
	public List getExamQuestionImageList(String indicatorId, String questionId) throws DataAccessException {
		return examQuestionImageDao.findExamQuestionImageList(indicatorId, questionId);
	}
}
