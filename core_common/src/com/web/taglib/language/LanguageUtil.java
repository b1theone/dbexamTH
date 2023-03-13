package com.web.taglib.language;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LanguageUtil extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Object clasz = null;
	protected String attribute = null;

	public Object getClasz() {
		return clasz;
	}

	public void setClasz(Object clasz) {
		this.clasz = clasz;
	}

	public Object getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			if (clasz != null) {

				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				String local = (String) request.getSession().getAttribute("pLang");

				Class<?> cls = null;
				cls = clasz.getClass();

				Field field = null;

				String attr = "name";
				if (attribute != null && !attribute.equals("")) {
					attr = attribute;
				}

				if (local != null && local.equalsIgnoreCase("en")) {
					field = cls.getDeclaredField(attr + "En");
				} else {
					field = cls.getDeclaredField(attr + "Th");
				}

				field.setAccessible(true);
				Object value = field.get(clasz);
				out.println(value);
			} else {
				out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		clasz = null;
		attribute = null;
	}

}
