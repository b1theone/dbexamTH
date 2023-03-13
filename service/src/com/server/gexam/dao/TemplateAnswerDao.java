package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.TemplateAnswer;

public interface TemplateAnswerDao extends CoreDao<TemplateAnswer, Serializable> {

	public List findTemplateAnswerList(String questionId, String conditionType) throws DataAccessException;

}
