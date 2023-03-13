package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.ExamQuestionImage;

public interface ExamQuestionImageService extends CoreService<ExamQuestionImage, Serializable> {

	public List getExamQuestionImageList(String indicatorId, String questionId) throws DataAccessException;

}
