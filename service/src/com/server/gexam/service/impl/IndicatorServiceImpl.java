package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.IndicatorDao;
import com.server.gexam.entity.Indicator;
import com.server.gexam.service.IndicatorService;

public class IndicatorServiceImpl extends CoreServiceImpl<Indicator, Serializable> implements IndicatorService {

	private IndicatorDao indicatorDao;

	public IndicatorServiceImpl(IndicatorDao indicatorDao) {
		super(indicatorDao);
		this.indicatorDao = indicatorDao;
	}
	
	@Override
	public List getAll() throws DataAccessException {
		return indicatorDao.findAll();
	}

}
