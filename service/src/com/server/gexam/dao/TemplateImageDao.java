package com.server.gexam.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.server.core.dao.CoreDao;
import com.server.gexam.entity.TemplateImage;

public interface TemplateImageDao extends CoreDao<TemplateImage, Serializable> {

	public List findTemplateImageList(String questionId, String imageId) throws DataAccessException;

}
