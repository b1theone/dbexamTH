package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.Indicator;

public interface IndicatorDao extends CoreDao<Indicator, Serializable> {
	public List findAll() throws DataAccessException;
}
