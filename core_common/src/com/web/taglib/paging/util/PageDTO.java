package com.web.taglib.paging.util;

import java.io.Serializable;

public class PageDTO implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 8304911984014886034L;
	private Integer pageNo;
	private Integer pageSize;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
