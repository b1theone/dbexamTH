package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.ExamQuestion;

public interface ExamQuestionDao extends CoreDao<ExamQuestion, Serializable> {

	public List findExamQuestionList(String indicatorId, String questionType, String question) throws DataAccessException;

}
