package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.ExamQuestionAnswerDao;
import com.server.gexam.dao.ExamQuestionDao;
import com.server.gexam.dao.ExamQuestionImageDao;
import com.server.gexam.entity.ExamQuestion;
import com.server.gexam.entity.ExamQuestionAnswer;
import com.server.gexam.entity.ExamQuestionImage;
import com.server.gexam.service.ExamQuestionService;

public class ExamQuestionServiceImpl
		extends CoreServiceImpl<ExamQuestion, Serializable>
		implements ExamQuestionService {

	private ExamQuestionDao examQuestionDao;
	private ExamQuestionAnswerDao examQuestionAnswerDao;
	private ExamQuestionImageDao examQuestionImageDao;

	public ExamQuestionServiceImpl(ExamQuestionDao examQuestionDao,
			ExamQuestionAnswerDao examQuestionAnswerDao,
			ExamQuestionImageDao examQuestionImageDao) {
		super(examQuestionDao);
		this.examQuestionDao = examQuestionDao;
		this.examQuestionAnswerDao = examQuestionAnswerDao;
		this.examQuestionImageDao = examQuestionImageDao;
	}

	@Override
	public boolean saveExamQuestion(List<ExamQuestion> entityList) {
		boolean result = false;
		try {
			if (entityList != null && entityList.size() > 0) {
				for (ExamQuestion question : entityList) {
					ExamQuestion nQuestion = examQuestionDao.merge(question);

					for (ExamQuestionImage image : question.getImageList()) {
						image.setExamQuestion(nQuestion);
						examQuestionImageDao.insert(image);
					}

					for (ExamQuestionAnswer answer : question.getAnswerList()) {
						answer.setExamQuestion(nQuestion);
						examQuestionAnswerDao.insert(answer);
					}
				}
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List getExamQuestionList(String indicatorId, String questionType, String question) throws DataAccessException {
		return examQuestionDao.findExamQuestionList(indicatorId, questionType, question);
	}

}
