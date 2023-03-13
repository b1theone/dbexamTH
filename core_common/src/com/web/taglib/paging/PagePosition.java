package com.web.taglib.paging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.MessageResources;

import com.web.taglib.paging.util.Pagination;

public class PagePosition extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Pagination paging = null;

	public Pagination getPaging() {
		return paging;
	}

	public void setPaging(Pagination paging) {
		this.paging = paging;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			if (paging != null) {

				String msgPage = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging").getMessage("msg.paging.page");
				String msgFrom = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging").getMessage("msg.paging.from");

				out.println(msgPage + "&nbsp;" + paging.getCurrentPage() + "&nbsp;" + msgFrom + "&nbsp;" + paging.getTotalPages());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		paging = null;
	}

}
