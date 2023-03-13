package com.web.taglib.paging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageRunning extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected String pageNo = null;
	protected String pageSize = null;
	protected Integer index = null;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			if (pageNo != null && pageSize != null && index != null) {
				out.println((Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize) + index + 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		pageNo = null;
		pageSize = null;
		index = null;
	}

}
