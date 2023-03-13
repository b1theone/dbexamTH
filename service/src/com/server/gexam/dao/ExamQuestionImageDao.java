package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.ExamQuestionImage;

public interface ExamQuestionImageDao extends CoreDao<ExamQuestionImage, Serializable> {

	public List findExamQuestionImageList(String indicatorId, String questionId) throws DataAccessException;

}
