package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.TemplateQuestion;

public interface TemplateQuestionService extends CoreService<TemplateQuestion, Serializable> {

	public List getTemplateQuestionList(String indicatorId, String conditionType, String templateType) throws DataAccessException;

}
