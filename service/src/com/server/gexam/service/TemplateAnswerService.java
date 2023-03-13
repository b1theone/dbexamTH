package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.TemplateAnswer;

public interface TemplateAnswerService extends CoreService<TemplateAnswer, Serializable> {

	public List getTemplateAnswerList(String questionId, String conditionType) throws DataAccessException;

}
