package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.TemplateQuestionDao;
import com.server.gexam.entity.TemplateQuestion;
import com.server.gexam.service.TemplateQuestionService;

public class TemplateQuestionServiceImpl extends CoreServiceImpl<TemplateQuestion, Serializable>
		implements TemplateQuestionService {

	private TemplateQuestionDao templateQuestionDao;

	public TemplateQuestionServiceImpl(TemplateQuestionDao templateQuestionDao) {
		super(templateQuestionDao);
		this.templateQuestionDao = templateQuestionDao;
	}

	@Override
	public List getTemplateQuestionList(String indicatorId, String conditionType, String templateType) throws DataAccessException {
		return templateQuestionDao.findTemplateQuestionList(indicatorId, conditionType, templateType);
	}
}
