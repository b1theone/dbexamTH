package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.Indicator;

public interface IndicatorService extends CoreService<Indicator, Serializable> {

	public List getAll() throws DataAccessException;

}
