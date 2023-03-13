package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.TemplateAnswerDao;
import com.server.gexam.entity.TemplateAnswer;
import com.server.gexam.service.TemplateAnswerService;

public class TemplateAnswerServiceImpl extends CoreServiceImpl<TemplateAnswer, Serializable>
		implements TemplateAnswerService {

	private TemplateAnswerDao templateAnswerDao;

	public TemplateAnswerServiceImpl(TemplateAnswerDao templateAnswerDao) {
		super(templateAnswerDao);
		this.templateAnswerDao = templateAnswerDao;
	}
	
	@Override
	public List getTemplateAnswerList(String questionId, String conditionType) throws DataAccessException {
		return templateAnswerDao.findTemplateAnswerList(questionId, conditionType);
	}

}
