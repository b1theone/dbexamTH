package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.TemplateQuestion;

public interface TemplateQuestionDao
		extends CoreDao<TemplateQuestion, Serializable> {

	public List findTemplateQuestionList(String indicatorId, String conditionType, String templateType) throws DataAccessException;

}
