package com.server.gexam.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.impl.CoreServiceImpl;
import com.server.gexam.dao.TemplateImageDao;
import com.server.gexam.entity.TemplateImage;
import com.server.gexam.service.TemplateImageService;

public class TemplateImageServiceImpl extends CoreServiceImpl<TemplateImage, Serializable>
		implements TemplateImageService {

	private TemplateImageDao templateImageDao;

	public TemplateImageServiceImpl(TemplateImageDao templateImageDao) {
		super(templateImageDao);
		this.templateImageDao = templateImageDao;
	}
	
	@Override
	public List getTemplateImageList(String questionId, String imageId) throws DataAccessException {
		return templateImageDao.findTemplateImageList(questionId, imageId);
	}

}
