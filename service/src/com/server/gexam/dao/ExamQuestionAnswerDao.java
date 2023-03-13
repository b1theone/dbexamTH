package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.ExamQuestionAnswer;

public interface ExamQuestionAnswerDao
		extends CoreDao<ExamQuestionAnswer, Serializable> {

	public List findExamQuestionAnswerList(String indicatorId, String questionId) throws DataAccessException;

}
