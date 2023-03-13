package com.server.gexam.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.service.CoreService;
import com.server.gexam.entity.TemplateImage;

public interface TemplateImageService extends CoreService<TemplateImage, Serializable> {

	public List getTemplateImageList(String questionId, String imageId) throws DataAccessException;

}
