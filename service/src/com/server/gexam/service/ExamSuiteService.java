package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.ExamSuite;

public interface ExamSuiteService extends CoreService<ExamSuite, Serializable> {

	public List getSuiteList(String name) throws DataAccessException;

}
